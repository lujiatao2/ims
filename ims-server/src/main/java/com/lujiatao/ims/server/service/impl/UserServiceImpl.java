package com.lujiatao.ims.server.service.impl;

import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.server.repository.UserDAO;
import com.lujiatao.ims.service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean add(User user) {
        return userDAO.insert(user);
    }

    @Override
    public boolean modify(User user) {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteByUsername(String username) {
        return userDAO.deleteByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userDAO.selectByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userDAO.selectAll();
    }

}
