package net.marco27.api.filesystemapi.executor.thread;

import static org.apache.commons.io.comparator.PathFileComparator.PATH_SYSTEM_COMPARATOR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            LOGGER.info(String.format("Printing %s into file %s...", this.pathToPrint, this.fileToPrint));

            fileWriter = new FileWriter(new File(this.fileToPrint));
            bufferedWriter = new BufferedWriter(fileWriter);
            printDirStructure(bufferedWriter, this.pathToPrint, this.fileToPrint);

            LOGGER.info(String.format("Printed %s into file %s...", this.pathToPrint, this.fileToPrint));
        } catch (Exception e) {
            LOGGER.error("Error! " + e.getMessage());
            throw e;
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                LOGGER.error("Error! " + e.getMessage());
                throw e;
            }
        }
        return new FileStructure.Builder(pathToPrint).build();
    }

    private void printDirStructure(BufferedWriter bufferedWriter, String pathToPrint, String fileToPrint)
            throws IOException {
        final List<File> filesInDirectorySorted = listAndSortDirectoryFiles(new File(pathToPrint));
        for (File oFile : filesInDirectorySorted) {
            if (oFile.isDirectory()) {
                bufferedWriter.write("[" + oFile.getAbsolutePath() + "]\n");
                printDirStructure(bufferedWriter, oFile.getAbsolutePath(), fileToPrint);
            } else {
                bufferedWriter.write(oFile.getAbsolutePath() + "\n");
            }
        }
    }

    private List<File> listAndSortDirectoryFiles(final File path) {
        List<File> result = new ArrayList<File>();
        if (path != null && path.isDirectory()) {
            result = Arrays.asList(path.listFiles());
            Collections.sort(result, PATH_SYSTEM_COMPARATOR);
        }
        return result;
    }
}
