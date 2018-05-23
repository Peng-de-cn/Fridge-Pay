package com.example.pengzhang.fridgepay.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products
{

   @SerializedName("version")
   @Expose
   private Integer version;
   @SerializedName("updatedAt")
   @Expose
   private String updatedAt;
   @SerializedName("imagePath")
   @Expose
   private String imagePath;
   @SerializedName("placeHolderImagePath")
   @Expose
   private String placeHolderImagePath;
   @SerializedName("currency")
   @Expose
   private String currency;
   @SerializedName("contentVolumeUnit")
   @Expose
   private String contentVolumeUnit;
   @SerializedName("products")
   @Expose
   private List<Product> products = null;

   public Integer getVersion() {
      return version;
   }

   public void setVersion(Integer version) {
      this.version = version;
   }

   public String getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public String getPlaceHolderImagePath() {
      return placeHolderImagePath;
   }

   public void setPlaceHolderImagePath(String placeHolderImagePath) {
      this.placeHolderImagePath = placeHolderImagePath;
   }

   public String getCurrency() {
      return currency;
   }

   public void setCurrency(String currency) {
      this.currency = currency;
   }

   public String getContentVolumeUnit() {
      return contentVolumeUnit;
   }

   public void setContentVolumeUnit(String contentVolumeUnit) {
      this.contentVolumeUnit = contentVolumeUnit;
   }

   public List<Product> getProducts() {
      return products;
   }

   public void setProducts(List<Product> products) {
      this.products = products;
   }
}



