package com.lujiatao.ims.server.repository;

import com.lujiatao.ims.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    boolean insert(User user);

    boolean update(User user);

    boolean deleteByUsername(String username);

    User selectByUsername(String username);

    List<User> selectAll();

}
