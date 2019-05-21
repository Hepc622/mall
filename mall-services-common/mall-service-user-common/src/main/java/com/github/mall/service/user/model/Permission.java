package com.github.mall.service.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author HPC
 * @since 2019-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限描述
     */
    private String description;

    /**
     * url路径
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTm;

    /**
     * 更新时间
     */
    private Date updateTm;

    /**
     * 创建者id
     */
    private Long createId;

    /**
     * 更新者id
     */
    private Long updateId;


}
