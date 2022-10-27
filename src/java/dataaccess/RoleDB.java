/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.*;

/**
 *
 * @author Khanh Nguyen
 */
public class RoleDB {

    public Role getRole(int roleID) throws Exception {
        Role role = null;
        
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;
        
        String sql = "SELECT * FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleID);
            result = ps.executeQuery();
            if (result.next()) {
                String roleName = result.getString(2);
                role = new Role(roleID, roleName);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return role;
    }
    
    public List<Role> getAllRole() throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet result = null;

        String sql = "SELECT * FROM role";

        try {
            ps = con.prepareStatement(sql);
            result = ps.executeQuery();
            while (result.next()) {
                int roleID = result.getInt(1);
                String roleName = result.getString(2);
                Role role = new Role(roleID, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(result);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }

}
