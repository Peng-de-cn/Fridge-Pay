package com.example.pengzhang.fridgepay.model;

import android.util.Log;

import com.example.pengzhang.fridgepay.data.Product;
import com.example.pengzhang.fridgepay.data.Products;
import com.example.pengzhang.fridgepay.db.Bill;
import com.example.pengzhang.fridgepay.db.Bill_Table;
import com.example.pengzhang.fridgepay.retrofit.ProductsDataService;
import com.example.pengzhang.fridgepay.retrofit.RetrofitInstance;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    public interface Target {
        void onResponse(Products products);
    }

    private Target target;

    public MainModel(Target target) {
        this.target = target;
    }

    public void loadProducts() {
        String baseUrl = "https://projekte.raysono.com/mobile_team/fridge_pay/v1/";
        ProductsDataService service = RetrofitInstance.getRetrofitInstance(baseUrl).create(
                ProductsDataService.class);

        Call<Products> call = service.getProducts();

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(final Call<Products> call, final Response<Products> response) {
                target.onResponse(response.body());
            }

            @Override
            public void onFailure(final Call<Products> call, final Throwable t) {
                // TODO
                Log.e("zp_test", "handle error later");
            }
        });
    }

    public void saveProducts(List<Product> productList) {
        for (Product product : productList) {
            Bill bill = new Select().from(Bill.class).where(Bill_Table.id.is(product.getId())).querySingle();
            if (bill != null) {
                bill.selectedCount += product.getSelectedCount();
                bill.update();
            } else {
                bill = new Bill();
                bill.id = product.getId();
                bill.name = product.getName();
                bill.price = product.getPrice();
                bill.selectedCount = product.getSelectedCount();
                bill.save();
            }
        }
    }
}
