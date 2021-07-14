package tech.trgt.trgtcmsapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.trgt.trgtcmsapi.dtos.BlockDto;
import tech.trgt.trgtcmsapi.dtos.PageDto;
import tech.trgt.trgtcmsapi.models.Block;
import tech.trgt.trgtcmsapi.models.Page;

import java.util.List;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    Page pageDtoToPage(PageDto pageDto);
    PageDto pageToPageDto(Page page);

    Block blockDtoToBlock(BlockDto blockDto);
    BlockDto blockToBlockDto(Block block);

    @Mapping(target = "page")
    List<BlockDto> blocksToBlockDtos(List<Block> blocks);

    @Mapping(target = "page")
    List<Block> blockDtosToBlocks(List<BlockDto> blockDtos);

}
