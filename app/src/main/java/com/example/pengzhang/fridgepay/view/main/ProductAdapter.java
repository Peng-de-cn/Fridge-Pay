package com.example.pengzhang.fridgepay.view.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.binding.BindingHolder;
import com.example.pengzhang.fridgepay.data.Product;
import com.example.pengzhang.fridgepay.data.Products;
import com.example.pengzhang.fridgepay.databinding.ItemProductBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<BindingHolder<ItemProductBinding>>
{

   public interface Target
   {
      void onAddButtonClicked(Product product);
      void onSubButtonClicked(Product product);
   }

   private Target target;
   private Products products;

   public ProductAdapter(Target target, Products products )
   {
      this.target = target;
      this.products = products;
   }

   @Override
   public BindingHolder<ItemProductBinding> onCreateViewHolder( final ViewGroup parent,
      final int viewType )
   {
      LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
      View view = inflater.inflate( R.layout.item_product, parent, false );
      return new BindingHolder<>( view );
   }

   @Override
   public void onBindViewHolder( final BindingHolder<ItemProductBinding> holder,
      final int position )
   {
      final int[] count = {0};
      final List<Product> productList = products.getProducts();
      String imageUrl = productList.get( position ).getImageName().length() != 0 ? products.getImagePath() + productList.get( position ).getImageName() : products.getPlaceHolderImagePath();
      String currency = products.getCurrency().toLowerCase().equals("eur") ? "€" : "$";
      String price = currency + " " + productList.get( position ).getPrice();
      ProductItem item = new ProductItem( imageUrl, productList.get( position ).getName(), price );
      holder.binding().setItem( item );
      holder.binding().executePendingBindings();

      if (productList.get(position).getSelectedCount() > 0) {
         holder.binding().subButton.setVisibility( View.VISIBLE );
         holder.binding().count.setVisibility( View.VISIBLE );
      } else {
         holder.binding().subButton.setVisibility( View.GONE );
         holder.binding().count.setVisibility( View.GONE );
      }


      holder.binding().addButton.setOnClickListener( new View.OnClickListener()
      {
         @Override
         public void onClick( final View v )
         {
            if (count[0] == 0) {
               holder.binding().subButton.setVisibility( View.VISIBLE );
               holder.binding().count.setVisibility( View.VISIBLE );
            }
            count[0]++;
            holder.binding().count.setText(count[0] + "");
            Product product = productList.get(holder.getAdapterPosition());
            product.setSelectedCount(count[0]);
            target.onAddButtonClicked(productList.get(holder.getAdapterPosition()));
         }
      } );

      holder.binding().subButton.setOnClickListener( new View.OnClickListener()
      {
         @Override
         public void onClick( final View v )
         {
            if (count[0] == 1) {
               holder.binding().subButton.setVisibility( View.GONE );
               holder.binding().count.setVisibility( View.GONE );
            }
            count[0]--;
            holder.binding().count.setText(count[0] == 0 ? "1" : count[0] + "");
            Product product = productList.get(holder.getAdapterPosition());
            product.setSelectedCount(count[0]);
            target.onSubButtonClicked(product);
         }
      } );
   }

   @Override
   public int getItemCount()
   {
      return products.getProducts().size();
   }

   @Override
   public int getItemViewType(int position) {
      return position;
   }
}
