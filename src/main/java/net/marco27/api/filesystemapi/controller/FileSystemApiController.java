package net.marco27.api.filesystemapi.controller;

import net.marco27.api.filesystemapi.domain.FileModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * The main use case for the API is to query the filesystem
 * <p>
 * The API needs the following endpoints:
 * <p>
 * GET /files – returns all the files in the filesystem
 */
@RestController
@RequestMapping
public class FileSystemApiController {

    @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getAllFiles() {
        List<FileModel> result = Arrays.asList(new FileModel());
        return ResponseEntity.ok(result);
    }

}
