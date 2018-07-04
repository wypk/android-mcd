package es.shwebill.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import es.shwebill.constants.EntityNames;
import es.shwebill.domain.type.Operators;
import es.shwebill.persistence.persistenceutil.DateRoomConverter;
import es.shwebill.persistence.persistenceutil.OperatorsEnumConverter;
import es.shwebill.persistence.persistenceutil.RoomEntity;

@Entity(tableName = EntityNames.TABLE_NAME_TOP_UP_TRANSACTIONS_HISTORY)
public class TopUpTransactionsHistoryEntity extends RoomEntity {

    @ColumnInfo(name = "top_up_phone_number")
    private String topUpPhoneNumber;

    @ColumnInfo(name = "top_up_amount")
    private double amount;

    @ColumnInfo(name = "top_up_date")
    @TypeConverters(DateRoomConverter.class)
    private Date topUpDate;

    @TypeConverters(OperatorsEnumConverter.class)
    @ColumnInfo(name = "operator_name")
    private Operators operatorName;

    public TopUpTransactionsHistoryEntity(String topUpPhoneNumber, double amount, Date topUpDate, Operators operatorName) {
        this.topUpPhoneNumber = topUpPhoneNumber;
        this.amount = amount;
        this.topUpDate = topUpDate;
        this.operatorName = operatorName;
    }

    public String getTopUpPhoneNumber() {
        return topUpPhoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTopUpDate() {
        return topUpDate;
    }

    public Operators getOperatorName() {
        return operatorName;
    }
}
