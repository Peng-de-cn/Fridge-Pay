package com.example.pengzhang.fridgepay.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Comparable<Product>
{
   @SerializedName( "id")
   @Expose
   private Integer id;
   @SerializedName("type")
   @Expose
   private String type;
   @SerializedName("name")
   @Expose
   private String name;
   @SerializedName("contentVolume")
   @Expose
   private Integer contentVolume;
   @SerializedName("price")
   @Expose
   private Double price;
   @SerializedName("imageName")
   @Expose
   private String imageName;

   private int selectedCount;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getContentVolume() {
      return contentVolume;
   }

   public void setContentVolume(Integer contentVolume) {
      this.contentVolume = contentVolume;
   }

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public String getImageName() {
      return imageName;
   }

   public void setImageName(String imageName) {
      this.imageName = imageName;
   }

   public int getSelectedCount() {
      return selectedCount;
   }

   public void setSelectedCount(int selectedCount) {
      this.selectedCount = selectedCount;
   }

   @Override
   public int compareTo( @NonNull final Product product )
   {
      return this.getName().toLowerCase().compareTo( product.getName().toLowerCase() );
   }
}