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

    public List<User> getAll(String email, String firstname, String lastname, Role role) throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll(email, firstname, lastname, role);
        return users;

    }

    public void insert(String email, String firstname, String lastname, String password, Role role) throws Exception {
        User user = new User(email, firstname, lastname, password, role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, String firstname, String lastname, String password, Role role) throws Exception {
        User user = new User(email, firstname, lastname, password, role);
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
