package net.marco27.api.filesystemapi.store;

import net.marco27.api.base.oracle.OracleServiceImpl;
import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.repository.FileStructureCrudRepository;
import net.marco27.api.filesystemapi.repository.FileStructureJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class FileSystemApiStoreImpl extends OracleServiceImpl implements FileSystemApiStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemApiStoreImpl.class);

    private static final String CQL_SELECT_BY_PATH = "SELECT * FROM file_structure WHERE path = '%s'";

    private ApplicationConfiguration applicationConfiguration;
    private FileStructureJpaRepository fileStructureJpaRepository;
    private FileStructureCrudRepository fileStructureCrudRepository;

    public FileSystemApiStoreImpl(@Autowired final ApplicationConfiguration applicationConfiguration,
                                  @Autowired FileStructureJpaRepository fileStructureJpaRepository,
                                  @Autowired FileStructureCrudRepository fileStructureCrudRepository) {
        this.applicationConfiguration = applicationConfiguration;
        this.fileStructureJpaRepository = fileStructureJpaRepository;
        this.fileStructureCrudRepository = fileStructureCrudRepository;
    }

    @Override
    public FileStructure findFileStructureById(final String path) {
        final Optional<FileStructure> result = fileStructureCrudRepository.findById(path);
        return result.orElse(null);
    }

    @Override
    public FileStructure findFileStructureByPath(final String path) {
        return fileStructureCrudRepository.findByPath(path);
    }

    @Override
    public FileStructure savePathStructure(final FileStructure fileStructure) {
        return fileStructureCrudRepository.save(fileStructure);
    }

    @Override
    public void deletePathStructure(final FileStructure fileStructure) {
        fileStructureCrudRepository.delete(fileStructure);
    }

    @Override
    public FileStructure loadFileStructure(final String path) {
        /*
        try (Cluster cluster = getCassandraCluster(applicationConfiguration.getCassandraAddresses())) {
            try (Session session = getCassandraSession(cluster, applicationConfiguration.getCassandraKeyspace())) {
                String query = String.format(CQL_SELECT_BY_PATH, path);
                ResultSet resultSet = session.execute(query);
                for (final Row row : resultSet) {
                    String rowPath = row.getString("path");
                    return new FileStructure.Builder(rowPath).build();
                }
            }
        }
        */
        FileStructure result = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT PATH, EXT, IS_DIRECTORY, NAME, TIMESTAMP FROM FILE_STRUCTURE WHERE PATH = ?");
            preparedStatement.setString(1, path);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String findPath = resultSet.getString(1);
                final String ext = resultSet.getString(2);
                final boolean isDirectory = resultSet.getBoolean(3);
                final String name = resultSet.getString(4);
                final String timestamp = resultSet.getString("TIMESTAMP");
                result = new FileStructure.Builder(findPath, name, ext).isDirectory(isDirectory).withName(name).withTimestamp(timestamp).build();
            }
            resultSet.close();
        } catch (Exception e) {
            LOGGER.error("Error loadFileStructure", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error close PreparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Error close Connection", e);
                }
            }
        }
        return result;
    }

}
