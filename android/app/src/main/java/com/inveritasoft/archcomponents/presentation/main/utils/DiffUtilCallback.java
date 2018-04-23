package com.inveritasoft.archcomponents.presentation.main.utils;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.inveritasoft.archcomponents.presentation.main.adapter.BaseView;

import java.util.List;

/**
 * Created by Oleksandr Kryvoruchko on 23.04.2018.
 */
public class DiffUtilCallback extends DiffUtil.Callback {

    List<BaseView> oldViewList;
    List<BaseView> newViewList;

    public DiffUtilCallback(List<BaseView> oldViewList, List<BaseView> newViewList) {
        this.oldViewList = oldViewList;
        this.newViewList = newViewList;
    }

    @Override
    public int getOldListSize() {
        return oldViewList == null ? 0 : oldViewList.size();
    }

    @Override
    public int getNewListSize() {
        return newViewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldViewList.get(oldItemPosition).getOrder() == newViewList.get(newItemPosition).getOrder();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldViewList.get(oldItemPosition).equals(newViewList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
