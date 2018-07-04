package es.shwebill.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import es.shwebill.constants.EntityNames;
import es.shwebill.persistence.dao.TopUpTransactionsHistoryDao;
import es.shwebill.persistence.dao.TransferTransactionsHistoryDao;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;
import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;
import es.shwebill.persistence.persistenceutil.DateRoomConverter;
import es.shwebill.persistence.persistenceutil.OperatorsEnumConverter;
import es.shwebill.persistence.persistenceutil.TransferTransactionsEnumConverter;

@Database(entities = {TopUpTransactionsHistoryEntity.class, TransferTransactionsHistoryEntity.class}
        , version = 1, exportSchema = false)
@TypeConverters({DateRoomConverter.class, OperatorsEnumConverter.class, TransferTransactionsEnumConverter.class})
public abstract class ShweBillRoomDatabase extends RoomDatabase {

    private static ShweBillRoomDatabase INSTANCE;

    public static ShweBillRoomDatabase getDatabase(final Context mContext) {

        if (null == INSTANCE) {
            synchronized (ShweBillRoomDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = buildDatabaseInstance(mContext);
                }
            }
        }
        return INSTANCE;
    }

    private static ShweBillRoomDatabase buildDatabaseInstance(Context mContext) {

        return Room.databaseBuilder(mContext.getApplicationContext(),
                ShweBillRoomDatabase.class,
                EntityNames.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract TopUpTransactionsHistoryDao topUpTransactionsHistoryDao();

    public abstract TransferTransactionsHistoryDao transferTransactionsHistoryDao();


    public void cleanUp() {
        INSTANCE = null;
    }
}
