package com.example.firebase;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.firebase.Model.Food;
import com.example.firebase.Model.SliderAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    RecyclerView popularRec;
    RecyclerView promoRec;
    ImageView food_menu;


    //Slider view
    SliderView sliderView;
    int[] images = {
            R.drawable.abc,
            R.drawable.abcd,
            R.drawable.abcde,
            R.drawable.efgh};

    //popular items
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Food> popularModel;
    PopularAdapter popularAdapter;

    //////////////SEARCH BOX
    EditText search_box;

    private List<Food> viewAllModelList;
    private RecyclerView recyclerViewSearch;
    private DatabaseReference ProductsRef;


    private Query query1, query2;


    //promo items

    List<Food> promoList;
    promoAdapter promoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ///Slider
        sliderView = findViewById(R.id.imageSlider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        ////
        food_menu = findViewById(R.id.food_menu);
        food_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity5.this, MainActivity4.class));
            }
        });

        popularRec = findViewById(R.id.main_recycler2);
        promoRec = findViewById(R.id.main_recycler3);
//Populaire
        popularRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        popularModel = new ArrayList<>();
        popularAdapter = new PopularAdapter(this, popularModel);
        popularRec.setAdapter(popularAdapter);

        db.collection("popularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Food food = document.toObject(Food.class);
                                popularModel.add(food);
                                popularAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });

        //Promoo
        promoRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        promoList = new ArrayList<>();
        promoAdapter = new promoAdapter(this, promoList);
        promoRec.setAdapter(promoAdapter);

        db.collection("PromoDuJour")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Food food = document.toObject(Food.class);
                                promoList.add(food);
                                promoAdapter.notifyDataSetChanged();
                            }
                        } else {

                        }
                    }
                });


///////////////////////////////////////////////////////////////////////////////////////////////////
        recyclerViewSearch = findViewById(R.id.search_rec);
        search_box = findViewById(R.id.search_box);
        //viewAllModelList = new ArrayList<>();
        // viewAllAdapter= new ViewAllAdapter(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        //recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    // viewAllModelList.clear();
                    // viewAllAdapter.notifyDataSetChanged();
                } else {
                    searchProduct(s.toString());

                }
            }
        });
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("dish");


    }

    private void searchProduct(String type)
    {
        //Pour Lister les produits en firebase
        query1=ProductsRef.orderByChild("Name").equalTo(type);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datas: snapshot.getChildren()){
                    String pname=datas.child("Name").getValue().toString();
                    String price=datas.child("price").getValue().toString();
                    FirebaseRecyclerOptions<Food> options =
                            new FirebaseRecyclerOptions.Builder<Food>()
                                    .setQuery(query1, Food.class)
                                    .build();
                    FirebaseRecyclerAdapter<Food, ProductViewHolder> adapter =
                            new FirebaseRecyclerAdapter<Food, ProductViewHolder>(options) {
                                @Override
                                protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Food model)
                                {
                                    holder.txtProductName.setText(model.getName());
                                    holder.txtProductPrice.setText(model.getPrice());
                                    Picasso.get().load(model.getImage()).into(holder.imageView);
                                      holder.itemView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent=new Intent(MainActivity5.this, Detail_Dish.class);
                                            intent.putExtra("Name",model.getName());
                                            startActivity(intent);
                                        }
                                    });

                                }

                                @NonNull
                                @Override
                                public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                                {
                                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_items, parent, false);
                                    ProductViewHolder holder = new ProductViewHolder(view);
                                    return holder;
                                }
                            };
                    recyclerViewSearch.setAdapter(adapter);
                    adapter.startListening();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}