package com.fileupload.demo.repository;

import com.fileupload.demo.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findByName(String filename);
}
