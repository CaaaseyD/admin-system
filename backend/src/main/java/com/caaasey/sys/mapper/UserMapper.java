package com.caaasey.sys.mapper;

import com.caaasey.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CaaaseyD
 * @since 2023-03-22
 */
public interface UserMapper extends BaseMapper<User> {

    default List<String> getRoleNamesByUserId(Integer id) {
        return null;
    }
}
