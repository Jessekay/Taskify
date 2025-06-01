package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kayje
 */
public class Tasks implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;
    private int user_id;
    private String title;
    private Date due_date;
    private boolean is_completed;
    private String priority;
    private int category_id;
    
    public Tasks() {}

    public Tasks(int id, String name, String description, String color, int user_id, String title, Date due_date, boolean is_completed, String priority, int category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
        this.title = title;
        this.due_date = due_date;
        this.is_completed = is_completed;
        this.priority = priority;
        this.category_id = category_id;
    }

    public Tasks(int CURRENT_USER_ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(Boolean is_completed) {
        this.is_completed = is_completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}