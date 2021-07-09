package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.SeoDto;
import tech.trgt.trgtcmsapi.models.Seo;

public interface SeoService {
    Seo createSeo(SeoDto seoDto);
    Seo patchSeo(Seo seo, SeoDto seoDto);
}
