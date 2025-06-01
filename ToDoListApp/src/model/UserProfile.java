package model;

import java.io.Serializable;

public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L; // Ensure this matches server if strict serialization is ever an issue

    private int id;
    private String bio;
    private String phoneNumber;
    private String profilePictureUrl;
    private User user; // For holding user association if needed on client

    public UserProfile() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
