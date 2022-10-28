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

    public String fieldValidation(String email, String firstname, String lastname, String password) {
        if (email == null || email.equals("") || firstname == null || firstname.equals("") || lastname == null || lastname.equals("") || password == null || password.equals("")) {
            return "All fields are required.";
        }

        return null;
    }

    public boolean userValidation(List<User> users, String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
