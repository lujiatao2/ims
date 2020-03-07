package com.lujiatao.ims.service;

import com.lujiatao.ims.common.entity.User;

import java.util.List;

public interface UserService {

    boolean add(User user);

    boolean modify(User user);

    boolean deleteByUsername(String username);

    User getByUsername(String username);

    List<User> getAll();

}
