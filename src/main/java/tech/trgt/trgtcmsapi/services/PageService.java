package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.PageDto;

import java.util.List;

public interface PageService {
    List<PageDto> getAllPages();
    PageDto getPageBySlug(String slug);
    PageDto createNewPage(PageDto pageDto);
    PageDto updatePage(String uuid, PageDto pageDto);
    void deletePage(String uuid);
}
