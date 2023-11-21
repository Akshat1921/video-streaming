package com.fileupload.demo.service;

import com.fileupload.demo.model.FileData;
import com.fileupload.demo.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UploadService {
    @Autowired
    private StorageRepository storageRepository;

    private final String FOLDER_PATH = "C:/Users/User/Downloads/VideoUpload/";
    public String uploadFile(MultipartFile file) throws IOException{
        String filePath = FOLDER_PATH+file.getOriginalFilename();
        FileData fileData = storageRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType()).filePath(filePath).build());
        file.transferTo(new File(filePath));
        if(fileData!=null){
            return "file uploaded successfully: "+filePath;
        }
        return null;
    }

    public byte[] downloadFile(String filename) throws IOException{
        Optional<FileData> fileData = storageRepository.findByName(filename);
        String filePath = fileData.get().getFilePath();
        System.out.println(filePath);
        byte[] files = Files.readAllBytes(new File(filePath).toPath());
        return files;
    }

}
