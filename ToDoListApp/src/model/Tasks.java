package model;

import java.io.Serializable;
import java.util.Date;
import model.User; // Added import
import model.Category; // Added import

/**
 *
 * @author kayje
 */
public class Tasks implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int id;
    // private String name; // Removed
    private String description;
    // private int user_id; // Removed
    private String title;
    private Date due_date; // Kept as java.util.Date
    // private boolean is_completed; // Removed
    private String priority;
    // private int category_id; // Removed

    private User user; // Added field
    private Category category; // Added field
    
    public Tasks() {}

    // Updated constructor
    public Tasks(int id, String description, String title, Date due_date, String priority, User user, Category category) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.due_date = due_date;
        this.priority = priority;
        this.user = user;
        this.category = category;
    }

    // Removed constructor: public Tasks(int CURRENT_USER_ID)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Removed getName() and setName()

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Removed getUser_id() and setUser_id()

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

    // Removed getIs_completed() and setIs_completed()

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    // Removed getCategory_id() and setCategory_id()

    // Added getters and setters for user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Added getters and setters for category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}