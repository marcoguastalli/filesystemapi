package net.marco27.api.filesystemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.marco27.api.filesystemapi.domain.FileStructure;

public interface FileStructureJpaRepository extends JpaRepository<FileStructure, String> {
}
