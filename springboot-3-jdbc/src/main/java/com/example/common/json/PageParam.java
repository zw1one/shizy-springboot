package com.example.common.json;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam<T> {

    private static final Integer DEFAULT_PAGE = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    @Schema(description = "当前页", example = "1")
    private Integer current = DEFAULT_PAGE;

    @Schema(description = "每页的data条数", example = "10")
    private Integer size = DEFAULT_PAGE_SIZE;

    /**
     * 获取mybatis plus的Page分页查询插件
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page
     */
    @Hidden
    public Page<T> getQueryPage() {
        return new Page<T>(current, size);
    }

}


























