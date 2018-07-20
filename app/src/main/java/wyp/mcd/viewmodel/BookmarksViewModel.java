/*
 * Copyright (C) 2018
 *  Source code is created by Elissa Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package wyp.mcd.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import wyp.mcd.infrastructure.entities.BookmarksEntity;
import wyp.mcd.infrastructure.repository.BookmarksRepository;

public class BookmarksViewModel extends AndroidViewModel {

    private BookmarksRepository bookmarksRepository;

    public BookmarksViewModel(Application application) {
        super(application);
        bookmarksRepository = new BookmarksRepository(application);
    }

    public LiveData<List<BookmarksEntity>> getItemBookmarksList() {
        return bookmarksRepository.getItemBookmarksList();
    }

    public void deleteBookmark(BookmarksEntity bookmarksEntity) {
        bookmarksRepository.deleteBookmark(bookmarksEntity);
    }

    public void deleteAllBookmark() {
        bookmarksRepository.deleteAllBookmark();
    }

    public int getBookmarkCount(String bookmarkWord) {
        return bookmarksRepository.getBookmarkCount(bookmarkWord);
    }

    public void insertBookmark(BookmarksEntity bookmarksEntity) {
        bookmarksRepository.insert(bookmarksEntity);
    }

    public void deleteByBookmarkWord(String bookmarkWord) {
        bookmarksRepository.deleteByBookmarkWord(bookmarkWord);
    }
}
