package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.Model.Prevelant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import android.os.Bundle;

public class ConfirmerOrder extends AppCompatActivity {
    private EditText etxtFullName ,etxtPhoneNumber,etxtHomeAddress;
    private Button shippmentConfirmBtn;
    private ImageView shippmentBackbtn;
    private String totalAmount="";
    DatabaseReference reference;
    FirebaseUser user1;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmer_order);

        totalAmount=getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Prix total est :"+totalAmount, Toast.LENGTH_SHORT).show();
        etxtFullName=(EditText)findViewById(R.id.shippment_name);
        etxtPhoneNumber=(EditText)findViewById(R.id.shippment_phone);
        etxtHomeAddress=(EditText)findViewById(R.id.shippment_home_address);
        user1= FirebaseAuth.getInstance().getCurrentUser();
        String gmail = user1.getEmail();
        shippmentConfirmBtn=(Button)findViewById(R.id.shippment_confirm_btn);

        shippmentConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });


    }

    private void check()
    {
        if(TextUtils.isEmpty(etxtFullName.getText().toString()))
        {
            Toast.makeText(this, "Nom est vide", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(etxtPhoneNumber.getText().toString()))
        {
            Toast.makeText(this, "Numéro est vide", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(etxtHomeAddress.getText().toString()))
        {
            Toast.makeText(this, "Adresse est vide", Toast.LENGTH_SHORT).show();
        }

        else
        {
            confirmOrder();
        }

    }

    private void confirmOrder()
    {
        Calendar calendar = Calendar.getInstance();
        rootNode= FirebaseDatabase.getInstance(); //instance de toutes les tables
      // reference=rootNode.getReference("Request"); // specifier quelle table
        //reference.push().setValue(etxtFullName.getText().toString(),etxtPhoneNumber.getText().toString());


        //SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
       // String saveCurrentDate = currentDate.format(calendar.getTime());

      //  SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
       // String saveCurrentTime = currentTime.format(calendar.getTime());

        final DatabaseReference OrdersRef=FirebaseDatabase.getInstance().getReference("Request")
                ;


        HashMap<String ,Object>orderMap=new HashMap<>();
        //orderMap.put("Total Amount",totalAmount);
        orderMap.put("Name",etxtFullName.getText().toString());
        orderMap.put("Phone",etxtPhoneNumber.getText().toString());
        orderMap.put("Address",etxtHomeAddress.getText().toString());

        //orderMap.put("date",saveCurrentDate);
        //orderMap.put("time",saveCurrentTime);
        orderMap.put("State","not shipped");
        orderMap.put("","");
        OrdersRef.child("user1").setValue(orderMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void unused) {
                Toast.makeText(ConfirmerOrder.this, "Order will be Placed", Toast.LENGTH_SHORT).show();

            }
        });

        /*
        OrdersRef.updateChildren(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference().child("User View")
                           // .child(etxtFullName.getText().toString())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(ConfirmerOrder.this, "Order will be Placed", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(ConfirmerOrder.this, com.example.firebase.MainActivity4.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });*/
    }
}

