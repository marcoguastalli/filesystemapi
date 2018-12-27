package net.marco27.api.filesystemapi.executor.thread;

import static org.apache.commons.io.comparator.PathFileComparator.PATH_SYSTEM_COMPARATOR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.marco27.api.filesystemapi.domain.FileStructure;

public class PrintFileSystemThread implements Callable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintFileSystemThread.class);

    private final String pathToPrint;
    private final String fileToPrint;

    public PrintFileSystemThread(final String pathToPrint, final String fileToPrint) {
        this.pathToPrint = pathToPrint;
        this.fileToPrint = fileToPrint;
    }

    @Override
    public FileStructure call() throws Exception {
        BufferedWriter bufferedWriter = null;
        try {
            LOGGER.info(String.format("Printing %s into file %s...", this.pathToPrint, this.fileToPrint));
            Charset charset = Charset.forName("UTF-8");
            Path pathToWrite = Paths.get(fileToPrint);

            bufferedWriter = Files.newBufferedWriter(pathToWrite, charset);
            printDirStructure(bufferedWriter, this.pathToPrint);

            LOGGER.info(String.format("Printed %s into file %s...", this.pathToPrint, this.fileToPrint));
            return new FileStructure.Builder(pathToPrint).build();
        } catch (Exception e) {
            LOGGER.error("Error! " + e.getMessage());
            throw e;
        } finally {
            bufferedWriter.close();
        }
    }

    private void printDirStructure(BufferedWriter bufferedWriter, String pathToPrint)
            throws IOException {
        final List<File> filesInDirectorySorted = listAndSortDirectoryFiles(Paths.get(pathToPrint).toFile());
        for (File oFile : filesInDirectorySorted) {
            if (oFile.isDirectory()) {
                bufferedWriter.write("[" + oFile.getAbsolutePath() + "]\n");
                printDirStructure(bufferedWriter, oFile.getAbsolutePath());
            } else {
                bufferedWriter.write(oFile.getAbsolutePath() + "\n");
            }
        }
    }

    private List<File> listAndSortDirectoryFiles(final File path) {
        List<File> result = new ArrayList<File>();
        if (path != null && path.isDirectory()) {
            result = Arrays.asList(path.listFiles());
            result.sort(PATH_SYSTEM_COMPARATOR);
        }
        return result;
    }
}
