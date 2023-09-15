package com.example.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.food.Domain.PopularDomain;
import com.example.food.Helper.ManagementCart;
import com.example.food.R;

public class ShowDetailActivity extends AppCompatActivity {

    private ConstraintLayout addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, starTxt, caloriesTxt, timeTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);

        initView();

        getBundle();
    }

    private void getBundle() {
        object = (PopularDomain)getIntent().getSerializableExtra("object");

        String picUrl = object.getPic();

        Glide.with(this).load(picUrl).into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDescriptor());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloriesTxt.setText(object.getCalories() + " cals");
        starTxt.setText(String.valueOf(object.getStar()));
        timeTxt.setText(String.valueOf(object.getTime()) + " mins");
        totalPriceTxt.setText("$" + Math.round(numberOrder * object.getFee() * 100.00) / 100.00);

        plusBtn.setOnClickListener(view -> {
            numberOrder++;
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("$" + Math.round(numberOrder * object.getFee() * 100.00) / 100.00);
        });

        minusBtn.setOnClickListener(view -> {
            if (numberOrder > 1) {
                numberOrder--;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("$" + Math.round(numberOrder * object.getFee() * 100.00) / 100.00);
        });

        addToCartBtn.setOnClickListener(view -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.title);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.description);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCardBtn);
        minusBtn = findViewById(R.id.minusCardBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        starTxt = findViewById(R.id.starTxt);
        caloriesTxt = findViewById(R.id.caloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
    }
}