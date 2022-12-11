package com.example.firebase;

import android.content.Context;
import android.content.Intent;
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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
   private Context context;
   private List<Food> popularlist;

    public PopularAdapter(Context context, List<Food> popularlist) {
        this.context = context;
        this.popularlist = popularlist;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(popularlist.get(position).getImageUrl()).into(holder.imageP);
        holder.textname.setText(popularlist.get(position).getName());
        holder.textPrice.setText(popularlist.get(position).getPrice());
        holder.Phone.setText(popularlist.get(position).getPhone());

        // holder.desc.setText(popularlist.get(position).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Detail_Dish.class);
                intent.putExtra("Name",popularlist.get(position).getName());
                intent.putExtra("Price",popularlist.get(position).getPrice());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageP;
        TextView textname,Phone;
        TextView textPrice;
       // TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //desc=itemView.findViewById(R.id.textView5);
            textname=itemView.findViewById(R.id.food_name);
            textPrice=itemView.findViewById(R.id.food_price);
            Phone=itemView.findViewById(R.id.textView5);

            imageP=itemView.findViewById(R.id.food_image);
        }
    }
}
