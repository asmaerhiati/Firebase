package com.example.firebase;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.Model.Food;

import java.util.List;

public class promoAdapter extends RecyclerView.Adapter<promoAdapter.ViewHolder>{

    private Context context;
    private  List<Food> list;

    public promoAdapter(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public promoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.imageView);

        //Glide.with(context).load(list.get(position).getImageUrl()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
       // holder.description.setText(list.get(position).getDescription());
        holder.phone.setText(list.get(position).getPhone());
        holder.rating.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,phone,description,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.rec_image);
            name=itemView.findViewById(R.id.rec_name);
            //description=itemView.findViewById(R.id.rec_dec);
            rating=itemView.findViewById(R.id.rec_rating);
            phone=itemView.findViewById(R.id.phone);

        }
    }
}
