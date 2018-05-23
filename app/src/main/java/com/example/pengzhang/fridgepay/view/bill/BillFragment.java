package com.example.pengzhang.fridgepay.view.bill;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.db.Bill;
import com.example.pengzhang.fridgepay.view.AlertDialogFragment;
import com.example.pengzhang.fridgepay.viewModel.BillViewModel;

import java.math.BigDecimal;
import java.util.List;

public class BillFragment extends Fragment implements BillViewModel.Target, BillView.Target {

    public static int REQUEST_DIALOG_PAY = 10001;

    private View rootView;
    private BillViewModel viewModel;
    private BillView billView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new BillViewModel(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bill, container, false);

        billView = rootView.findViewById(R.id.bill_mainfl);
        billView.setTarget(this);

        viewModel.onCreate();
        return rootView;
    }

    @Override
    public void showBillList(List<Bill> bills) {
        RecyclerView recyclerView = rootView.findViewById(R.id.bought_products_recycler);
        BoughtProductAdapter adapter = new BoughtProductAdapter(bills);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        updateBill(bills);
    }

    private void updateBill(List<Bill> bills) {
        double totalPrice = 0;
        for (Bill bill: bills) {
            totalPrice += bill.price * bill.selectedCount;
        }

        BigDecimal amount = new BigDecimal(totalPrice);
        amount = amount.setScale(1, BigDecimal.ROUND_HALF_UP);
        billView.updateAmount(amount);

    }

    @Override
    public void onPayButtonClicked() {
        AlertDialogFragment dialogFragment = (AlertDialogFragment) AlertDialogFragment
                .newInstance( getString( R.string.dialog_pay_title ),
                        getString( R.string.dialog_body_title ), "" );
        dialogFragment
                .setTargetFragment( this, REQUEST_DIALOG_PAY);
        dialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_DIALOG_PAY) {
            viewModel.deleteBill();
            getActivity().finish();
        }
    }
}
