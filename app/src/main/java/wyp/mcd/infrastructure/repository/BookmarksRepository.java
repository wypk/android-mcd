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

package wyp.mcd.infrastructure.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import wyp.mcd.infrastructure.dao.BookmarksDao;
import wyp.mcd.infrastructure.entities.BookmarksEntity;
import wyp.mcd.infrastructure.pesistance.McdRoomDatabase;

public class BookmarksRepository {

    private BookmarksDao bookmarksDao;
    private LiveData<List<BookmarksEntity>> itemBookmarksList;

    public BookmarksRepository(Application application) {
        McdRoomDatabase mcdRoomDatabase = McdRoomDatabase.getDatabase(application);
        bookmarksDao = mcdRoomDatabase.bookmarksDao();
        itemBookmarksList = bookmarksDao.getAllBookmarks();
    }

    public LiveData<List<BookmarksEntity>> getItemBookmarksList() {
        return itemBookmarksList;
    }

    public void deleteBookmark(BookmarksEntity bookmarksEntity) {
        bookmarksDao.deleteBookmark(bookmarksEntity);
    }

    public int getBookmarkCount(String bookmarkWord) {
        return bookmarksDao.getBookmarksCount(bookmarkWord);
    }

    public void deleteByBookmarkWord(String bookmarkWord) {
        bookmarksDao.deleteByBookmarkWord(bookmarkWord);
    }

    public void insert(BookmarksEntity bookmarksEntity) {
        new InsertAsyncTask(bookmarksDao).execute(bookmarksEntity);
    }

    public void deleteAllBookmark() {
        new DeleteAllAsyncTask(bookmarksDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<BookmarksEntity, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        InsertAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BookmarksEntity... params) {
            mAsyncTaskDao.insertBookmark(params[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<BookmarksEntity, Void, Void> {

        private BookmarksDao mAsyncTaskDao;

        DeleteAllAsyncTask(BookmarksDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BookmarksEntity... params) {
            mAsyncTaskDao.deleteAllBookmarks();
            return null;
        }
    }
}
