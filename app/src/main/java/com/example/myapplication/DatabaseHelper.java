// DatabaseHelper.java - Refactored and Well-Commented Version

package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

// DatabaseHelper class for managing SQLite database operations
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database configuration constants
    private static final String DATABASE_NAME = "lost_and_found.db";
    private static final int DATABASE_VERSION = 1;

    // Table and column names
    public static final String TABLE_NAME = "adverts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_POST_TYPE = "post_type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LOCATION = "location";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_POST_TYPE + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " DATE, " +
                COLUMN_LOCATION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    // Handle database upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert a new item into the database
    public void insertItem(String postType, String name, String phone, String description, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Populate values for insertion
        values.put(COLUMN_POST_TYPE, postType);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Retrieve an item by its ID
    public LostAndFoundItem getItemById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        LostAndFoundItem item = null;
        if (cursor.moveToFirst()) {
            item = new LostAndFoundItem(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POST_TYPE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION))
            );
        }

        cursor.close();
        db.close();
        return item;
    }

    // Delete an item by its ID
    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Retrieve a list of all items (ID and description)
    public List<ItemDescription> getAllItems() {
        List<ItemDescription> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + ", " + COLUMN_POST_TYPE + ", " + COLUMN_NAME + " FROM " + TABLE_NAME, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String postType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POST_TYPE));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));

            itemList.add(new ItemDescription(id, postType + ": " + name));
        }

        cursor.close();
        db.close();
        return itemList;
    }

    // Inner class for item descriptions
    public class ItemDescription {
        private int id;
        private String description;

        public ItemDescription(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() { return id; }

        public String getDescription() { return description; }
    }
}
