package model;

import java.io.Serializable;
// No HashSet or Set import needed if not including the tasks collection
// import java.util.HashSet;
// import java.util.Set;


public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    // private Set<Tasks> tasks = new HashSet<>(); // Generally omit for client DTO

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    // public Set<Tasks> getTasks() { return tasks; }
    // public void setTasks(Set<Tasks> tasks) { this.tasks = tasks; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() { // Useful for ComboBox display
        return name;
    }
}
