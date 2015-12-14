package com.dao;

import com.bean.SEmail;
import com.bean.User;

public interface UserDao {
    public void add(User user);
    public User find(String username, String password);
    public boolean find(String username);
    public boolean sendEmail(SEmail semail);
}
