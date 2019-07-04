package com.github.mall.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页基础数据返回Vo
 *
 * @author hpc
 * @date 2019/4/27
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageOutVo<T> implements Serializable{
    private static final long serialVersionUID = -8201439966090055097L;

    /**当前页数*/
    private Integer pageNo;

    /**页面数据大小*/
    private Integer pageSize;

    /**总条数*/
    private Long total;

    /**总页数*/
    private Long pages;

    /**返回的数据*/
    private T list;
}
