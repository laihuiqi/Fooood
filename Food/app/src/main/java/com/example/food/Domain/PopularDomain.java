package com.example.food.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String pic;
    private String descriptor;
    private Double fee;
    private int numberInCart;
    private double star;
    private int time;
    private int calories;

    public PopularDomain(String title, String pic, String descriptor, Double fee, int numberInCart,
                         double star, int time, int calories) {
        this.title = title;
        this.pic = pic;
        this.descriptor = descriptor;
        this.fee = fee;
        this.numberInCart = numberInCart;
        this.star = star;
        this.time = time;
        this.calories = calories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
