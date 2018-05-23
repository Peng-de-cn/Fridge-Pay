package com.example.pengzhang.fridgepay.model;

import com.example.pengzhang.fridgepay.db.Bill;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class BillModel {

    public interface Target {
        void onResponse(List<Bill> bills);
    }

    private Target target;

    public BillModel(Target target) {
        this.target = target;
    }

    public void loadPurchasedProducts() {
        List<Bill> bills = new Select().from(Bill.class).queryList();
        target.onResponse(bills);
    }

    public void deleteBillFromDb() {
        Delete.tables(Bill.class);
    }

}
