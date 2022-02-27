package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Factor {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("headerValue")
    @Expose
    private String headerValue;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("detailText")
    @Expose
    private String detailText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

}
