package com.lujiatao.ims.common.entity;

import com.lujiatao.ims.common.constant.Role;

public class User extends BasePO {

    private String username;
    private String password;
    private Role role;
    private boolean isActive;

    public User() {
    }

    public User(String username, String password, Role role, boolean isActive) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
