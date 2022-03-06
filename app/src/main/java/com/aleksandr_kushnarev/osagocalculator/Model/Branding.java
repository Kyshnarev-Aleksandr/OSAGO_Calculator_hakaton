package com.aleksandr_kushnarev.osagocalculator.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Branding {
    @SerializedName("fontColor")
    @Expose
    private String fontColor;
    @SerializedName("backgroundColor")
    @Expose
    private String backgroundColor;
    @SerializedName("iconTitle")
    @Expose
    private String iconTitle;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bankLogoUrlPDF")
    @Expose
    private String bankLogoUrlPDF;
    @SerializedName("bankLogoUrlSVG")
    @Expose
    private String bankLogoUrlSVG;

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getIconTitle() {
        return iconTitle;
    }

    public void setIconTitle(String iconTitle) {
        this.iconTitle = iconTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankLogoUrlPDF() {
        return bankLogoUrlPDF;
    }

    public void setBankLogoUrlPDF(String bankLogoUrlPDF) {
        this.bankLogoUrlPDF = bankLogoUrlPDF;
    }

    public String getBankLogoUrlSVG() {
        return bankLogoUrlSVG;
    }

    public void setBankLogoUrlSVG(String bankLogoUrlSVG) {
        this.bankLogoUrlSVG = bankLogoUrlSVG;
    }
}
