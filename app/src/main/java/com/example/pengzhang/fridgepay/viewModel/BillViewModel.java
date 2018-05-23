package com.example.pengzhang.fridgepay.viewModel;

import com.example.pengzhang.fridgepay.db.Bill;
import com.example.pengzhang.fridgepay.model.BillModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BillViewModel implements BillModel.Target {

    public interface Target {
        void showBillList(List<Bill> bills);
    }

    private BillModel billModel;
    private Target target;

    public BillViewModel(Target target) {
        this.target = target;
        billModel = new BillModel(this);
    }

    public void onCreate() {
        billModel.loadPurchasedProducts();
    }

    public void deleteBill() {
        billModel.deleteBillFromDb();
    }

    @Override
    public void onResponse(List<Bill> bills) {
        Collections.sort(bills, new Comparator<Bill>() {
            @Override
            public int compare(Bill o1, Bill o2) {
                return Integer.compare(o1.selectedCount, o2.selectedCount) * -1;
            }
        });
        target.showBillList(bills);
    }
}
