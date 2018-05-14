package com.inveritasoft.archcomponents.presentation.main.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.db.entities.BookEntity;
import com.inveritasoft.archcomponents.presentation.main.adapter.BookModel;
import com.inveritasoft.archcomponents.repository.ArchRepository;

import java.util.ArrayList;
import java.util.List;

public class DataFragmentViewModel extends ViewModel {

    private final ArchRepository repository;

    private List<BookModel> changedBooks;

    public DataFragmentViewModel() {
        repository = App.getInstance().getRepository();
    }

    public LiveData<List<BookModel>> getBooksForUi() {
        return Transformations.map(repository.getBooks(), new Deserializer());
    }

    public void updateBooks(final List<BookModel> books) {
        changedBooks = books;
    }

    private class Deserializer implements Function<List<BookEntity>, List<BookModel>> {

        @Override
        public List<BookModel> apply(final List<BookEntity> input) {
            final List<BookModel> result = new ArrayList<>();
            for (final BookEntity bookEntity : input) {
                final BookModel bookModel = new BookModel(bookEntity.getName(), bookEntity.getBookOrder());
                result.add(bookModel);
            }
            return result;
        }
    }

    public void onSave() {
        repository.updateBooks(changedBooks);
    }
}
