package tech.trgt.trgtcmsapi.services;

import tech.trgt.trgtcmsapi.dtos.BlogDto;
import tech.trgt.trgtcmsapi.dtos.BlogDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs();

    BlogDto getBlogByUuid(String uuid);

    BlogDto createNewBlog(BlogDto blogDto);

    BlogDto updateBlog(String uuid, BlogDto blogDto);

    BlogDto patchBlog(String uuid, BlogDto blogDto);

    void deleteBlogByUuid(String uuid);
}
