package net.marco27.api.filesystemapi.store;

import java.util.Optional;

import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileSystemApiStore {

    Optional<FileStructure> findById(String path);

    FileStructure findFileStructureByPath(String path);

    FileStructure saveFileStructure(FileStructure fileStructure);

    void deleteFileStructure(FileStructure fileStructure);

}
