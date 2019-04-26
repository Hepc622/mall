package com.github.mall.mapper;

import com.github.mall.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HPC
 * @since 2019-04-26
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
