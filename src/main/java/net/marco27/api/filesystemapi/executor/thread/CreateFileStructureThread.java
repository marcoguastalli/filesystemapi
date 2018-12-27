package net.marco27.api.filesystemapi.executor.thread;

import java.util.concurrent.Callable;

import net.marco27.api.filesystemapi.domain.FileStructure;

public class CreateFileStructureThread implements Callable {

    private final String path;

    public CreateFileStructureThread(final String path) {
        this.path = path;
    }

    @Override
    public FileStructure call() {
        return new FileStructure.Builder(this.path).build();
    }

}
