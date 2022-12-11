package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.Model.feedback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity6 extends AppCompatActivity {
    Button add;
    RatingBar ratingBar;
    TextView textView;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        textView=findViewById(R.id.tvFeedback);
        mAuth = FirebaseAuth.getInstance();


        ratingBar=findViewById(R.id.rbStars);

        add=findViewById(R.id.btnSend);
        rootNode= FirebaseDatabase.getInstance(); //instance de toutes les tables
        reference=rootNode.getReference("feedbacks"); // specifier quelle table

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=textView.getText().toString();
                String rate= String.valueOf(ratingBar.getRating());
                feedback user= new feedback(text,rate);
               // reference.child(rate).setValue(user);
                reference.push().setValue(user);
                Toast.makeText(MainActivity6.this, "Thank you for your time ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}