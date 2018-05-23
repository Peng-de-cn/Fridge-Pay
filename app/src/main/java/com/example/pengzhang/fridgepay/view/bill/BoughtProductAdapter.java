package com.example.pengzhang.fridgepay.view.bill;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.binding.BindingHolder;
import com.example.pengzhang.fridgepay.databinding.ItemBoughtproductBinding;
import com.example.pengzhang.fridgepay.db.Bill;

import java.util.List;

public class BoughtProductAdapter extends RecyclerView.Adapter<BindingHolder<ItemBoughtproductBinding>> {

    private List<Bill> bills;

    public BoughtProductAdapter(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public BindingHolder<ItemBoughtproductBinding> onCreateViewHolder(final ViewGroup parent,
                                                                      final int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_boughtproduct, parent, false);
        return new BindingHolder<>(view);
    }

    @Override
    public void onBindViewHolder(final BindingHolder<ItemBoughtproductBinding> holder,
                                 final int position) {
        Bill bill = bills.get(position);
        BoughtProductItem item = new BoughtProductItem(bill.name, "€" + bill.price, String.valueOf(bill.selectedCount));
        holder.binding().setItem(item);
        holder.binding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
