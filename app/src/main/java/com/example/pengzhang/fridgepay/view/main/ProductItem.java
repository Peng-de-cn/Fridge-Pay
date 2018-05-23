package com.example.pengzhang.fridgepay.view.main;

public class ProductItem {
    private String icon;
    private String name;
    private String price;


    public ProductItem(String icon, String name, String price) {
        this.icon = icon;
        this.name = name;
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

}
