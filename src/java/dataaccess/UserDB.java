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

/**
 *
 * @author Khanh Nguyen
 */
public class UserDB {

    /**
     * Retrieve all users information except password
     *
     * @return
     * @throws Exception
     */
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        ResultSet rs = null;

        String sql = "SELECT user.email, user.first_name, user.last_name, role.role_name FROM user, role where user.role = role.role_id";

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString("email");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String role = rs.getString("role_name");
                User user = new User(email, firstname, lastname, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            cp.freeConnection(con);
        }
        return users;
    }

    /**
     * Create new user
     *
     * @param user
     * @throws Exception
     */
    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;

        String sql = "INSERT INTO user (email, first_name, last_name, password, role VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    /**
     * Edit the user
     *
     * @param user
     * @throws Exception
     */
    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;

        String sql = "UPDATE user SET email=?, first_name=?, last_name=?, password=?, role=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole_id());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

    }

    /**
     * Delete the user
     *
     * @param user
     * @throws Exception
     */
    public void delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;

        String sql = "DELETE FROM user WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
