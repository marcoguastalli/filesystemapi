package net.marco27.api.filesystemapi.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class FileModel implements Serializable {

    @NotNull(message = "File path can not be null!")
    private String path;
    @NotNull(message = "File name can not be null!")
    private String name;
    @NotNull(message = "File extension can not be null!")
    private String ext;
    private String timestamp;

    private FileModel() {
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getExt() {
        return ext;
    }

    public String getTimestamp() {
        return timestamp;
    }

    private FileModel(final FileModelBuilder builder) {
        this.path = builder.path;
        this.name = builder.name;
        this.ext = builder.ext;
        this.timestamp = builder.timestamp;
    }

    public static class FileModelBuilder {
        private String path;
        private String name;
        private String ext;
        private String timestamp;

        public FileModelBuilder(final String path, final String name, final String ext) {
            this.path = path;
            this.name = name;
            this.ext = ext;
        }

        public FileModel build() {
            return new FileModel(this);
        }

    }

}
