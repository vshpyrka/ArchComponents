package com.inveritasoft.archcomponents.presentation.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inveritasoft.archcomponents.databinding.CategoryItemBinding;


public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public final CategoryItemBinding binding;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    public void bind(@NonNull final CategoryModel entity) {
        binding.setItem(entity);
        binding.executePendingBindings();
    }

}
