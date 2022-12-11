package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firebase.Model.AllCategory;
import com.example.firebase.Model.CategoryItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    FloatingActionButton button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=findViewById(R.id.fab);

        // here we will add some dummy data to our model class
        // here we will add data to category item model class
        // added in first category
        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.rfissa,"Rfissa","22$","+21275837483","5"));
        categoryItemList.add(new CategoryItem(1, R.drawable.hrira,"Hrira","10$","+21284387873","5"));
        categoryItemList.add(new CategoryItem(1, R.drawable.tajine,"Tajine","11$","+2123772738","5"));
        categoryItemList.add(new CategoryItem(1, R.drawable.poulet,"Poulet roti","10$","+2128374870","5"));
        categoryItemList.add(new CategoryItem(1, R.drawable.pruneaux,"Tajine de boeuf aux prunes","20$","+21273478374","5"));

        // added in second category
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.khlii,"Khlii","3 $ ","+212837293","3 "));
        categoryItemList2.add(new CategoryItem(1, R.drawable.jawhara,"Jawhara","6 $","+212839329","6 "));
        categoryItemList2.add(new CategoryItem(1, R.drawable.filalya,"Rghifa de fes","7 $","+2123239873","7"));
        categoryItemList2.add(new CategoryItem(1, R.drawable.trid,"Trid au poulet","12 $","+2129838329","12 "));

        // added in 3rd category
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.tanger1,"Soupe des fruits de mer","10 $","+212637236","7"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.tanger3,"Bissara","3 $","+21232782e","4 "));
        categoryItemList3.add(new CategoryItem(1, R.drawable.tang3,"Tajine au boulettes de poisson","8 $","+21278733646","7"));
        categoryItemList3.add(new CategoryItem(1, R.drawable.hollywood1,"","","",""));
        categoryItemList3.add(new CategoryItem(1, R.drawable.hollywood1,"","","",""));
        categoryItemList3.add(new CategoryItem(1, R.drawable.hollywood1,"","","",""));

        // added in 4th category
        List<CategoryItem> categoryItemList4 = new ArrayList<>();
        categoryItemList4.add(new CategoryItem(1, R.drawable.rabat1,"Couscous aux amendes et oeufs","10 $","+2126762715","6"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.medf,"Medfouna","5 $","+212626725","5"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.loup,"Loup grille","10$","+212425212","8"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.pastilla,"la pastilla aux fruits de mer","20$","+2124525145","9"));
        categoryItemList4.add(new CategoryItem(1, R.drawable.hollywood1,"","","",""));
        categoryItemList4.add(new CategoryItem(1, R.drawable.hollywood1,"","","",""));


        // added in 5th category
        List<CategoryItem> categoryItemList5 = new ArrayList<>();
        categoryItemList5.add(new CategoryItem(1, R.drawable.kech2,"Tanjia","15 $","+2167723626","9"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.kech1,"Tajine aux poulet et olives","8 $","+882372388","9"));
        categoryItemList5.add(new CategoryItem(1, R.drawable.pruneaux, "Tajine de boeuf aux prunes","20 $","+21273763","9"));


        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Most Popular dishes ", categoryItemList));
        allCategoryList.add(new AllCategory("Ville de Fes", categoryItemList2));
        allCategoryList.add(new AllCategory("Nord du Maroc", categoryItemList3));
        allCategoryList.add(new AllCategory("L'Est du Maroc ", categoryItemList4));
        allCategoryList.add(new AllCategory("Ville de Marrakech", categoryItemList5));

        setMainCategoryRecycler(allCategoryList);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MyCart.class);
                startActivity(intent);
            }
        });
    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList){

        mainCategoryRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);

    }


}