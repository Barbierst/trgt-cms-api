package tech.trgt.trgtcmsapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.trgt.trgtcmsapi.dtos.BlogDto;
import tech.trgt.trgtcmsapi.dtos.BlogListDto;
import tech.trgt.trgtcmsapi.services.BlogService;

import javax.validation.Valid;

@RestController
@RequestMapping(BlogController.BASE_URL)
public class BlogController {
    public static final String BASE_URL = "/api/v1/blogs";

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BlogListDto getAllBlogs() {
        return new BlogListDto(blogService.getAllBlogs());
    }

    @GetMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDto getBlogUuid(@PathVariable String uuid) {
        return blogService.getBlogByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BlogDto createNewBlog(@Valid @RequestBody BlogDto blogDto) {
        return blogService.createNewBlog(blogDto);
    }

    @PutMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDto updateBlog(@Valid @PathVariable String uuid, @RequestBody BlogDto blogDto) {
        return blogService.updateBlog(uuid, blogDto);
    }

    @PatchMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDto patchBlog(@PathVariable String uuid, @RequestBody BlogDto blogDto) {
        return blogService.patchBlog(uuid, blogDto);
    }

    @DeleteMapping("{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBlog(@PathVariable String uuid) {
        blogService.deleteBlogByUuid(uuid);
    }
}
