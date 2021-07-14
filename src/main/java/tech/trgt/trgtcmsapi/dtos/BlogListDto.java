package tech.trgt.trgtcmsapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class BlogListDto {
    private List<BlogDto> blogs;
}
