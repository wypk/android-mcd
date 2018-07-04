package es.shwebill.persistence.persistenceutil;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
