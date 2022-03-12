package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArrayHeader {
    @SerializedName("factors")
    @Expose
    private List<Factor> factors;

    public List<Factor> getFactors() {
        return factors;
    }
}
