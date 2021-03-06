package com.example.hogwartsmini.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应对象
 * @param <T>
 */
@Data
public class PageTableResponse<T> implements Serializable {

    private static final long servialVersionUID = -7472879865434342343L;

    private Integer recordsTotal;
    private List<T> data;
}
