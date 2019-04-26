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

//    @ApiModelProperty("当前页")
    private Integer pageNo;

//    @ApiModelProperty("每页条数")
    private Integer pageSize;

//    @ApiModelProperty("总条数")
    private Long total;

//    @ApiModelProperty("总页数")
    private Long pages;

//    @ApiModelProperty("数据集")
    private T list;
}
