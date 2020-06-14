package net.marco27.api.filesystemapi.controller;

import static net.marco27.api.base.ApiConstants.SLASH;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.marco27.api.base.domain.JsonError;
import net.marco27.api.base.domain.JsonSuccess;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.exception.DocumentNotFoundException;
import net.marco27.api.filesystemapi.service.FileSystemApiService;
import net.marco27.api.filesystemapi.store.FileSystemApiStore;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;
import net.marco27.api.filesystemapi.validation.service.ValidationService;

/** The main use case for the API is to read the filesystem */
@RestController
@RequestMapping
public class FileSystemApiController {

    private final FileSystemApiService fileSystemApiService;
    private final ValidationService validationService;
    private final FileSystemApiStore fileSystemApiStore;

    public FileSystemApiController(@Autowired final FileSystemApiService fileSystemApiService,
            @Autowired final ValidationService validationService,
            @Autowired final FileSystemApiStore fileSystemApiStore) {
        this.fileSystemApiService = fileSystemApiService;
        this.validationService = validationService;
        this.fileSystemApiStore = fileSystemApiStore;
    }

    @PostMapping("/create/{path}")
    public ResponseEntity<FileStructure> storeFileStructure(@Valid @PathVariable final String path) throws DocumentNotFoundException {
        final String validPath = validatePath(path);
        Optional<FileStructure> result = fileSystemApiStore.findById(validatePath(validPath));
        if (!result.isPresent()) {
            FileStructure fileStructure = fileSystemApiService.createFileStructure(validPath);
            if (fileStructure != null) {
                fileSystemApiStore.saveFileStructure(fileStructure);
                result = Optional.of(fileStructure);
            } else {
                throw new DocumentNotFoundException("Invalid path: " + path);
            }
        }
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("/read/{path}")
    public ResponseEntity<FileStructure> findFileStructure(@Valid @PathVariable final String path) throws DocumentNotFoundException {
        FileStructure result = fileSystemApiStore.findFileStructureByPath(validatePath(path));
        if (result == null) {
            throw new DocumentNotFoundException("Invalid path: " + path);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find/{path}")
    public FileStructure findFileStructureById(@NotNull(message = "path could not be null.") @PathVariable("path") final String path)
            throws DocumentNotFoundException {
        final Optional<FileStructure> fileStructure = fileSystemApiStore.findById(validatePath(path));
        return fileStructure.orElseThrow(() -> new DocumentNotFoundException("FileStructure not found with path: " + path));
    }

    @PutMapping("/update/{path}")
    public ResponseEntity<FileStructure> update(@PathVariable(value = "path") final String path,
            @Valid @RequestBody final FileStructure updateFileStructure) throws DocumentNotFoundException {
        final FileStructure existingFileStructure = fileSystemApiStore.findById(validatePath(path))
                .orElseThrow(() -> new DocumentNotFoundException("FileStructure not found with path:" + path));
        existingFileStructure.setName(updateFileStructure.getName());
        existingFileStructure.setTimestamp(updateFileStructure.getTimestamp());
        return ResponseEntity.ok(fileSystemApiStore.saveFileStructure(existingFileStructure));
    }

    @DeleteMapping("/delete/{path}")
    public ResponseEntity<JsonSuccess> deleteFileStructure(@Valid @PathVariable final String path) {
        FileStructure fileStructure = fileSystemApiStore.findFileStructureByPath(validatePath(path));
        if (fileStructure != null) {
            fileSystemApiStore.deleteFileStructure(fileStructure);
            return ResponseEntity.ok(new JsonSuccess());
        }
        return ResponseEntity.ok(new JsonSuccess(String.format("path not found %s", path)));
    }

    @GetMapping("/printPathToFile/{pathToPrint}/{fileToPrint}")
    public ResponseEntity<PathFileToPrint> printPathToFile(@Valid @PathVariable final String pathToPrint,
            @Valid @PathVariable final String fileToPrint) {
        final PathFileToPrint pathFileToPrint = new PathFileToPrint.Builder(pathToPrint, fileToPrint).build();
        final ValidationResult validationResult = validationService.validateInput(pathFileToPrint);
        if (validationResult.isValid()) {
            final PathFileToPrint result = fileSystemApiService.printPathToFile(pathFileToPrint.getPathToPrint(),
                    pathFileToPrint.getFileToPrint());
            return new ResponseEntity<>(result, CREATED);
        } else {
            return new ResponseEntity(new JsonError().addErrorMessage(validationResult.getErrorMessage()),
                    validationResult.getHttpStatus());
        }
    }

    /** The input path parameter from a request cannot start with SLASH, but absolute paths are used
     *
     * @param path to validate
     * @return a valid path */
    @Valid
    private String validatePath(@PathVariable @Valid final String path) {
        return StringUtils.startsWith(path, SLASH) ? path : SLASH.concat(path);
    }

}
