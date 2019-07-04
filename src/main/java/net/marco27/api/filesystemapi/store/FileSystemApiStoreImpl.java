package net.marco27.api.filesystemapi.store;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.repository.FileStructureJpaRepository;

@Service
public class FileSystemApiStoreImpl implements FileSystemApiStore {

    private FileStructureJpaRepository fileStructureJpaRepository;

    public FileSystemApiStoreImpl(@Autowired final FileStructureJpaRepository fileStructureJpaRepository) {
        this.fileStructureJpaRepository = fileStructureJpaRepository;
    }

    @Override
    public FileStructure findFileStructureById(final String path) {
        final Optional<FileStructure> result = this.fileStructureJpaRepository.findById(path);
        return result.orElse(null);
    }

    @Override
    public FileStructure findFileStructureByPath(final String path) {
        return this.fileStructureJpaRepository.findByPath(path);
    }

    @Override
    public FileStructure saveFileStructure(final FileStructure fileStructure) {
        return this.fileStructureJpaRepository.save(fileStructure);
    }

    @Override
    public void deleteFileStructure(final FileStructure fileStructure) {
        this.fileStructureJpaRepository.delete(fileStructure);
    }

}
