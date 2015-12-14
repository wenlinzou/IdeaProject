package com.bean;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private String showpassword;
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getShowpassword() {
        return showpassword;
    }
    public void setShowpassword(String showpassword) {
        this.showpassword = showpassword;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

}
