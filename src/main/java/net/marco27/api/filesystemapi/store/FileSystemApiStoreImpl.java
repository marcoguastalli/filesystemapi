package net.marco27.api.filesystemapi.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import net.marco27.api.base.cassandra.CassandraServiceImpl;
import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;

@Service
public class FileSystemApiStoreImpl extends CassandraServiceImpl implements FileSystemApiStore {

    private ApplicationConfiguration applicationConfiguration;

    public FileSystemApiStoreImpl(@Autowired final ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    public FileStructure loadFileStructure(final String path) {
        Cluster cluster = getCassandraCluster(applicationConfiguration.getCassandraAddresses());
        Session session = getCassandraSession(cluster, applicationConfiguration.getCassandraKeyspace());
        session.close();
        cluster.close();
        return null;
    }

    @Override
    public void storeFileStructure(final FileStructure result) {

    }
}
