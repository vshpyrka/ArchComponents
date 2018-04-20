package com.inveritasoft.archcomponents.presentation.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.inveritasoft.archcomponents.R;
import com.inveritasoft.archcomponents.presentation.main.utils.DragAndDropHelper;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        DragAndDropHelper.ActionCompletionContract {
    private static final int BOOK_TYPE = 0;
    private static final int CATEGORY_TYPE = 1;
    private static final String BOOK = "book";
    private List<BaseView> booksList;
    private ItemTouchHelper touchHelper;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case BOOK_TYPE:
                view = inflater.inflate(R.layout.book_item, parent, false);
                return new BookViewHolder(view);
            case CATEGORY_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category_item, parent, false);
                return new CategoryViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_item, parent, false);
                return new BookViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == BOOK_TYPE) {
            BookModel bookModel = (BookModel) booksList.get(position);
            ((BookViewHolder) holder).bind(bookModel);
            ((BookViewHolder) holder).binding.imageviewReorder.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        touchHelper.startDrag(holder);
                    }
                    return false;
                }
            });
        } else {
            CategoryModel categoryModel = (CategoryModel) booksList.get(position);
            ((CategoryViewHolder) holder).bind(categoryModel);
        }
    }

    @Override
    public int getItemCount() {
        return booksList == null ? 0 : booksList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (booksList.get(position).getType().equals(BOOK)) {
            return BOOK_TYPE;
        } else {
            return CATEGORY_TYPE;
        }
    }

    public void setBooksList(List<BaseView> usersList) {
        this.booksList = usersList;
        notifyDataSetChanged();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        BaseView targetBook = booksList.get(oldPosition);
        BaseView book = new BookModel(targetBook);
        booksList.remove(oldPosition);
        booksList.add(newPosition, book);
        notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public void onViewSwiped(int position) {
        booksList.remove(position);
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {

        this.touchHelper = touchHelper;
    }
}