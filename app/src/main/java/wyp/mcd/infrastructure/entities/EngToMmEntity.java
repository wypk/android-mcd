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

package wyp.mcd.infrastructure.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import wyp.mcd.component.persistence.RoomEntity;
import wyp.mcd.component.util.DbConstants;

@Entity(tableName = DbConstants.TABLE_NAME_ENG_TO_MM)
public class EngToMmEntity extends RoomEntity {

    @ColumnInfo(name = "vocabulary", index = true)
    private String vocabulary;

    @ColumnInfo(name = "meaning")
    private String meaning;

    public EngToMmEntity(String vocabulary, String meaning) {
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
