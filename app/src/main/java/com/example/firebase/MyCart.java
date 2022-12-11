package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebase.Model.CartModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCart extends AppCompatActivity {

    RecyclerView mainCategoryRecycler;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference database;
    CartAdapter myadapter;
    Button byNow;
    FirebaseUser user1;
    ArrayList<CartModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        mainCategoryRecycler = findViewById(R.id.cart_rec);
        byNow = findViewById(R.id.buy_now);
        database = FirebaseDatabase.getInstance().getReference("Requests");
        DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Requests");

        mainCategoryRecycler.setHasFixedSize(true);

        mainCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myadapter = new CartAdapter(this, list);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        CartModel request = dataSnapshot.getValue(CartModel.class);
                        //  if(dataSnapshot.child("Bissara").child("user").getValue().toString()==user1.getEmail().toString()) {
                  //  if (cartListRef.child(request.getName()).child("user").toString() == user1.getEmail().toString()) {

                        //
                        list.add(request);
                        //}
                        mainCategoryRecycler.setAdapter(myadapter);

                        //   request.setName(dataSnapshot.child("price").getValue().toString());
                        //String r1=dataSnapshot.child("name").getValue().toString();
                        // String r2=dataSnapshot.child("price").getValue().toString();
                        //String r3=dataSnapshot.child("quantity").getValue().toString();

                        //    Toast.makeText(MyCart.this,request.getImageUrl().toString(),Toast.LENGTH_SHORT).show();

                    }

                    myadapter.notifyDataSetChanged();
                }
          //  }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyCart.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    //////////////////////////


        byNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MyCart.this,"Your order is confirmed",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MyCart.this, ConfirmerOrder.class));

            }
        });

    }


}