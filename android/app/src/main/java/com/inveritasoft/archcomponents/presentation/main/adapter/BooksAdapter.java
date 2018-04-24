package com.inveritasoft.archcomponents.presentation.main.adapter;

import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.presentation.main.utils.DiffUtilCallback;
import com.inveritasoft.archcomponents.presentation.main.utils.DragAndDropHelper;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements DragAndDropHelper.ActionCompletionContract {

    private List<BookModel> booksList = new ArrayList<>();
    private ItemTouchHelper touchHelper;
    private MediatorLiveData<List<BookModel>> changedData = new MediatorLiveData<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        BookModel bookModel = booksList.get(position);
        ((BookViewHolder) holder).bind(bookModel);
        ((BookViewHolder) holder).binding.imageviewReorder.setOnTouchListener((v, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                touchHelper.startDrag(holder);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return booksList == null ? 0 : booksList.size();
    }

    public void setBooksList(List<BookModel> books) {
        this.booksList = books;
        notifyDataSetChanged();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        BookModel targetBook = booksList.get(oldPosition);
        BookModel book = new BookModel(targetBook);
        booksList.remove(oldPosition);
        booksList.add(newPosition, book);
        notifyItemMoved(oldPosition, newPosition);
        changedData.postValue(booksList);
    }

    @Override
    public void onViewSwiped(int position) {
        booksList.remove(position);
        notifyItemRemoved(position);
    }

    public MediatorLiveData<List<BookModel>> getChangedData() {
        return changedData;
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {
        this.touchHelper = touchHelper;
    }

    public void updateList(List<BookModel> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(this.booksList, newList));
        booksList.clear();
        booksList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}