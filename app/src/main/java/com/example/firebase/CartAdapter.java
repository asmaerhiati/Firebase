package com.example.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.Model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHlder>{
    DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Requests");

    Context context;
    ArrayList<CartModel> list;

    public CartAdapter( Context context, ArrayList<CartModel> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.itemcart,parent,false);
        return new MyViewHlder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHlder holder, int position) {
        CartModel cartmodel=list.get(position);


        //if(cartListRef.child(cartmodel.getName()).child("user").toString()==user1.getEmail().toString()){
       // if(cartmodel.getUser().equals(user1.getEmail())) {
            holder.foodname.setText(cartmodel.getName());
            holder.price.setText(cartmodel.getPrice());
            holder.quantity.setText(cartmodel.getQuantity());
            Glide.with(context).load(list.get(position).getImageUrl()).into(holder.imageView);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartListRef
                            .child(cartmodel.getName())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(context, MyCart.class);
                                        // intent.putExtra("Name",categoryItemList.get(position).getName());

                                        context.startActivity(intent);
                                    }
                                }
                            });

                }
            });

        }

      //  Toast.makeText(CartAdapter.this,a,Toast.LENGTH_SHORT).show();





    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder {

        TextView foodname,price,quantity;
        ImageView imageView;
        Button delete;

        public MyViewHlder(@NonNull View itemView) {
            super(itemView);
            foodname=itemView.findViewById(R.id.cart_view_product_name);
            price=itemView.findViewById(R.id.cart_view_price);
            imageView=itemView.findViewById(R.id.cart_view_product_image);
            quantity=itemView.findViewById(R.id.cart_view_quantity);
            delete=itemView.findViewById(R.id.btnLogout);
        }
    }
}
