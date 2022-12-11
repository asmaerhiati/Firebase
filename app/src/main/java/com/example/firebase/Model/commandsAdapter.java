package com.example.firebase.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.MyCart;
import com.example.firebase.R;
import com.example.firebase.UserHelperClass;
import com.example.firebase.requestAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class commandsAdapter extends RecyclerView.Adapter<com.example.firebase.Model.commandsAdapter.MyHolder> {
    DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Requests");

    Context context;
    ArrayList<CartModel> list;

    public commandsAdapter(Context context, ArrayList<CartModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public com.example.firebase.Model.commandsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.itemcommande, parent, false);
        return new MyHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        CartModel cartmodel = list.get(position);
        holder.name.setText(cartmodel.getName());
        holder.quantity.setText(cartmodel.getQuantity());
        holder.price.setText(cartmodel.getPrice());
        holder.user.setText(cartmodel.getUser());

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
                                    Intent intent = new Intent(context, requestAdmin.class);
                                    // intent.putExtra("Name",categoryItemList.get(position).getName());

                                    context.startActivity(intent);
                                }
                            }
                        });

            }
        });

}

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, quantity, price,user;
        Button delete;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.cart_view_product_name);
            name = itemView.findViewById(R.id.cart_view_price);
            price=itemView.findViewById(R.id.cart_view_quantity);
            quantity=itemView.findViewById(R.id.user);
            delete=itemView.findViewById(R.id.btnLogout);

        }

    }
}
