package com.example.firebasetest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryBoardAdapter extends RecyclerView.Adapter<StoryBoardAdapter.StoryboardViewHolder> {
    private List<StoryBoard> storyboardList;  // Data to be displayed
    private OnItemClickListener listener;  // For handling click events

    // Constructor to initialize the list
    public StoryBoardAdapter(List<StoryBoard> storyboardList) {
        this.storyboardList = storyboardList;
    }

    // Define an interface to handle clicks on items
    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_storyboard, parent, false);
        return new StoryboardViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryboardViewHolder holder, int position) {
        // Get the current storyboard from the list
        StoryBoard storyboard = storyboardList.get(position);

        // Set the data for the current item
        holder.screenTitle.setText(storyboard.getScreenTitle());
    }

    @Override
    public int getItemCount() {
        return storyboardList.size();  // Number of items in the list
    }

    // ViewHolder to manage individual item views
    public static class StoryboardViewHolder extends RecyclerView.ViewHolder {
        TextView screenTitle;
        Button editButton, deleteButton;

        public StoryboardViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            screenTitle = itemView.findViewById(R.id.screenTitle);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            // Set click listeners for the buttons
            editButton.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(position);
                    }
                }
            });

            deleteButton.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });
        }
    }
}

