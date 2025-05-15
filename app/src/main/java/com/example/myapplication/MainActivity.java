// MainActivity.java - Refactored and Well-Commented Version

package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display for a modern UI experience
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust layout padding to account for system bars (status/navigation)
        adjustSystemBarPadding();

        // Initialize buttons and set their listeners
        initializeButtons();
    }

    // Method to adjust padding for system bars (status and navigation)
    private void adjustSystemBarPadding() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to initialize buttons and set click listeners
    private void initializeButtons() {
        Button createButton = findViewById(R.id.createButton);
        Button showButton = findViewById(R.id.showButton);

        // Listener for "Create" button to start CreateActivity
        createButton.setOnClickListener(this::openCreateActivity);

        // Listener for "Show" button to start ListActivity
        showButton.setOnClickListener(this::openListActivity);
    }

    // Method to start CreateActivity
    private void openCreateActivity(View v) {
        Intent intent = new Intent(MainActivity.this, CreateActivity.class);
        startActivity(intent);
    }

    // Method to start ListActivity
    private void openListActivity(View v) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
