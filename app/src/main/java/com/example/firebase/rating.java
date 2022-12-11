package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebase.Model.CartModel;
import com.example.firebase.Model.Food;
import com.example.firebase.Model.feedback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class rating extends AppCompatActivity {
    RecyclerView mainCategoryRecycler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    RequestAdapter myadapter;
    ArrayList<feedback> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mainCategoryRecycler = findViewById(R.id.cart_rec);
        database = FirebaseDatabase.getInstance().getReference("feedbacks");


        mainCategoryRecycler.setHasFixedSize(true);

        mainCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myadapter = new RequestAdapter(this, list);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    feedback request = dataSnapshot.getValue(feedback.class);
                    list.add(request);

                    mainCategoryRecycler.setAdapter(myadapter);

                }

                myadapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(rating.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }}



