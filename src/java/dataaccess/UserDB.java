/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.*;
import dataaccess.RoleDB;

/**
 *
 * @author Khanh Nguyen
 */
public class UserDB {
    
    RoleDB roleDB = new RoleDB();

    public List<User> getUser() throws Exception {
        List<User> notes = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;

        String sql = "SELECT email, first_name, last_name, role * FROM user";

        try {
            ps = con.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                String email = result.getString(1);
                String fname = result.getString(2);
                String lname = result.getString(3);
                Role role = roleDB.getRole(result.getInt(4)) ;
                User note = new User(email, fname, lname, role);
                notes.add(note);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return notes;
    }

}
