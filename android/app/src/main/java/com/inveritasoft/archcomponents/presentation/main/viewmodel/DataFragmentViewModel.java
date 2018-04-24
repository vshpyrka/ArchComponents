package com.inveritasoft.archcomponents.presentation.main.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.inveritasoft.archcomponents.App;
import com.inveritasoft.archcomponents.db.entities.CategoryWithBooks;
import com.inveritasoft.archcomponents.presentation.main.adapter.BaseView;
import com.inveritasoft.archcomponents.repository.ArchRepository;

import java.util.List;

public class DataFragmentViewModel extends ViewModel {

    private final String TYPE_CATEGORY = "category";
    private final String TYPE_BOOK = "book";
    private LiveData<List<BaseView>> booksForUi = new MediatorLiveData<>();
    private ArchRepository repository = App.getInstance().getRepository();


    public DataFragmentViewModel() {
        repository.getBooksFromApi();
        booksForUi = Transformations.map(repository.getCategoriesWithBooks(), new Deserializer());
    }


//    public List<BaseView> getBooks() {
//        List<Category> categories = DataMock.generateMockData();
//        List<BaseView> booksList = new ArrayList<>();
//        for (Category category : categories) {
//            booksList.add(new CategoryModel(TYPE_CATEGORY, category.getName(), categories.indexOf(category), category.getCategoryId()));
//            for (Book book : category.getBooks()) {
//                booksList.add(new BookModel(TYPE_BOOK, book.getName(), category.getBooks().indexOf(book) + categories.indexOf(category), book.getCategoryId()));
//            }
//        }
//        return booksList;
//    }

    private class Deserializer implements Function<List<CategoryWithBooks>, List<BaseView>> {

        @Override
        public List<BaseView> apply(final List<CategoryWithBooks> input) {


            return null;
        }
    }
}
