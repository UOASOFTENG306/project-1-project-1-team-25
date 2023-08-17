package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SellActivity extends AppCompatActivity {

    private TextView logoText;
    private EditText titleInput;
    private EditText subtitleInput;
    private EditText descriptionInput;
    private EditText priceInput;
    private Spinner categorySpinner;
    private RecyclerView imagesRecyclerView;
    private Button addImageButton;
    private Button removeImageButton;
    private Button listItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        logoText = findViewById(R.id.logoText);
        titleInput = findViewById(R.id.titleInput);
        subtitleInput = findViewById(R.id.subtitleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        priceInput = findViewById(R.id.priceInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        imagesRecyclerView = findViewById(R.id.imagesRecyclerView);
        addImageButton = findViewById(R.id.addImageButton);
        removeImageButton = findViewById(R.id.removeImageButton);
        listItemButton = findViewById(R.id.listItemButton);

        logoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
