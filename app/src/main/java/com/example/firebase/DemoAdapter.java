package com.example.firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DemoAdapter extends RecyclerView.Adapter<VH> {

    List<String>items;

    public DemoAdapter(List<String> items) {
        this.items=items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrating,parent,false);
        return new VH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(items.get(position));
        holder.text2.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class VH extends RecyclerView.ViewHolder{
    TextView textView;
    RatingBar ratingBar;
    TextView text2;
    private DemoAdapter adapter;
    public VH(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.nametext);
}
    public VH linkAdapter(DemoAdapter adapter){
        this.adapter=adapter;
        return this;
    }
}

