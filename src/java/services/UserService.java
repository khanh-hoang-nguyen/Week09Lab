/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.*;
import dataaccess.*;

/**
 *
 * @author Khanh Nguyen
 */
public class UserService {

    public List<User> getAllUser() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAllUser();
        return users;
    }
    
    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
    }
    
    public void insert(String email, String fname, String lname, String password, Role role) throws Exception {
        User user = new User(email, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, String fname, String lname, String password, Role role) throws Exception {
        User user = new User(email, fname, lname, password, role);
        UserDB userDB = new UserDB();
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
 
    
}
