package com.example.common.json;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> {

    @Schema(description = "查询列表")
    private List<T> records;

    @Schema(description = "当前页", example = "1")
    private long current;

    @Schema(description = "每页的data条数", example = "10")
    private long size;

    @Schema(description = "data总数", example = "12")
    private long total;

    @Schema(description = "data总页数", example = "2")
    private long pages;

}


























