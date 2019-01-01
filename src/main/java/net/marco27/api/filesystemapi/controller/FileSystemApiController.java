package net.marco27.api.filesystemapi.controller;

import static net.marco27.api.filesystemapi.domain.PathFileToPrint.SLASH;
import static org.springframework.http.HttpStatus.CREATED;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import net.marco27.api.filesystemapi.store.FileSystemApiStore;
import net.marco27.api.filesystemapi.validation.model.ValidationResult;
import net.marco27.api.filesystemapi.validation.service.ValidationService;

/** The main use case for the API is to read the filesystem */
@RestController
@RequestMapping
public class FileSystemApiController {

    @Autowired
    private FileSystemApiService fileSystemApiService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FileSystemApiStore fileSystemApiStore;

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

    @GetMapping("/getPathStructure/{path}")
    public ResponseEntity<FileStructure> getPathStructure(@Valid @PathVariable final String path) {
        // the input parameter cannot start with SLASH
        final FileStructure result = fileSystemApiService
                .createFileStructure(StringUtils.startsWith(path, SLASH) ? path : SLASH.concat(path));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/storePathStructure/{path}")
    public ResponseEntity<FileStructure> storePathStructure(@Valid @PathVariable final String path) {
        FileStructure result = fileSystemApiStore.loadFileStructure(path);
        if (result == null) {
            result = fileSystemApiService.createFileStructure(StringUtils.startsWith(path, SLASH) ? path : SLASH.concat(path));
            fileSystemApiStore.storeFileStructure(result);
        }
        return ResponseEntity.ok(result);
    }

}
