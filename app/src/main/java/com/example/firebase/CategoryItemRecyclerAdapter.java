package com.example.firebase;

import static androidx.core.content.ContextCompat.startActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Model.CategoryItem;

import java.util.List;
import android.content.Intent;
import android.widget.TextView;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;
    private List<CategoryItem> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder,int position) {

        holder.itemImage.setImageResource(categoryItemList.get(position).getImageUrl());
        holder.textViewRestaurantName.setText(categoryItemList.get(position).getName());
        holder.categoryPrice.setText(categoryItemList.get(position).getPrice());
        holder.categoryPhone.setText(categoryItemList.get(position).getPhone());
        holder.categoryRate.setText(categoryItemList.get(position).getRating());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Detail_Dish.class);
                intent.putExtra("Name",categoryItemList.get(position).getName());

             context.startActivity(intent);
            }
       });
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView textViewRestaurantName;
        TextView categoryPrice;
        TextView categoryRate;
        TextView categoryPhone;
        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            textViewRestaurantName=itemView.findViewById(R.id.textViewRestaurantName);
            categoryPhone=itemView.findViewById(R.id.textPhone);
            categoryRate=itemView.findViewById(R.id.textRating);
            categoryPrice=itemView.findViewById(R.id.textprice);
        }
    }

}