package com.github.mall.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础分页vo
 *
 * @author hpc
 * @date 2019/4/27
 */
@Data
public class PageInVo implements Serializable {

    private static final long serialVersionUID = -2193105092392981877L;

//    @ApiModelProperty("当前第几页")
    private Integer pageNo = 1;

//    @ApiModelProperty("每页条数")
    private Integer pageSize = 10;


}