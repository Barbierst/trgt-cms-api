package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.PageDto;
import tech.trgt.trgtcmsapi.dtos.PageListDto;
import tech.trgt.trgtcmsapi.services.PageService;

import javax.validation.Valid;

@RestController
@RequestMapping(PageController.BASE_URL)
public class PageController {
    public static final String BASE_URL = "/api/v1/pages";

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageListDto getAllPages() {
        return new PageListDto(pageService.getAllPages());
    }

    @GetMapping("{slug}")
    @ResponseStatus(HttpStatus.OK)
    public PageDto getPage(@PathVariable String slug) {
        return pageService.getPageBySlug(slug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PageDto createNewpage(@Valid @RequestBody PageDto pageDto) {
        return pageService.createNewPage(pageDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public PageDto updatePage(@Valid @PathVariable String uuid, @RequestBody PageDto pageDto) {
        return pageService.updatePage(uuid, pageDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePage(@PathVariable String uuid) {
        pageService.deletePage(uuid);
    }
}
