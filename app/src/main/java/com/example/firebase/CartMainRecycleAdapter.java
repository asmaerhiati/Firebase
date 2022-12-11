package com.example.firebase;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Model.CartModel;

import java.util.List;

public class CartMainRecycleAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;
    private List<CartModel> categoryItemList;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCategoryItemList(List<CartModel> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public Context getContext() {
        return context;
    }

    public List<CartModel> getCategoryItemList() {
        return categoryItemList;
    }

    public CartMainRecycleAdapter(Context context, List<CartModel> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }
    @NonNull
    @Override
    public CategoryItemRecyclerAdapter.CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemRecyclerAdapter.CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemRecyclerAdapter.CategoryItemViewHolder holder, int position) {


        holder.textViewRestaurantName.setText("WOOORKED");
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {

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
