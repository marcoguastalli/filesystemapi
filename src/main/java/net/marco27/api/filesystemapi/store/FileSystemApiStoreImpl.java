package net.marco27.api.filesystemapi.store;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import net.marco27.api.base.cassandra.CassandraServiceImpl;
import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.repository.FileStructureCrudRepository;
import net.marco27.api.filesystemapi.repository.FileStructureJpaRepository;

@Service
public class FileSystemApiStoreImpl extends CassandraServiceImpl implements FileSystemApiStore {

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
        try (Cluster cluster = getCassandraCluster(applicationConfiguration.getCassandraAddresses())) {
            Session session = getCassandraSession(cluster, applicationConfiguration.getCassandraKeyspace());
            session.close();
        }
        Optional<FileStructure> result = fileStructureJpaRepository.findById(path);
        return result.orElse(null);
    }

    @Override
    public FileStructure storeFileStructure(final FileStructure fileStructure) {
        return fileStructureJpaRepository.save(fileStructure);
    }
}
