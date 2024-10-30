package com.example.firebasetest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView tvWelcome;
    private Button btnLogout;
    private RecyclerView recyclerView;
    private StoryBoardAdapter storyboardAdapter;
    private List<StoryBoard> storyboardList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and adapter
        storyboardList = new ArrayList<>();
        storyboardAdapter = new StoryBoardAdapter(storyboardList);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(storyboardAdapter);

        // Load data into the list (this can come from Firebase or anywhere else)
        loadStoryboards();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        // Get the current user
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            tvWelcome.setText("Welcome, " + currentUser.getEmail());
        }

        // Set onClickListener for the logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        // Handle edit and delete button clicks
        storyboardAdapter.setOnItemClickListener(new StoryBoardAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                // Handle editing the item at 'position'
                StoryBoard storyboard = storyboardList.get(position);
                // Show a dialog or another screen to edit the storyboard
            }

            @Override
            public void onDeleteClick(int position) {
                // Handle deleting the item at 'position'
                StoryBoard storyboard = storyboardList.get(position);
                deleteStoryboardFromFirebase(storyboard);  // This would be a method to delete from Firebase
                storyboardList.remove(position);  // Remove from local list
                storyboardAdapter.notifyItemRemoved(position);
            }
        });
    }

    // Dummy method to load storyboard data
    private void loadStoryboards() {
        storyboardList.add(new StoryBoard("Screen 1", "Buttons, Text Fields", "Screen 2"));
        storyboardList.add(new StoryBoard("Screen 2", "ImageView, ListView", "Screen 3"));
        storyboardAdapter.notifyDataSetChanged();  // Notify adapter to refresh the list
    }

    // Method to delete from Firebase (implement your own logic here)
    private void deleteStoryboardFromFirebase(StoryBoard storyboard) {
        // Firebase Firestore delete logic here
    }

    private void logoutUser() {
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
