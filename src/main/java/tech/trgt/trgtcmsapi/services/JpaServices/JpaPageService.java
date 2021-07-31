package tech.trgt.trgtcmsapi.services.JpaServices;

import org.springframework.stereotype.Service;
import tech.trgt.trgtcmsapi.dtos.BlogDto;
import tech.trgt.trgtcmsapi.dtos.PageDto;
import tech.trgt.trgtcmsapi.mappers.PageMapper;
import tech.trgt.trgtcmsapi.models.*;
import tech.trgt.trgtcmsapi.repositories.BlockRepository;
import tech.trgt.trgtcmsapi.repositories.ImageRepository;
import tech.trgt.trgtcmsapi.repositories.PageRepository;
import tech.trgt.trgtcmsapi.services.PageService;
import tech.trgt.trgtcmsapi.services.ResourceNotFoundException;
import tech.trgt.trgtcmsapi.services.SeoService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JpaPageService implements PageService {

    private final PageMapper pageMapper;
    private final PageRepository pageRepository;
    private final BlockRepository blockRepository;
    private final ImageRepository imageRepository;
    private final SeoService seoService;

    public JpaPageService(PageMapper pageMapper, PageRepository pageRepository, BlockRepository blockRepository, ImageRepository imageRepository, SeoService seoService) {
        this.pageMapper = pageMapper;
        this.pageRepository = pageRepository;
        this.blockRepository = blockRepository;
        this.imageRepository = imageRepository;
        this.seoService = seoService;
    }

    @Override
    public List<PageDto> getAllPages() {
        return pageRepository.findAll().stream().map(pageMapper::pageToPageDto).collect(Collectors.toList());
    }

    @Override
    public PageDto getPageBySlug(String slug) {
        Page page = pageRepository.findBySlug(slug);

        if (page == null) {
            throw new ResourceNotFoundException();
        }

        return pageMapper.pageToPageDto(page);
    }

    @Override
    public PageDto createNewPage(PageDto pageDto) {
        Page page = pageMapper.pageDtoToPage(pageDto);
        page.setUuid(UUID.randomUUID().toString());

        if (pageDto.getSeo() != null) {
            Seo seo = seoService.createSeo(pageDto.getSeo());
            page.setSeo(seo);
        }

        if (pageDto.getImage() != null) {
            Image image = imageRepository.findByUuid(pageDto.getImage().getUuid());
            page.setImage(image);
        }

        List<Block> blocks = createOrUpdateBlocks(pageDto, page);
        page.setBlocks((Set<Block>) blocks);

        return saveAndReturnDto(page);
    }

    @Override
    @Transactional
    public PageDto updatePage(String uuid, PageDto pageDto) {
        Page original = pageRepository.findByUuid(uuid);

        if (original == null) {
            throw new ResourceNotFoundException();
        }

        Page page = pageMapper.pageDtoToPage(pageDto);
        page.setUuid(original.getUuid());
        page.setId(original.getId());

        if (pageDto.getSeo() != null) {
            Seo seo = createOrPatchSeo(page, pageDto);
            page.setSeo(seo);
        }

        if (pageDto.getImage() != null) {
            Image image = imageRepository.findByUuid(pageDto.getImage().getUuid());
            page.setImage(image);
        }

        List<Block> blocks = createOrUpdateBlocks(pageDto, page);
        page.setBlocks((Set<Block>) blocks);

        return saveAndReturnDto(page);
    }

    @Override
    @Transactional
    public void deletePage(String uuid) {
        pageRepository.deleteByUuid(uuid);
    }

    private Seo createOrPatchSeo(Page page, PageDto pageDto) {
        Seo originalSeo = page.getSeo();

        Seo seo;
        if (originalSeo == null) {
            seo = seoService.createSeo(pageDto.getSeo());
        } else {
            seo = seoService.patchSeo(originalSeo, pageDto.getSeo());
        }

        return seo;
    }

    private List<Block> createOrUpdateBlocks(PageDto pageDto, Page page) {
        List<Block> blocks = new ArrayList<>();

        pageDto.getBlocks().forEach(blockDto -> {
            Block block;
            if (blockDto.getUuid() != null) {
                Block originalBlock = blockRepository.findByUuid(blockDto.getUuid());
                block = pageMapper.blockDtoToBlock(blockDto);
                block.setUuid(originalBlock.getUuid());
                block.setId(originalBlock.getId());
            } else {
                block = pageMapper.blockDtoToBlock(blockDto);
                block.setUuid(UUID.randomUUID().toString());
            }

            block.setPage(page);

            if (blockDto.getImageDto() != null) {
                Image image = imageRepository.findByUuid(blockDto.getImageDto().getUuid());
                block.setImage(image);
            }

            blocks.add(block);
        });

        return blocks;
    }

    private PageDto saveAndReturnDto(Page page) {
        Page savedPage = pageRepository.save(page);

        return pageMapper.pageToPageDto(savedPage);
    }
}
