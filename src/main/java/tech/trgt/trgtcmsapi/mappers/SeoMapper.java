package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.SeoDto;
import tech.trgt.trgtcmsapi.models.Seo;

@Mapper
public interface SeoMapper {
    SeoMapper INSTANCE = Mappers.getMapper(SeoMapper.class);

    Seo seoDtoToSeo(SeoDto seoDto);
    SeoDto seoToSeoDto(Seo seo);
}
