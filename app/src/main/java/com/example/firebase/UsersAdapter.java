
package com.example.firebase;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<com.example.firebase.UsersAdapter.MyHolder> {

    Context context;
    ArrayList<UserHelperClass> list;

    public UsersAdapter(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public com.example.firebase.UsersAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.itemuser, parent, false);
        return new MyHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull com.example.firebase.UsersAdapter.MyHolder holder, int position) {

        UserHelperClass cartmodel = list.get(position);
        holder.name.setText(cartmodel.getUsername());
        holder.phone.setText(cartmodel.getPhone());
        holder.email.setText(cartmodel.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name,email,phone;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_view_product_name);
            phone = itemView.findViewById(R.id.cart_view_price);
            email=itemView.findViewById(R.id.cart_view_quantity);

        }

    }
}
