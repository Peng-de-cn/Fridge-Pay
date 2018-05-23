package com.example.pengzhang.fridgepay.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pengzhang.fridgepay.R;

import java.math.BigDecimal;

public class ShopCarView extends FrameLayout
{
   public interface Target {
      void onBuyButtonClicked();
   }


   private Button buyButton;
   private LinearLayout amountLayout;
   private TextView amount;
   public ImageView shoppingCar;
   public TextView carBadge;
   private Target target;
   private SwipeRefreshLayout refreshLayout;

   public ShopCarView(@NonNull Context context) {
      super(context);
   }

   public ShopCarView(@NonNull Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
      super.onLayout(changed, left, top, right, bottom);
      if (shoppingCar == null) {
         shoppingCar = findViewById(R.id.shoppingCar);
         carBadge = findViewById(R.id.car_badge);
         amountLayout = findViewById(R.id.amount_container);
         amount = findViewById(R.id.tv_amount);
         buyButton = findViewById(R.id.buyButton);
         buyButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               if (target != null) {
                  target.onBuyButtonClicked();
               }
            }
         });
      }
   }

   public void setTarget(Target target) {
      this.target = target;
   }

   public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
      this.refreshLayout = refreshLayout;
   }

   public void updateAmount(BigDecimal amount) {
      if (amount.compareTo(new BigDecimal(0.0)) == 0) {
         buyButton.setEnabled(false);
         amountLayout.setVisibility(View.GONE);
         shoppingCar.setImageResource(R.drawable.shop_car_empty);
      } else {
         buyButton.setEnabled(true);
         amountLayout.setVisibility(View.VISIBLE);
         shoppingCar.setImageResource(R.drawable.shop_car);
      }
      this.amount.setText("€" + amount);
   }

   public void showBadge(int total) {
      if (total > 0) {
         carBadge.setVisibility(View.VISIBLE);
         carBadge.setText(total + "");
         if (refreshLayout != null) {
            refreshLayout.setEnabled(false);
         }
      } else {
         carBadge.setVisibility(View.INVISIBLE);
         if (refreshLayout != null) {
            refreshLayout.setEnabled(true);
         }
      }
   }
}

