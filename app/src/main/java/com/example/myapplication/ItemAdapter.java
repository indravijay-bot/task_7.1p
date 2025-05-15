// ItemAdapter.java - Refactored and Well-Commented Version

package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter class for handling RecyclerView items
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    // List of items to be displayed in RecyclerView
    private List<DatabaseHelper.ItemDescription> itemList;

    // Listener for item click events
    private OnItemClickListener onItemClickListener;

    // Interface definition for item click callbacks
    public interface OnItemClickListener {
        void onItemClick(int itemId);
    }

    // Constructor for the adapter with item list
    public ItemAdapter(List<DatabaseHelper.ItemDescription> itemList) {
        this.itemList = itemList;
    }

    // Method to create a new ViewHolder for each item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    // Method to bind data to the ViewHolder for each item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseHelper.ItemDescription item = itemList.get(position);

        // Set description and ID text for each item
        holder.textDescription.setText(item.getDescription());
        holder.idTextView.setText(String.valueOf(item.getId()));

        // Set click listener for the item view
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int itemId = Integer.parseInt(holder.idTextView.getText().toString());
                onItemClickListener.onItemClick(itemId);
            }
        });
    }

    // Method to get the total number of items
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Method to set a custom click listener for items
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Custom ViewHolder class for RecyclerView items
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textDescription; // TextView for item description
        TextView idTextView;      // Hidden TextView for item ID

        // ViewHolder constructor initializes item views
        public ViewHolder(View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.textDescription);
            idTextView = itemView.findViewById(R.id.idTextView);
        }
    }
}