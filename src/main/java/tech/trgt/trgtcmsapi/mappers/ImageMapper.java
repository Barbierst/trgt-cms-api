package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.ImageDto;
import tech.trgt.trgtcmsapi.models.Image;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto imageToImageDto(Image image);
    Image imageDtoToImage(ImageDto imageDto);
}
