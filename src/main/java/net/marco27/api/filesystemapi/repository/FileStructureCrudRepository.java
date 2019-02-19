package net.marco27.api.filesystemapi.repository;

import net.marco27.api.filesystemapi.domain.FileStructure;
import org.springframework.data.repository.CrudRepository;

public interface FileStructureCrudRepository extends CrudRepository<FileStructure, String> {

    FileStructure findByPath(String path);
}
