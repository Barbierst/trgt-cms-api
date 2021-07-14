package tech.trgt.trgtcmsapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PageListDto {
    private List<PageDto> pages;
}
