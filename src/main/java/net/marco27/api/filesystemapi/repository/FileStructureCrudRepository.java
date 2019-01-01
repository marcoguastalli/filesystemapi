package net.marco27.api.filesystemapi.repository;

import org.springframework.data.repository.CrudRepository;

import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileStructureCrudRepository extends CrudRepository<FileStructure, String> {
}
