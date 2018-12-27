package net.marco27.api.filesystemapi.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.marco27.api.base.domain.JsonError;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.service.FileSystemApiService;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;
import net.marco27.api.filesystemapi.validation.service.ValidationService;

/** The main use case for the API is to query the filesystem
 * <p>
 * The API needs the following endpoints:
 * <p>
 * GET /files – returns all the files in the filesystem */
@RestController
@RequestMapping
public class FileSystemApiController {

    @Autowired
    private FileSystemApiService fileSystemApiService;
    @Autowired
    private ValidationService validationService;

    @GetMapping("/printPathToFile/{pathToPrint}/{fileToPrint}")
    public ResponseEntity<PathFileToPrint> printPathToFile(@PathVariable final String pathToPrint, @PathVariable final String fileToPrint) {
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

    @GetMapping("/getPathStructure/{path}")
    public ResponseEntity<FileStructure> getPathStructure(@PathVariable final String path) {
        final FileStructure result = fileSystemApiService.createFileModel(path);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileStructure>> getAllFiles() {
        List<FileStructure> result = Arrays.asList(removeThis());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<FileStructure> getFileByName(@Valid @PathVariable String fileName) {
        FileStructure result = removeThis();
        return ResponseEntity.ok(result);
    }

    private FileStructure removeThis() {
        String path = "path";
        String name = "name";
        String ext = "ext";
        return new FileStructure.Builder(path, name, ext).withTimestamp(null).isDirectory(false).addChildren(Collections.EMPTY_LIST)
                .build();
    }
}
