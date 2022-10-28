/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;

/**
 *
 * @author Khanh Nguyen
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userservice = new UserService();
        RoleService roleservice = new RoleService();
        String action = request.getParameter("action");

        try {
            if (action != null && action.equals("edit")) {
                String email = request.getParameter("email");
                User selectedUser = userservice.getUser(email);
                request.setAttribute("selectedUser", selectedUser);
                request.setAttribute("email", selectedUser.getEmail());
            } else if (action != null && action.equals("delete")) {
                String email = request.getParameter("email");
                userservice.delete(email);
                List<User> users = userservice.getAllUser();
                if (users.isEmpty()) {
                    request.setAttribute("message", "No users found. Please add a user.");
                }
            }

            List<User> users = userservice.getAllUser();
            request.setAttribute("users", users);

            List<Role> roles = roleservice.getAllRole();
            request.setAttribute("roles", roles);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userservice = new UserService();
        // action must be one of: create, update
        String action = request.getParameter("action");

        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        Role newRole = new Role(role);

        try {
            if (userservice.fieldValidation(email, firstname, lastname, password) == null) {
                switch (action) {
                    case "update": {
                        userservice.update(email, firstname, lastname, password, newRole);
                    }
                    break;
                    case "create": {
                        List<User> users = userservice.getAllUser();
                        if (userservice.userValidation(users, email)) {
                            userservice.insert(email, firstname, lastname, password, newRole);
                        } else {
                            request.setAttribute("message", "This email is already used.");
                        }
                    }
                    break;
                }
            } else {
                request.setAttribute("alertMessage", "All fields are required.");
            }

            List<User> users = userservice.getAllUser();
            request.setAttribute("users", users);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        doGet(request, response);
    }

    

}
