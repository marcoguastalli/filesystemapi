package net.marco27.api.filesystemapi.store;

import net.marco27.api.base.cassandra.CassandraService;
import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileSystemApiStore extends CassandraService {

    FileStructure loadFileStructure(String path);

    FileStructure storeFileStructure(FileStructure fileStructure);
}
