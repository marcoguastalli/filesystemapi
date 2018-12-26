package net.marco27.api.filesystemapi.service;

import net.marco27.api.filesystemapi.domain.FileModel;
import org.springframework.stereotype.Service;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Override
    public FileModel getFileModel() {
        final String path = "/opt/opt.txt";
        final String name = "opt.txt";
        final String ext = "txt";
        return new FileModel.FileModelBuilder(path, name, ext).build();
    }
}
