package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    // UI Components
    private RadioGroup radioGroup; // Group containing radio buttons
    private RadioButton radioLost; // RadioButton for 'Lost'
    private RadioButton radioFound; // RadioButton for 'Found'
    private EditText nameEditText; // EditText for name
    private EditText phoneEditText; // EditText for phone number
    private EditText descriptionEditText; // EditText for description
    private EditText locationEditText; // EditText for location
    private DatePicker datePicker; // DatePicker for selecting a date
    private Button saveButton; // Button to save the item

    // Database Helper instance
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        initializeComponents(); // Initialize view components and database

        saveButton.setOnClickListener(v -> saveItem()); // Set the click event for the save button
    }

    // Method to initialize UI components
    private void initializeComponents() {
        dbHelper = new DatabaseHelper(this);

        // Initialize view components
        radioGroup = findViewById(R.id.rgPostType);
        radioLost = findViewById(R.id.rbLost);
        radioFound = findViewById(R.id.rbFound);
        nameEditText = findViewById(R.id.etName);
        phoneEditText = findViewById(R.id.etPhone);
        descriptionEditText = findViewById(R.id.etDescription);
        locationEditText = findViewById(R.id.etLocation);
        datePicker = findViewById(R.id.datePicker);
        saveButton = findViewById(R.id.btnSave);

        // Validate initialization
        validateComponents();
    }

    // Method to validate that all components are properly initialized
    private void validateComponents() {
        if (radioGroup == null || radioLost == null || radioFound == null || nameEditText == null ||
                phoneEditText == null || descriptionEditText == null || locationEditText == null || datePicker == null || saveButton == null) {
            Toast.makeText(this, "Initialization error", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Method to save an item
    private void saveItem() {
        // Ensure a RadioButton is selected
        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedButton = findViewById(checkedId);

        if (selectedButton == null) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        // Extract data from UI
        String postType = selectedButton.getText().toString();
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String location = locationEditText.getText().toString();

        // Get the date from DatePicker
        String formattedDate = getSelectedDate();

        // Insert the data into the database
        dbHelper.insertItem(postType, name, phone, description, formattedDate, location);

        // Notify the user and close
        Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    // Method to get the selected date from DatePicker
    private String getSelectedDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        return year + "-" + month + "-" + day;
    }
}
