package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.firebase.Model.feedback;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyHolder> {

    Context context;
    ArrayList<feedback> list;

    public RequestAdapter(Context context, ArrayList<feedback> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.itemfeedback, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        feedback cartmodel = list.get(position);
        holder.text.setText(cartmodel.getText());
        holder.rate.setText(cartmodel.getRate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

            TextView text,rate;


        public MyHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.cart_view_product_name);
                rate = itemView.findViewById(R.id.cart_view_price);

            }

        }
    }
