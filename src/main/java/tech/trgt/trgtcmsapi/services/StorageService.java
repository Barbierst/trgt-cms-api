package tech.trgt.trgtcmsapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void uploadFile(MultipartFile file);
}
