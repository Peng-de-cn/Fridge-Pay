package com.example.pengzhang.fridgepay.retrofit;

import com.example.pengzhang.fridgepay.data.Products;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsDataService
{

   @GET("products.json")
   Call<Products> getProducts();
}
