package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrayHeader {
    @SerializedName("factors")
    @Expose
    private Factor[] factors;

    public Factor[] getFactors() {
        return factors;
    }
}
