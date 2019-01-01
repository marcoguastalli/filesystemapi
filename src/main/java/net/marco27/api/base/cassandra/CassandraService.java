package net.marco27.api.base.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public interface CassandraService {

    Cluster getCassandraCluster(String... addresses);

    Session getCassandraSession(Cluster cluster, String keyspaceName);

    boolean createKeyspace(Session session, String keyspaceName);
}
