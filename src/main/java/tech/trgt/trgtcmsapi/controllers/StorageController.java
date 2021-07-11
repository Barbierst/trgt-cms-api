package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.trgt.trgtcmsapi.dtos.ImageDto;
import tech.trgt.trgtcmsapi.dtos.ImageListDto;
import tech.trgt.trgtcmsapi.services.ImageService;
import tech.trgt.trgtcmsapi.services.StorageService;

@RestController
@RequestMapping(StorageController.BASE_URL)
public class StorageController {
    public static final String BASE_URL = "/api/v1/files";

    private final StorageService storageService;
    private final ImageService imageService;

    public StorageController(StorageService storageService, ImageService imageService) {
        this.storageService = storageService;
        this.imageService = imageService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ImageListDto getAllImages() {
        return new ImageListDto(imageService.getAllImages());
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDto getImage(@PathVariable String uuid) {
        return imageService.getImageByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ImageDto uploadFile(@RequestParam("file") MultipartFile file) {
        String url = storageService.uploadFile(file);
        ImageDto imageDto = new ImageDto();
        imageDto.setTitle(file.getOriginalFilename());
        imageDto.setUrl(url);

        return imageService.createImage(imageDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ImageDto updateImage(@PathVariable String uuid, @RequestBody ImageDto imageDto) {
        return imageService.updateImage(uuid, imageDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteImage(@PathVariable String uuid) {
        imageService.deleteImageByUuid(uuid);
    }
}
