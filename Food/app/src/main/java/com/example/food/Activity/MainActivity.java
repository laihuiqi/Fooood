package com.example.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.food.Adapter.CategoryAdapter;
import com.example.food.Adapter.PopularAdapter;
import com.example.food.Domain.CategoryDomain;
import com.example.food.Domain.PopularDomain;
import com.example.food.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategory, recyclerViewPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();

        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory = findViewById(R.id.view1);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "https://clipartix.com/wp-content/uploads/2017/07/Pizza-0-images-about-clipart-on.jpg"));
        categoryList.add(new CategoryDomain("Burger", "https://th.bing.com/th/id/R.f93fa330c3d76ccb5b0fa94e6d10e3cf?rik=D6s5nYsGhA%2brPw&riu=http%3a%2f%2fclipart-library.com%2fimg1%2f1241302.png&ehk=RWmHmWcjZXLf5gL%2bxmHq%2fwEw3lARYYB73xRVPEOnbgo%3d&risl=&pid=ImgRaw&r=0"));
        categoryList.add(new CategoryDomain("Beverage", "https://i.pinimg.com/originals/9d/34/52/9d3452913c0cc10f6770c2410c529dd3.png"));
        categoryList.add(new CategoryDomain("Korean", "https://th.bing.com/th/id/OIP.10chLi3XHrOy17WuCp8YKQHaHa?pid=ImgDet&rs=1"));
        categoryList.add(new CategoryDomain("Western", "https://th.bing.com/th/id/R.8557310eb125b18e3675617a75d0465a?rik=bsHtHSv8dQZRZQ&riu=http%3a%2f%2fclipart-library.com%2fimg%2f1002670.png&ehk=7usGJK15TApcyFVZEIuhPRvE4QSRc2L%2fuq%2fQHOLQWCg%3d&risl=&pid=ImgRaw&r=0"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategory.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopular = findViewById(R.id.view2);
        recyclerViewPopular.setLayoutManager(linearLayoutManager);

        ArrayList<PopularDomain> popularList = new ArrayList<>();
        popularList.add(new PopularDomain("Pepperoni", "https://th.bing.com/th/id/R.67a879ba645c64d6eafea85b9c758971?rik=hbIN4iH3gectYQ&riu=http%3a%2f%2fwww.clker.com%2fcliparts%2fe%2fU%2fp%2fT%2fe%2fQ%2fpepperoni-pizza-hi.png&ehk=kPcmxLE4bSvdXVjDdNLr0clm1e2uNzzgrgkvRQxVQNo%3d&risl=&pid=ImgRaw&r=0",
                "A classic favorite with a thin crust, tomato sauce, melted cheese, and savory slices of pepperoni.",
                20.15, 0, 4.8, 8, 350));
        popularList.add(new PopularDomain("Ramen", "https://assets.onlinelabels.com/images/clip-art/oksmith/Ramen%20(_3)-314317.png",
                "A comforting bowl of Japanese noodle soup with rich broth, tender noodles, and various toppings like sliced pork and seaweed.",
                15.45, 0, 4.2, 15, 500));
        popularList.add(new PopularDomain("XiaoLongBao", "https://media.istockphoto.com/vectors/soup-dumpling-vector-illustration-on-white-background-vector-id1182376458?k=20&m=1182376458&s=612x612&w=0&h=KoA2A1lDCzkeX0xQF81YgyuoKP8m_Oqgy27EZTt67jM=",
                "Delicate and flavorful Chinese dumplings filled with juicy meat or seafood, traditionally served with a flavorful dipping sauce.",
                10.00, 0, 4.0, 12, 300));
        popularList.add(new PopularDomain("Cocktail", "https://th.bing.com/th/id/OIP.432hA_kD-OLYrw7qABSERgHaLT?pid=ImgDet&rs=1",
                "A meticulously crafted drink blending spirits, mixers, and garnishes to create a harmonious balance of flavors and a delightful sipping experience.",
                18.30, 0, 3.5, 30, 250));
        popularList.add(new PopularDomain("Fish fillet", "https://clipground.com/images/cooked-fish-clipart-png-5.png",
                "Tender, flaky fish coated in a light batter or breadcrumbs, served with a squeeze of lemon for a refreshing touch.",
                12.85, 0, 2.8, 13, 200));

        adapter2 = new PopularAdapter(popularList);
        recyclerViewPopular.setAdapter(adapter2);
    }
}