package es.shwebill.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import es.shwebill.constants.EntityNames;
import es.shwebill.persistence.entities.TransferTransactionsHistoryEntity;
import es.shwebill.persistence.persistenceutil.DateRoomConverter;
import es.shwebill.persistence.persistenceutil.TransferTransactionsEnumConverter;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({DateRoomConverter.class, TransferTransactionsEnumConverter.class})
public interface TransferTransactionsHistoryDao {

    @Query(value = "SELECT * FROM " + EntityNames.TABLE_NAME_TRANSFER_TRANSACTIONS_HISTORY)
    LiveData<List<TransferTransactionsHistoryEntity>> getAllTransferTransactionsHistory();

    @Insert(onConflict = REPLACE)
    void saveTransferTransactionsHistory(TransferTransactionsHistoryEntity transferTransactionsHistoryEntity);
}
