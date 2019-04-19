/*
 * Copyright 2019 Wai Yan (TechBase Software). All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.mcd.infrastructure.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

import wyp.mcd.component.persistence.DateRoomConverter;
import wyp.mcd.component.util.DbConstants;
import wyp.mcd.infrastructure.entities.BookmarksEntity;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateRoomConverter.class)
public interface BookmarksDao {

    @Query(value = "SELECT * FROM " + DbConstants.TABLE_NAME_BOOKMARKS)
    LiveData<List<BookmarksEntity>> getAllBookmarks();

    @Insert(onConflict = REPLACE)
    void insertBookmark(BookmarksEntity bookmarksEntity);

    @Update(onConflict = REPLACE)
    void updateBookmark(BookmarksEntity bookmarksEntity);

    @Delete
    void deleteBookmark(BookmarksEntity bookmarksEntity);

    @Query("DELETE FROM " + DbConstants.TABLE_NAME_BOOKMARKS)
    void deleteAllBookmarks();

    @Query("SELECT COUNT(bookmark_word) FROM bookmarks WHERE bookmark_word = :bookmarkWord")
    int getBookmarksCount(String bookmarkWord);

    @Query("DELETE FROM bookmarks WHERE bookmark_word = :bookmarkWord")
    void deleteByBookmarkWord(String bookmarkWord);
}
