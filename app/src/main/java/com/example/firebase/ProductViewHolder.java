package com.example.firebase;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;
import com.example.firebase.Model.ItemClickListner;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtRate,txtProductCategory, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;


    public ProductViewHolder(View itemView)
    {
        super(itemView);

        //product_details_send_button = (Button) itemView.findViewById(R.id.product_details_send_button);

        imageView = (ImageView) itemView.findViewById(R.id.item_image);
        txtProductName = (TextView) itemView.findViewById(R.id.textViewRestaurantName);
        txtProductPrice = (TextView) itemView.findViewById(R.id.textprice);
        txtRate=(TextView) itemView.findViewById(R.id.textRating);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}