package com.example.hogwartsmini.common;


import com.example.hogwartsmini.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求对象
 * @param <Dto>
 */
@ApiModel(value = "列表查询的分页参数", description = "请求参数类")
@Data
public class PageTableRequest<Dto extends BaseDto> implements Serializable {

    private static final long servialVersionUID = -7472879865481412343L;

    @ApiModelProperty(value = "页码", required = true, example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数据", required = true, example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "特定查询参数", required = true, example = "status=1")
    private Dto params;
}
