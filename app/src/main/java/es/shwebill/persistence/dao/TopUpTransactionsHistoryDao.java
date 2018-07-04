package es.shwebill.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import es.shwebill.constants.EntityNames;
import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.entities.TopUpTransactionsHistoryEntity;
import es.shwebill.persistence.persistenceutil.DateRoomConverter;
import es.shwebill.persistence.persistenceutil.OperatorsEnumConverter;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters({DateRoomConverter.class, OperatorsEnumConverter.class})
public interface TopUpTransactionsHistoryDao {

    @Query(value = "SELECT * FROM " + EntityNames.TABLE_NAME_TOP_UP_TRANSACTIONS_HISTORY)
    LiveData<List<TopUpTransactionsHistoryEntity>> getAllTransferTransactionsHistory();

    @Insert(onConflict = REPLACE)
    void saveTransferTransactionsHistory(TopUpTransactionsHistoryEntity topUpTransactionsHistoryEntity);

    @Query(value = "SELECT * FROM " + EntityNames.TABLE_NAME_TOP_UP_TRANSACTIONS_HISTORY + " WHERE operator_name = :operators")
    LiveData<List<TopUpTransactionsHistoryEntity>> getFilterWithOperatorType(Operators operators);
}
