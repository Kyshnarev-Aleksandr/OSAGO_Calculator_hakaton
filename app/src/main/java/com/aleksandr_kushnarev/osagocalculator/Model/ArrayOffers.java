package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArrayOffers {
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = null;

    @SerializedName("actionText")
    @Expose
    private String actionText;

    public List<Offer> getOffers() {
        return offers;
    }

    public String getActionText() {
        return actionText;
    }
}
