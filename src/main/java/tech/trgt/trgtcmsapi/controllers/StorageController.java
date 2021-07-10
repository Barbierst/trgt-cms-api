package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.trgt.trgtcmsapi.services.StorageService;

@RestController
@RequestMapping(StorageController.BASE_URL)
public class StorageController {
    public static final String BASE_URL = "/api/v1/files";

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.uploadFile(file);
    }
}
