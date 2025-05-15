// DetailActivity.java - Refactored and Well-Commented Version

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    // UI elements for displaying item details
    private TextView postTypeValue, nameValue, phoneValue, descriptionValue, dateValue, locationValue, idTextView;
    private Button removeButton;

    // Database helper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity); // Set layout for the activity

        // Initialize database helper and UI elements
        initializeComponents();

        // Retrieve item ID from intent
        int itemId = getIntent().getIntExtra("item_id", -1);

        // Validate the retrieved item ID
        if (!isValidItemId(itemId)) return;

        // Display item details using the ID
        displayItemDetails(itemId);

        // Set click listener for the remove button
        setRemoveButtonListener();
    }

    // Method to initialize UI components and database
    private void initializeComponents() {
        dbHelper = new DatabaseHelper(this);

        idTextView = findViewById(R.id.idTextView);
        postTypeValue = findViewById(R.id.postTypeValue);
        nameValue = findViewById(R.id.etName);
        phoneValue = findViewById(R.id.etPhone);
        descriptionValue = findViewById(R.id.descriptionValue);
        dateValue = findViewById(R.id.datePicker);
        locationValue = findViewById(R.id.etLocation);
        removeButton = findViewById(R.id.removebutton);
    }

    // Method to check if the item ID is valid
    private boolean isValidItemId(int itemId) {
        if (itemId == -1) {
            Toast.makeText(this, "Invalid item ID", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if invalid
            return false;
        }
        idTextView.setText(String.valueOf(itemId)); // Store item ID
        return true;
    }

    // Method to display item details
    private void displayItemDetails(int itemId) {
        LostAndFoundItem item = dbHelper.getItemById(itemId);

        if (item == null) {
            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set item details in TextViews
        postTypeValue.setText(item.getPostType());
        nameValue.setText(item.getName());
        phoneValue.setText(item.getPhone());
        descriptionValue.setText(item.getDescription());
        dateValue.setText(item.getDate());
        locationValue.setText(item.getLocation());
    }

    // Method to set the remove button click listener
    private void setRemoveButtonListener() {
        removeButton.setOnClickListener(v -> {
            int idToDelete = Integer.parseInt(idTextView.getText().toString());
            dbHelper.deleteItem(idToDelete); // Delete the item

            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();

            // Navigate back to MainActivity
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Close current activity
        });
    }
}
