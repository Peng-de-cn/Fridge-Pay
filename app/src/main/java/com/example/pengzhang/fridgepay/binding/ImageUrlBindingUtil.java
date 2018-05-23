package com.example.pengzhang.fridgepay.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageUrlBindingUtil
{
   @BindingAdapter( "srcCompat")
   public static void bindImageResource(ImageView imageView, String url) {
      Picasso.get().load( url ).into( imageView );
   }
}
