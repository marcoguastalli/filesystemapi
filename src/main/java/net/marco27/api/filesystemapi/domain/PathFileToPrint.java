package net.marco27.api.filesystemapi.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.Setter;

@Setter
public final class PathFileToPrint implements Serializable {

    // As the GET operation doens't allows slash
    public static final String SLASH = "/";

    private String pathToPrint;
    private String fileToPrint;

    // Default Constructor used for deserialize
    public PathFileToPrint() {
    }

    private PathFileToPrint(final String pathToPrint, final String fileToPrint) {
        this.pathToPrint = pathToPrint;
        this.fileToPrint = fileToPrint;
    }

    public String getPathToPrint() {
        return pathToPrint;
    }

    public String getFileToPrint() {
        return fileToPrint;
    }

    public static class Builder {
        private String pathToPrint;
        private String fileToPrint;

        public Builder(final String pathToPrint, final String fileToPrint) {
            this.pathToPrint = StringUtils.startsWith(pathToPrint, SLASH) ? pathToPrint : SLASH.concat(pathToPrint);
            this.fileToPrint = StringUtils.startsWith(fileToPrint, SLASH) ? fileToPrint : SLASH.concat(fileToPrint);
        }

        public PathFileToPrint build() {
            return new PathFileToPrint(pathToPrint, fileToPrint);
        }

    }

    @Override
    public String toString() {
        return "{ pathToPrint: \"" + pathToPrint + "\"" +
                ", fileToPrint: \"" + fileToPrint + "\" }";
    }
}
