package com.inveritasoft.archcomponents.presentation.main.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.databinding.DataFragmentBinding;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.presentation.main.adapter.BooksAdapter;
import com.inveritasoft.archcomponents.presentation.main.utils.DragAndDropHelper;
import com.inveritasoft.archcomponents.presentation.main.viewmodel.DataFragmentViewModel;

import java.util.List;

public class DataFragment extends Fragment {

    public static final String TAG = "DataFragment";

    private DataFragmentViewModel viewModel;

    private DataFragmentBinding binding;

    private BooksAdapter adapter;

    public static DataFragment newInstance() {
        return new DataFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.data_fragment, container,
                false);
        viewModel = ViewModelProviders.of(this).get(DataFragmentViewModel.class);
        binding.setViewModel(viewModel);
        adapter = new BooksAdapter();
        DragAndDropHelper swipeAndDragHelper = new DragAndDropHelper(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeAndDragHelper);
        adapter.setTouchHelper(touchHelper);
        initRecyclerView(touchHelper);
        subscribeForData();
        return binding.getRoot();
    }

    private void initRecyclerView(ItemTouchHelper touchHelper) {
        binding.dataRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.dataRecycler.setAdapter(adapter);
        touchHelper.attachToRecyclerView(binding.dataRecycler);
    }

    private void subscribeForData() {
        viewModel.getBooksForUi().observe(this, this::showResults);
        adapter.getChangedData().observe(this, this::updateResults);
    }

    private void updateResults(final List<BookModel> books) {
        viewModel.updateBooks(books);
    }

    private void showResults(final List<BookModel> bookModels) {
        if (bookModels == null || bookModels.isEmpty()) {
            adapter.setBooksList(bookModels);
        } else {
            adapter.updateList(bookModels);
        }
    }
}
