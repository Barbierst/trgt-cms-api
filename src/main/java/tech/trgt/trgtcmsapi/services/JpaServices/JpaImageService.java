package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.ImageDto;
import tech.trgt.trgtcmsapi.mappers.ImageMapper;
import tech.trgt.trgtcmsapi.models.Image;
import tech.trgt.trgtcmsapi.repositories.ImageRepository;
import tech.trgt.trgtcmsapi.services.ImageService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaImageService implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public JpaImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public List<ImageDto> getAllImages() {
        return imageRepository.findAll().stream().map(imageMapper::imageToImageDto).collect(Collectors.toList());
    }

    @Override
    public ImageDto createImage(ImageDto imageDto) {
        Image image = imageMapper.imageDtoToImage(imageDto);
        image.setUuid(UUID.randomUUID().toString());

        imageRepository.save(image);

        return imageMapper.imageToImageDto(image);
    }

    @Override
    public ImageDto getImageByUuid(String uuid) {
        Image image = imageRepository.findByUuid(uuid);

        if (image == null) {
            throw new ResourceNotFoundException();
        }

        return imageMapper.imageToImageDto(image);
    }

    @Override
    public ImageDto updateImage(String uuid, ImageDto imageDto) {
        Image original = imageRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Image image = imageMapper.imageDtoToImage(imageDto);
        image.setUuid(original.getUuid());
        image.setId(original.getId());

        return imageMapper.imageToImageDto(image);
    }

    @Override
    public void deleteImageByUuid(String uuid) {
        imageRepository.deleteByUuid(uuid);
    }
}
