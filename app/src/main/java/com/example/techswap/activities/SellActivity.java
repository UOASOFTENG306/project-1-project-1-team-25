package com.example.techswap.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.techswap.R;
import com.example.techswap.adapters.SellImageAdapter;
import com.example.techswap.database.Database;
import com.example.techswap.interfaces.IDatabase;
import com.example.techswap.interfaces.ISellImageAdapter;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.ItemFactory;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SellActivity extends AppCompatActivity {

    IDatabase db = new Database();
    final ISellImageAdapter sellImageAdapter = new SellImageAdapter(this, null);
    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private final List<String> imageUrlList = new ArrayList<>();
    private EditText titleInput;
    private EditText subtitleInput;
    private EditText descriptionInput;
    private EditText priceInput;
    private Spinner categorySpinner;
    private Uri imageUri;
    final ActivityResultLauncher<String> mGetContent = registerForActivityResult((new ActivityResultContracts.GetContent()),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        imageUri = result;
                        uploadImage();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        TextView logoText = findViewById(R.id.logoText);
        titleInput = findViewById(R.id.titleInput);
        subtitleInput = findViewById(R.id.subtitleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        priceInput = findViewById(R.id.priceInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        RecyclerView imagesRecyclerView = findViewById(R.id.imagesRecyclerView);
        Button addImageButton = findViewById(R.id.addImageButton);
        Button listItemButton = findViewById(R.id.listItemButton);

        List<CharSequence> list = new ArrayList<>();
        list.add("CPU");
        list.add("GPU");
        list.add("Motherboard");
        list.add("Storage");
        list.add("Memory");
        list.add("Power");
        list.add("Case");
        list.add("Other");

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.adapter_spinner_item, list);
        // Apply the adapter to the spinner
        categorySpinner.setAdapter(adapter);

        // Images recycler view
        LinearLayoutManager imageLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        imagesRecyclerView.setLayoutManager(imageLayoutManager);
        imagesRecyclerView.setAdapter((RecyclerView.Adapter<?>) sellImageAdapter);

        logoText.setOnClickListener(v -> {
            // switch to another activity
            Intent intent = new Intent(SellActivity.this, MainActivity.class);
            startActivity(intent);
        });

        listItemButton.setOnClickListener(v -> {
            if (!(titleInput.getText().toString().equals("") || subtitleInput.getText().toString().equals("") || descriptionInput.getText().toString().equals("") || priceInput.getText().toString().equals(""))) {
                Item item = ItemFactory.getItem(categorySpinner.getSelectedItem().toString());

                Details details = item.getDetails();
                details.setTitle(titleInput.getText().toString());
                details.setSubtitle(subtitleInput.getText().toString());
                details.setDescription(descriptionInput.getText().toString());
                details.setPrice(Double.parseDouble(priceInput.getText().toString()));

                item.setDetails(details);
                item.setImageUrls(imageUrlList);

                UUID uuid = UUID.randomUUID();
                item.setId(uuid.toString());

                db.addItem(item);

                // switch to another activity
                Intent intent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        addImageButton.setOnClickListener(v -> mGetContent.launch("image/*"));
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());
            imageUrlList.add(getResources().getString(R.string.image_placeholder));
            sellImageAdapter.updateImages(imageUrlList);

            reference.putFile(imageUri).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    reference.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        // urlList gets added as firestore field
                        imageUrlList.remove(imageUrlList.size() - 1);
                        imageUrlList.add(imageUrl);
                        sellImageAdapter.updateImages(imageUrlList);
                    });
                }
            });
        }
    }
}
