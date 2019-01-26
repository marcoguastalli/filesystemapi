package net.marco27.api.filesystemapi.store;

import net.marco27.api.base.oracle.OracleService;
import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileSystemApiStore extends OracleService {

    FileStructure loadFileStructure(String path);

    FileStructure findFileStructure(String path);

    FileStructure storeFileStructure(FileStructure fileStructure);
}
