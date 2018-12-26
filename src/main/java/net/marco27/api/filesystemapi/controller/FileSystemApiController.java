package net.marco27.api.filesystemapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.marco27.api.filesystemapi.domain.FileModel;
import net.marco27.api.filesystemapi.service.FileSystemService;

import javax.validation.Valid;

/** The main use case for the API is to query the filesystem
 *
 * The API needs the following endpoints:
 *
 * * GET /files – returns all the files in the filesystem */
@RestController
@RequestMapping
public class FileSystemApiController {

    @Autowired
    private FileSystemService fileSystemService;

    @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getAllFiles() {
        List<FileModel> result = Arrays.asList(fileSystemService.getFileModel());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<FileModel> getFileByName(@Valid @PathVariable String fileName) {
        FileModel result = fileSystemService.getFileModel();
        return ResponseEntity.ok(result);
    }

}
