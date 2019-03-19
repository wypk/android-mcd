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

package wyp.mcd.component.persistence;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public abstract class RoomEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

//    @ColumnInfo(name = "created_date")
//    @TypeConverters(DateRoomConverter.class)
//    protected Date createdDate;
//
//    @ColumnInfo(name = "updated_date")
//    @TypeConverters(DateRoomConverter.class)
//    protected Date updatedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
