package net.marco27.api.filesystemapi.util;

import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.createFileStructure;
import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.getFileExtension;
import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.isDirectory;
import static net.marco27.api.filesystemapi.util.FileSystemApiUtil.listAndSortDirectoryFiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.marco27.api.filesystemapi.domain.FileStructure;

public class FileSystemApiUtilTest {

    private static final String PATH = "/Users/marcoguastalli/temp";

    @Test
    public void testListAndSortDirectoryFiles() {
        assertEquals(new ArrayList<File>(), listAndSortDirectoryFiles(new File("non existing path")));
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("DS_Store", getFileExtension(".DS_Store"));
        assertEquals("jpg", getFileExtension("image.jpg"));
        assertEquals("", getFileExtension("images"));
    }

    @Test
    public void testIsDirectory() {
        assertFalse(isDirectory(".DS_Store"));
        assertTrue(isDirectory(PATH));
    }

    @Test
    public void testCreateFileStructure() throws IOException {
        final FileStructure expected = FileStructure.builder().path(PATH).build();
        final FileStructure result = createFileStructure(PATH);
        assertNotEquals(expected, result);
    }

}
