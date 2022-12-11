package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.firebase.Model.Prevelant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.firebase.Model.AllCategory;
import com.example.firebase.Model.CartModel;
import com.example.firebase.Model.CategoryItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Detail_Dish extends AppCompatActivity {
    ImageView image;
    Prevelant prevelant;

    TextView pricetext,descriptionText,nametext,textView4;
    DatabaseReference ref;
    Button button;
    FirebaseDatabase rootNode;
    StorageReference storage;
    ElegantNumberButton numberButton;
    FirebaseAuth mAuth;
    FirebaseUser user1;
    RatingBar ratingBar;
        int total;
        String det="Details commande";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dish);
        ratingBar=findViewById(R.id.ratingBar);
        textView4=findViewById(R.id.textView4);
        ref= FirebaseDatabase.getInstance().getReference().child("dish");
        storage= FirebaseStorage.getInstance().getReference().child("images");
        button=findViewById(R.id.add_product_to_cart_btn);
        mAuth = FirebaseAuth.getInstance();
        image=findViewById(R.id.img_food);
        pricetext=findViewById(R.id.food_price);
        descriptionText=findViewById(R.id.food_description);
        nametext=findViewById(R.id.food_name);
        numberButton=findViewById(R.id.number_btn);
        String category=getIntent().getStringExtra("Name");

       // Toast.makeText(Detail_Dish.this,category,Toast.LENGTH_SHORT).show();

        user1= FirebaseAuth.getInstance().getCurrentUser();
        String gmail = user1.getEmail();




        ref.child(category).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot=task.getResult();
                        String price=String.valueOf(dataSnapshot.child("price").getValue());
                        pricetext.setText(price);
                        String description=String.valueOf(dataSnapshot.child("description").getValue());
                        descriptionText.setText(description);
                        String name=String.valueOf(dataSnapshot.child("Name").getValue());
                        nametext.setText(name);
                        String link=dataSnapshot.child("ImageUrl").getValue(String.class);
                        //Toast.makeText(Detail_Dish.this,link,Toast.LENGTH_SHORT).show();
                        String a=String.valueOf(dataSnapshot.child("ImageUrl").getValue());
                        Picasso.get().load(a).into(image);
                            textView4.setText(a);
                    }else{
                        Toast.makeText(Detail_Dish.this,"Not found",Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(Detail_Dish.this,"Faiiled",Toast.LENGTH_SHORT).show();
                }
            }
        });

button.setOnClickListener(view ->{
    createUser();


});

    }

    private void createUser() {

                rootNode=FirebaseDatabase.getInstance(); //instance de toutes les tables
                ref=rootNode.getReference("Requests"); // specifier quelle table

        CartModel cart=new CartModel(nametext.getText().toString(), numberButton.getNumber(),pricetext.getText().toString(),textView4.getText().toString(),user1.getEmail().toString());
                ref.child(nametext.getText().toString()).setValue(cart);


    }
}