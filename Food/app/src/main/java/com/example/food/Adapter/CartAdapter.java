package com.example.food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food.Domain.PopularDomain;
import com.example.food.Helper.ManagementCart;
import com.example.food.Interface.ChangeItemCountListener;
import com.example.food.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<PopularDomain> selectedFoodList;
    private ManagementCart managementCart;
    ChangeItemCountListener changeItemCountListener;

    public CartAdapter(ArrayList<PopularDomain> selectedFoodList, Context context,
                       ChangeItemCountListener changeItemCountListener) {
        this.selectedFoodList = selectedFoodList;
        this.managementCart = new ManagementCart(context);
        this.changeItemCountListener = changeItemCountListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(selectedFoodList.get(position).getTitle());
        holder.feeEachItem.setText("$" + selectedFoodList.get(position).getFee());
        holder.totalEachItem.setText("$" + Math.round(selectedFoodList.get(position).getFee()
                * selectedFoodList.get(position).getNumberInCart() * 100.00) / 100.00);
        holder.num.setText(String.valueOf(selectedFoodList.get(position).getNumberInCart()));

        String picUrl = selectedFoodList.get(position).getPic();

        Glide.with(holder.itemView.getContext()).load(picUrl).into(holder.pic);

        holder.add.setOnClickListener(v ->
                managementCart.increaseFoodCount(selectedFoodList, position, () -> {
            notifyDataSetChanged();
            changeItemCountListener.changed();
        }));

        holder.sub.setOnClickListener(v ->
                managementCart.decreaseFoodCount(selectedFoodList, position, () -> {
                    notifyDataSetChanged();
                    changeItemCountListener.changed();
                }));
    }

    @Override
    public int getItemCount() {
        return selectedFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        ImageView add;
        ImageView sub;
        TextView totalEachItem;
        TextView num;
        TextView feeEachItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            add = itemView.findViewById(R.id.plusCardBtn);
            sub = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
