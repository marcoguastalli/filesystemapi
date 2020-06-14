package net.marco27.api.filesystemapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** A file like:
 *
 * {
 *   path: "/Users/marcoguastalli/temp",
 *   name: "temp",
 *   ext: null,
 *   timestamp: "2019-02-19 11:35:56",
 *   children: [
 *     {
 *       path: "/Users/marcoguastalli/temp/.DS_Store",
 *       name: ".DS_Store",
 *       ext: "DS_Store",
 *       timestamp: "2019-02-12 09:01:05",
 *       children: [
 *
 *       ],
 *       directory: false
 *     },
 *     {
 *       path: "/Users/marcoguastalli/temp/docker.txt",
 *       name: "docker.txt",
 *       ext: "txt",
 *       timestamp: "2019-02-19 11:35:56",
 *       children: [
 *
 *       ],
 *       directory: false
 *     }
 *   ],
 *   directory: true
 * }
 *
 *
 *
 * */
@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class FileStructure implements Serializable {

    @Id
    @NotNull(message = "File path can not be null!")
    private String path;
    @NotNull(message = "File name can not be null!")
    private String name;
    private String ext;
    private String timestamp;
    private boolean isDirectory;
    @OneToMany(targetEntity = FileStructure.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<FileStructure> children;

}
