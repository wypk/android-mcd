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

package wyp.mcd.infrastructure.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import wyp.mcd.component.persistence.RoomEntity;
import wyp.mcd.component.util.Constants;

@Entity(tableName = Constants.TABLE_NAME_ENG_TO_ENG)
public class EngToEngEntity extends RoomEntity {

    @ColumnInfo(name = "vocabulary", index = true)
    private String vocabulary;

    @ColumnInfo(name = "meaning")
    private String meaning;

    public EngToEngEntity(String vocabulary, String meaning) {
        this.vocabulary = vocabulary;
        this.meaning = meaning;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
