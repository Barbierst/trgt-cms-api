package tech.trgt.trgtcmsapi.services.UtilServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.SeoDto;
import tech.trgt.trgtcmsapi.mappers.SeoMapper;
import tech.trgt.trgtcmsapi.models.Seo;
import tech.trgt.trgtcmsapi.services.SeoService;

import java.util.UUID;

@Service
public class UtilSeoService implements SeoService {

    private final SeoMapper seoMapper;

    public UtilSeoService(SeoMapper seoMapper) {
        this.seoMapper = seoMapper;
    }

    @Override
    public Seo createSeo(SeoDto seoDto) {
        Seo seo = seoMapper.seoDtoToSeo(seoDto);
        seo.setUuid(UUID.randomUUID().toString());

        return seo;
    }

    @Override
    public Seo patchSeo(Seo seo, SeoDto seoDto) {
        if (seoDto.getTitle() != null) {
            seo.setTitle(seoDto.getTitle());
        }
        if (seoDto.getDescription() != null) {
            seo.setDescription(seoDto.getDescription());
        }

        return seo;
    }
}
