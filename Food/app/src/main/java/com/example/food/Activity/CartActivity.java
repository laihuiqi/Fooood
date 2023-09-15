package com.example.food.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.Adapter.CartAdapter;
import com.example.food.Domain.PopularDomain;
import com.example.food.Helper.ManagementCart;
import com.example.food.Interface.ChangeItemCountListener;
import com.example.food.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView foodFee, deliveryFee, totalFee, taxFee, empty;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        bottomNavigation();
        try {
            if (!managementCart.getListCart().isEmpty()) {
                for (PopularDomain item : managementCart.getListCart()) {
                    if (item.getNumberInCart() > 0) {
                        calculateCard();
                        break;
                    }
                }
            }
        } catch (NullPointerException ne) {
            // do nothing
        }
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(view -> startActivity(new Intent(CartActivity.this, MainActivity.class)));

        cartBtn.setOnClickListener(view -> startActivity(new Intent(CartActivity.this, CartActivity.class)));
    }

    private void initView() {
        foodFee = findViewById(R.id.foodFee);
        deliveryFee = findViewById(R.id.deliveryFee);
        totalFee = findViewById(R.id.totalFee);
        taxFee = findViewById(R.id.tax);
        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
        empty = findViewById(R.id.empty);
    }

    private void calculateCard() {
        double taxPercentage = 0.2;
        double delivery = 3.99;

        tax = Math.round(managementCart.getTotalFee() * taxPercentage * 100.00) / 100.00;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.00) / 100.00;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.00) / 100.00;

        foodFee.setText("$" + itemTotal);
        taxFee.setText("$" + tax);
        deliveryFee.setText("$" + delivery);
        totalFee.setText("$" + total);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(
                managementCart.getListCart(), this, () -> calculateCard());

        recyclerViewList.setAdapter(adapter);
        boolean isEmptyCart = true;
        try {
            if (!managementCart.getListCart().isEmpty()) {
                for (PopularDomain item : managementCart.getListCart()) {
                    if (item.getNumberInCart() > 0) {
                        empty.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
                        isEmptyCart = false;
                        break;
                    }
                }
                if (isEmptyCart) {
                    empty.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                }
            }
        } catch (NullPointerException ne) {
            empty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }
    }
}
