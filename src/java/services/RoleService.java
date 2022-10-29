/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author Khanh Nguyen
 */
public class RoleService {

    public int getRoleID(String roleName) {
        if (roleName.equals("system admin")) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public List<Role> getAllRole() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAllRole();
        return roles;
    }
    
}
