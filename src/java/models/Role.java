/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Khanh Nguyen
 */
public class Role implements Serializable {
    private String systemadmin;
    private String regularuser;

    public Role() {
    }

    public Role(String systemadmin, String regularuser) {
        this.systemadmin = systemadmin;
        this.regularuser = regularuser;
    }

    public String getSystemadmin() {
        return systemadmin;
    }

    public void setSystemadmin(String systemadmin) {
        this.systemadmin = systemadmin;
    }

    public String getRegularuser() {
        return regularuser;
    }

    public void setRegularuser(String regularuser) {
        this.regularuser = regularuser;
    }
    
    
    
}
