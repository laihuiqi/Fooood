package com.example.food.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.food.Domain.PopularDomain;
import com.example.food.Interface.ChangeItemCountListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    private static ArrayList<PopularDomain> food = new ArrayList<>();

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(PopularDomain item) {
        ArrayList<PopularDomain> foodList = getListCart();
        boolean isExist = false;
        int itemNum = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getTitle().equals(item.getTitle())) {
                isExist = true;
                itemNum = i;
                break;
            }
        }

        if (isExist) {
            foodList.get(itemNum).setNumberInCart(item.getNumberInCart() + 1);
        } else {
            foodList.add(item);
        }

        //tinyDB.putListObject("CartList", foodList);
        food = foodList;
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListCart() {
        //return TinyDB.getListObject("CardList);
        return food;
    }

    public void decreaseFoodCount(ArrayList<PopularDomain> foodList, int position,
                             ChangeItemCountListener changeItemCountListener) {
        if (foodList.get(position).getNumberInCart() == 1) {
            foodList.remove(position);
        } else {
            foodList.get(position).setNumberInCart(foodList.get(position).getNumberInCart() - 1);
        }
        food = foodList;
        changeItemCountListener.changed();
    }

    public void increaseFoodCount(ArrayList<PopularDomain> foodList, int position,
                                  ChangeItemCountListener changeItemCountListener) {
        foodList.get(position).setNumberInCart(foodList.get(position).getNumberInCart() + 1);
        food = foodList;
        changeItemCountListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<PopularDomain> foodList = getListCart();
        double fee = 0;
        for (PopularDomain food:foodList) {
            fee += food.getFee() * food.getNumberInCart();
        }

        return fee;
    }
}
