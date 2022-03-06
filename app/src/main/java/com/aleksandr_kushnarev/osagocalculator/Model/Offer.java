package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rating")
    @Expose
    private double rating;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("branding")
    @Expose
    private Branding branding;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Branding getBranding() {
        return branding;
    }

    public void setBranding(Branding branding) {
        this.branding = branding;
    }
}
