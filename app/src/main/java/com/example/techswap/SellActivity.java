package com.example.techswap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.adapters.SellImageAdapter;
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.ItemFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private Uri imageUri;
    private final List<String> imageUrlList = new ArrayList<String>();
    SellImageAdapter sellImageAdapter = new SellImageAdapter(this, null);

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
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        // Apply the adapter to the spinner
        categorySpinner.setAdapter(adapter);

        // Images recycler view
        LinearLayoutManager imageLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        imagesRecyclerView.setLayoutManager(imageLayoutManager);
        imagesRecyclerView.setAdapter(sellImageAdapter);

        logoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to another activity
                Intent intent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        listItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleInput.getText().toString().equals("") || subtitleInput.getText().toString().equals("") || descriptionInput.getText().toString().equals("") || priceInput.getText().toString().equals("")) {
                    return;

                } else {

                    ItemFactory factory = new ItemFactory();
                    Item item = factory.getItem(categorySpinner.getSelectedItem().toString());

                    Details details = item.getDetails();
                    details.setTitle(titleInput.getText().toString());
                    details.setSubtitle(subtitleInput.getText().toString());
                    details.setDescription(descriptionInput.getText().toString());
                    details.setPrice(Double.parseDouble(priceInput.getText().toString()));

                    item.setDetails(details);
                    item.setImageUrls(imageUrlList);

                    UUID uuid = UUID.randomUUID();
                    item.setId(uuid.toString());

                    DatabaseSetter db = new DatabaseSetter();
                    db.addItem(item);

                    // switch to another activity
                    Intent intent = new Intent(SellActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());

            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                // urlList gets added as firestore field
                                imageUrlList.add(imageUrl);
                                sellImageAdapter.updateImages(imageUrlList);
                                // TODO: Set image on sell activity, use URL somewhere
                            }
                        });
                    }
                }
            });
        }
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult((new ActivityResultContracts.GetContent()),
        new ActivityResultCallback<Uri>()   {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    imageUri = result;
                    uploadImage();
                }
            }
    });
}
