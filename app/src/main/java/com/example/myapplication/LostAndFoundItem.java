// LostAndFoundItem.java - Refactored and Well-Commented Version

package com.example.myapplication;

// Data model class representing a Lost and Found item
public class LostAndFoundItem {

    // Attributes of a lost or found item
    private int id;               // Unique identifier
    private String postType;      // Type of post (Lost or Found)
    private String name;          // Name of the person posting
    private String phone;         // Contact phone number
    private String description;   // Description of the item
    private String date;          // Date of the incident
    private String location;      // Location of the incident

    // Constructor to initialize all fields of the LostAndFoundItem
    public LostAndFoundItem(int id, String postType, String name, String phone, String description, String date, String location) {
        this.id = id;
        this.postType = postType;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Getter methods for accessing item details
    public int getId() { return id; }
    public String getPostType() { return postType; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
}
