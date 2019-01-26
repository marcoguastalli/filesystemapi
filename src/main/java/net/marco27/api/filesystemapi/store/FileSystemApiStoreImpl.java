package net.marco27.api.filesystemapi.store;

import net.marco27.api.base.oracle.OracleServiceImpl;
import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.repository.FileStructureCrudRepository;
import net.marco27.api.filesystemapi.repository.FileStructureJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileSystemApiStoreImpl extends OracleServiceImpl implements FileSystemApiStore {

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

        return fileStructureCrudRepository.findByPath(path);
    }

    @Override
    public FileStructure findFileStructure(final String path) {
        final Optional<FileStructure> result = fileStructureCrudRepository.findById(path);
        return result.orElse(null);
    }

    @Override
    public FileStructure storeFileStructure(final FileStructure fileStructure) {
        return fileStructureCrudRepository.save(fileStructure);
    }
}
