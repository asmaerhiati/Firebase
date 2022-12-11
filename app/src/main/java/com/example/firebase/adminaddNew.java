package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.grpc.Compressor;

public class adminaddNew extends AppCompatActivity {

    private String CategoryName, Description, Price, Pname;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDescription, InputProductPrice;
    private Uri ImageUri;
    private static final int GalleryPick = 1;

private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminadd_new);
        AddNewProductButton =  findViewById(R.id.add_new_product);
        InputProductImage = findViewById(R.id.select_product_image);
        InputProductName = findViewById(R.id.product_name);
        InputProductDescription =  findViewById(R.id.product_description);
        InputProductPrice = findViewById(R.id.product_prices);

        //firebaseauth=FirebaseAuth.getInstance();

        firebaseFirestore= FirebaseFirestore.getInstance();
        //storagereference= FirebaseStorage.getInstance().getReference();

        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });
        AddNewProductButton.setOnClickListener(view ->{
                ValidateProductData();

        });
    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }
    private void ValidateProductData()
    {
        Description = InputProductDescription.getText().toString();
        Price = InputProductPrice.getText().toString();
        Pname = InputProductName.getText().toString();


        if (ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description))
        {
            Toast.makeText(this, "Please write product description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Price))
        {
            Toast.makeText(this, "Please write product Price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Please write product name...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {
        final String product = InputProductName.getText().toString();
        final String description = InputProductDescription.getText().toString();
        final String price = InputProductPrice.getText().toString();

        if(!TextUtils.isEmpty(product) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(price)){
            //  File newFile = new File(imageUri.getPath());
            Map<String,String> items = new HashMap<>();
            items.put("descriptiom",description);
            items.put("name",product);
            items.put("price",price);

            firebaseFirestore.collection("PromoDuJour").add(items).addOnCompleteListener(
                    new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            InputProductName.setText("");
                            InputProductPrice.setText("");
                            InputProductDescription.setText("");
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        }
                    }
            );


        }else{
            Toast.makeText(adminaddNew.this,"Fill all the fields",Toast.LENGTH_SHORT).show();
        }

    }

    }


