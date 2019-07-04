package net.marco27.api.filesystemapi.store;

import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileSystemApiStore {

    FileStructure findFileStructureById(String path);

    FileStructure findFileStructureByPath(String path);

    FileStructure saveFileStructure(FileStructure fileStructure);

    void deleteFileStructure(FileStructure fileStructure);

}
