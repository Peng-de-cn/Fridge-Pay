package com.example.pengzhang.fridgepay.viewModel;

import com.example.pengzhang.fridgepay.data.Product;
import com.example.pengzhang.fridgepay.data.Products;
import com.example.pengzhang.fridgepay.model.MainModel;

import java.util.Collections;
import java.util.List;

public class MainViewModel implements MainModel.Target {
    public interface Target {
        void showProductList(Products products);
    }

    private MainModel mainModel;
    private Target target;

    public MainViewModel(Target target) {
        this.target = target;
        mainModel = new MainModel(this);
    }

    public void loadProducts() {
        mainModel.loadProducts();
    }

    public void onBuyButtonClicked(List<Product> productList) {
        mainModel.saveProducts(productList);
    }

    @Override
    public void onResponse(final Products products) {
        List<Product> productList = products.getProducts();
        Collections.sort(productList);
        target.showProductList(products);
    }


}
