package com.example.pengzhang.fridgepay.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

   private final T binding;

   public BindingHolder(View itemView) {
      super(itemView);
      this.binding = DataBindingUtil.bind(itemView);
   }

   @NonNull
   public T binding() {
      return binding;
   }
}
