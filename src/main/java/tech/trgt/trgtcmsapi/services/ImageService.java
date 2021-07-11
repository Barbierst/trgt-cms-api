package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.ImageDto;

import java.util.List;

public interface ImageService {
    List<ImageDto> getAllImages();

    ImageDto createImage(ImageDto imageDto);

    ImageDto getImageByUuid(String uuid);

    ImageDto updateImage(String uuid, ImageDto imageDto);

    void deleteImageByUuid(String uuid);
}
