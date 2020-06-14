package net.marco27.api.filesystemapi.service;

import static net.marco27.api.util.TestUtil.getJsonFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;
import net.marco27.api.filesystemapi.domain.PathFileToPrint;
import net.marco27.api.filesystemapi.executor.PrintFileSystemExecutor;
import net.marco27.api.filesystemapi.executor.thread.CreateFileStructureThread;

public class FileSystemApiServiceTest {

    private static final String TEST_JSON_FILE = "/pathprinted.json";
    private static final String PATH_ROOT_HOME = "/home/root";
    private static final String PATH_TO_PRINT = "/home/root/Pictures";
    private static final String FILE_TO_PRINT = "/home/root/Documents/pictures.json";

    private FileSystemApiService fileSystemApiService;
    @Mock
    private PrintFileSystemExecutor printFileSystemExecutor;
    @Mock
    private ApplicationConfiguration applicationConfiguration;

    private PathFileToPrint pathFileToPrint;

    @BeforeEach
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        fileSystemApiService = new FileSystemApiServiceImpl(printFileSystemExecutor);
        final File file = new File(this.getClass().getResource(TEST_JSON_FILE).getFile());
        pathFileToPrint = getJsonFromFile(file, PathFileToPrint.class);
    }

    @Test
    public void testPrintPathToFile() {
        final PathFileToPrint result = fileSystemApiService.printPathToFile(PATH_TO_PRINT, FILE_TO_PRINT);
        assertEquals(pathFileToPrint.toString(), result.toString());
    }

    @Test
    public void testCreateFileStructure() throws ExecutionException, InterruptedException {
        when(printFileSystemExecutor.execute(any(CreateFileStructureThread.class)))
                .thenReturn(FileStructure.builder().path(PATH_ROOT_HOME).build());
        final FileStructure result = fileSystemApiService.createFileStructure(PATH_ROOT_HOME);
        assertEquals(PATH_ROOT_HOME, result.getPath());
    }
}
