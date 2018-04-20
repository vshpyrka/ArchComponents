package com.inveritasoft.archcomponents.presentation.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.inveritasoft.archcomponents.databinding.BookItemBinding;

public class BookViewHolder extends RecyclerView.ViewHolder {

    public final BookItemBinding binding;

    public BookViewHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    public void bind(@NonNull final BookModel entity) {
        binding.setItem(entity);
        binding.executePendingBindings();
    }
}
