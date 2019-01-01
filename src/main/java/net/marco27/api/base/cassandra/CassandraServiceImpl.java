package net.marco27.api.base.cassandra;

import org.springframework.stereotype.Service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

@Service
public class CassandraServiceImpl implements CassandraService {

    private static final String CQL_KEYSPACE_CREATE = "CREATE KEYSPACE IF NOT EXISTS %s WITH replication = {'class':'SimpleStrategy','replication_factor':1};";

    @Override
    public Cluster getCassandraCluster(final String... addresses) {
        return Cluster.builder().addContactPoints(addresses).build();
    }

    @Override
    public Session getCassandraSession(final Cluster cluster, final String keyspaceName) {
        return cluster.connect(keyspaceName);
    }

    @Override
    public boolean createKeyspace(final Session session, final String keyspaceName) {
        final String query = String.format(CQL_KEYSPACE_CREATE, keyspaceName);
        final ResultSet resultSet = session.execute(query);
        return resultSet.wasApplied();
    }
}
