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
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;

import wyp.mcd.component.persistence.DateRoomConverter;
import wyp.mcd.component.util.DbConstants;
import wyp.mcd.infrastructure.entities.EngToEngEntity;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateRoomConverter.class)
public interface EngToEngDao {

    @Query("SELECT * FROM eng_to_eng WHERE vocabulary LIKE :vocabulary")
    LiveData<List<EngToEngEntity>> find(String vocabulary);

    @Insert(onConflict = REPLACE)
    void insert(EngToEngEntity engToEngEntity);

    @Query("SELECT COUNT(id) FROM eng_to_eng")
    int getCount();

    @Query("SELECT * FROM " + DbConstants.TABLE_NAME_ENG_TO_ENG)
    LiveData<List<EngToEngEntity>> getAll();

    @Query("SELECT * FROM eng_to_eng WHERE vocabulary = :vocabulary")
    EngToEngEntity getEngToEng(String vocabulary);
}
