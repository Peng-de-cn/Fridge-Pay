package com.example.pengzhang.fridgepay.view.bill;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pengzhang.fridgepay.R;

import java.math.BigDecimal;

public class BillView extends FrameLayout
{
    public interface Target {
        void onPayButtonClicked();
    }


    private Target target;

    public BillView(@NonNull Context context) {
        super(context);
    }

    public BillView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void updateAmount(BigDecimal amount) {
        Button payButton = findViewById(R.id.payButton);
        LinearLayout amountLayout = findViewById(R.id.amount_container);
        TextView amountTextView = findViewById(R.id.tv_amount);
        payButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (target != null) {
                    target.onPayButtonClicked();
                }
            }
        });

        if (amount.compareTo(new BigDecimal(0.0)) == 0) {
            payButton.setEnabled(false);
            amountLayout.setVisibility(View.GONE);
        } else {
            payButton.setEnabled(true);
            amountLayout.setVisibility(View.VISIBLE);
        }
        amountTextView.setText("€" + amount);
    }
}

