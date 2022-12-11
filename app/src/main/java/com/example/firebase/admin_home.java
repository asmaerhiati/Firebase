package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class admin_home extends AppCompatActivity {

    ImageView popular;
    ImageView rduction;
    ImageView commands;
    ImageView users,review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        review=findViewById(R.id.review);

        popular=findViewById(R.id.populat);
        rduction=findViewById(R.id.reduction);
        users=findViewById(R.id.Infos);
        commands=findViewById(R.id.commands);
        popular.setOnClickListener(view ->{
               // startActivity(new Intent(admin_home.this, informationUser.class));
            Intent intent = new Intent(admin_home.this, informationUser.class);
            intent.putExtra("category", "PromoDuJour");
            startActivity(intent);

        });
        rduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(admin_home.this, informationUser.class);
      //          Intent intent = new Intent(admin_home.this, rating.class);
                intent.putExtra("category", "popularProducts");
                startActivity(intent);
            }
        });
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(admin_home.this, usersAdmin.class);
                //          Intent intent = new Intent(admin_home.this, rating.class);
              //  intent.putExtra("category", "popularProducts");
                startActivity(intent);
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(admin_home.this,rating.class);
                //          Intent intent = new Intent(admin_home.this, rating.class);
                //  intent.putExtra("category", "popularProducts");
                startActivity(intent);
            }
        });

        commands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(admin_home.this, requestAdmin.class);
                //          Intent intent = new Intent(admin_home.this, rating.class);
                //  intent.putExtra("category", "popularProducts");
                startActivity(intent);
            }
        });

    }
}