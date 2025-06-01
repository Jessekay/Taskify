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
public class Category implements Serializable{
     private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;
    private Integer user_id;
    private Date created;

   


    public Category() {}

    public Category(Integer id, String name, String description, String color, Integer user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
     public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    } 
}
