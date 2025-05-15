// ListActivity.java - Refactored and Well-Commented Version

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    // RecyclerView for displaying list of items
    private RecyclerView recyclerView;

    // Database helper for managing item data
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        // Initialize the RecyclerView and its layout
        initializeRecyclerView();

        // Load and display items from the database
        displayItemList();
    }

    // Method to initialize the RecyclerView
    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Method to load and display items from the database
    private void displayItemList() {
        dbHelper = new DatabaseHelper(this); // Initialize database helper
        List<DatabaseHelper.ItemDescription> itemList = dbHelper.getAllItems(); // Retrieve all items

        // Set up the RecyclerView adapter with the list
        ItemAdapter adapter = new ItemAdapter(itemList);

        // Set item click listener for navigating to DetailActivity
        adapter.setOnItemClickListener(this::navigateToDetail);

        recyclerView.setAdapter(adapter); // Attach adapter to RecyclerView
    }

    // Method to navigate to DetailActivity for a selected item
    private void navigateToDetail(int itemId) {
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        intent.putExtra("item_id", itemId); // Pass item ID to detail activity
        startActivity(intent);
    }
}
