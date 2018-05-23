package com.example.pengzhang.fridgepay.view.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.data.Product;
import com.example.pengzhang.fridgepay.data.Products;
import com.example.pengzhang.fridgepay.view.bill.BillActivity;
import com.example.pengzhang.fridgepay.viewModel.MainViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements ProductAdapter.Target, MainViewModel.Target, ShopCarView.Target {

    private View rootView;
    private MainViewModel viewModel;
    private ProductAdapter adapter;
    private ShopCarView shopCarView;
    private SwipeRefreshLayout refreshLayout;
    private List<Product> productList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new MainViewModel(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        refreshLayout = rootView.findViewById(R.id.products_swiperefreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.loadProducts();
            }
        });

        shopCarView = rootView.findViewById(R.id.car_mainfl);
        shopCarView.setTarget(this);
        shopCarView.setRefreshLayout(refreshLayout);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.loadProducts();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater )
    {
        super.onCreateOptionsMenu( menu, inflater );
        inflater.inflate( R.menu.menu_main, menu );
    }

    @Override
    public boolean onOptionsItemSelected( final MenuItem item )
    {
        switch (item.getItemId())
        {
            case R.id.menu_bill:
                Intent intent = new Intent(getActivity(), BillActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    @Override
    public void showProductList(final Products products) {
        refreshLayout.setRefreshing(false);
        RecyclerView recyclerView = rootView.findViewById(R.id.products_recycler);
        adapter = new ProductAdapter(this, products);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAddButtonClicked(Product product) {
        updateShoppingCar(product);
    }

    @Override
    public void onSubButtonClicked(Product product) {
        updateShoppingCar(product);
    }

    @Override
    public void onBuyButtonClicked() {
        viewModel.onBuyButtonClicked(productList);
        clearShoppingCar();
    }

    private void updateShoppingCar(Product product) {

        if (productList.contains(product)) {
            productList.remove(product);
        }
        productList.add(product);


        int totalCount = 0;
        double totalPrice = 0;
        for (Product drink : productList) {
            totalCount += drink.getSelectedCount();
            totalPrice += drink.getPrice() * drink.getSelectedCount();
        }

        BigDecimal amount = new BigDecimal(totalPrice);
        amount = amount.setScale(1, BigDecimal.ROUND_HALF_UP);

        shopCarView.showBadge(totalCount);
        shopCarView.updateAmount(amount);
    }

    private void clearShoppingCar() {
        for (Product product : productList) {
            product.setSelectedCount(0);
        }
        adapter.notifyDataSetChanged();
        productList.clear();
        BigDecimal amount = new BigDecimal(0);
        amount = amount.setScale(1, BigDecimal.ROUND_HALF_UP);
        shopCarView.showBadge(0);
        shopCarView.updateAmount(amount);
    }
}
