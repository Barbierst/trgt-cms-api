package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.BlogDto;
import tech.trgt.trgtcmsapi.mappers.BlogMapper;
import tech.trgt.trgtcmsapi.models.Blog;
import tech.trgt.trgtcmsapi.models.Image;
import tech.trgt.trgtcmsapi.models.Seo;
import tech.trgt.trgtcmsapi.repositories.BlogRepository;
import tech.trgt.trgtcmsapi.repositories.ImageRepository;
import tech.trgt.trgtcmsapi.services.BlogService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;
import tech.trgt.trgtcmsapi.services.SeoService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JpaBlogService implements BlogService {

    private final BlogMapper blogMapper;
    private final BlogRepository blogRepository;
    private final ImageRepository imageRepository;
    private final SeoService seoService;

    public JpaBlogService(BlogMapper blogMapper, BlogRepository blogRepository, ImageRepository imageRepository, SeoService seoService) {
        this.blogMapper = blogMapper;
        this.blogRepository = blogRepository;
        this.imageRepository = imageRepository;
        this.seoService = seoService;
    }

    @Override
    public List<BlogDto> getAllBlogs() {
        return blogRepository.findAll().stream().map(blogMapper::blogToBlogDto).collect(Collectors.toList());
    }

    @Override
    public BlogDto getBlogByUuid(String uuid) {
        Blog blog = blogRepository.findByUuid(uuid);

        if (blog == null) {
            throw new ResourceNotFoundException();
        }

        return blogMapper.blogToBlogDto(blog);
    }

    @Override
    public BlogDto createNewBlog(BlogDto blogDto) {
        Blog blog = blogMapper.blogDtoToBlog(blogDto);
        blog.setUuid(UUID.randomUUID().toString());

        if (blogDto.getSeo() != null) {
            Seo seo = seoService.createSeo(blogDto.getSeo());
            blog.setSeo(seo);
        }

        if (blogDto.getImage() != null) {
            Image image = imageRepository.findByUuid(blogDto.getImage().getUuid());
            blog.setImage(image);
        }

        return saveAndReturnDto(blog);
    }

    @Override
    @Transactional
    public BlogDto updateBlog(String uuid, BlogDto blogDto) {
        Blog original = blogRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Blog blog = blogMapper.blogDtoToBlog(blogDto);
        blog.setUuid(original.getUuid());
        blog.setId(original.getId());

        setRelations(blog, blogDto);

        return saveAndReturnDto(blog);
    }

    @Override
    @Transactional
    public BlogDto patchBlog(String uuid, BlogDto blogDto) {
        Blog blog = blogRepository.findByUuid(uuid);

        if (blog == null) {
            throw new ResourceNotFoundException();
        }

        if (blogDto.getSlug() != null) {
            blog.setSlug(blogDto.getSlug());
        }

        if (blogDto.getTitle() != null) {
            blog.setTitle(blogDto.getTitle());
        }

        if (blogDto.getContent() != null) {
            blog.setContent(blogDto.getContent());
        }

        setRelations(blog, blogDto);

        return saveAndReturnDto(blog);
    }

    @Override
    @Transactional
    public void deleteBlogByUuid(String uuid) {
        blogRepository.deleteByUuid(uuid);
    }

    private BlogDto saveAndReturnDto(Blog blog) {
        Blog savedBlog= blogRepository.save(blog);

        return blogMapper.blogToBlogDto(savedBlog);
    }

    private void setRelations(Blog blog, BlogDto blogDto) {
        if (blogDto.getSeo() != null) {
            Seo seo = createOrPatchSeo(blogDto, blog);
            blog.setSeo(seo);
        }

        if (blogDto.getImage() != null) {
            Image image = imageRepository.findByUuid(blogDto.getImage().getUuid());
            blog.setImage(image);
        }
    }

    private Seo createOrPatchSeo(BlogDto blogDto, Blog blog) {
        Seo originalSeo = blog.getSeo();

        Seo seo;
        if (originalSeo == null) {
            seo = seoService.createSeo(blogDto.getSeo());
        } else {
            seo = seoService.patchSeo(originalSeo, blogDto.getSeo());
        }

        return seo;
    }
}
