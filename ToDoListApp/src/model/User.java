/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kayje
 */
public class User implements Serializable{
private static final long serialVersionUID = 1L;
    private String username;
    private int id;
    private String email;
    private String password;
    private Date created;
    
    public User() {}

    public User(String username, String email, String password, Integer id, Date created) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    
    
    
}
