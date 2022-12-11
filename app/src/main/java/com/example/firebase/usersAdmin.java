package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.firebase.Model.feedback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class usersAdmin extends AppCompatActivity {
    RecyclerView mainCategoryRecycler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    UsersAdapter myadapter;
    ArrayList<UserHelperClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_admin);

        mainCategoryRecycler = findViewById(R.id.cart_rec);
        database = FirebaseDatabase.getInstance().getReference("users");


        mainCategoryRecycler.setHasFixedSize(true);

        mainCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myadapter = new UsersAdapter(this, list);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserHelperClass request = dataSnapshot.getValue(UserHelperClass.class);
                    list.add(request);

                    mainCategoryRecycler.setAdapter(myadapter);

                }

                myadapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(usersAdmin.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }}