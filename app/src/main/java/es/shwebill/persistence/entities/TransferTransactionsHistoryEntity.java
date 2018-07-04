package es.shwebill.persistence.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import es.shwebill.constants.EntityNames;
import es.shwebill.domain.type.TransferTransactions;
import es.shwebill.persistence.persistenceutil.DateRoomConverter;
import es.shwebill.persistence.persistenceutil.RoomEntity;
import es.shwebill.persistence.persistenceutil.TransferTransactionsEnumConverter;

@Entity(tableName = EntityNames.TABLE_NAME_TRANSFER_TRANSACTIONS_HISTORY)
public class TransferTransactionsHistoryEntity extends RoomEntity {

    @ColumnInfo(name = "account_name")
    private String accountName;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "transfer_date")
    @TypeConverters(DateRoomConverter.class)
    private Date transferDate;

    @TypeConverters(TransferTransactionsEnumConverter.class)
    @ColumnInfo(name = "transfer_transactions_type")
    private TransferTransactions transferTransactions;

    public TransferTransactionsHistoryEntity(String accountName, double amount, Date transferDate, TransferTransactions transferTransactions) {
        this.accountName = accountName;
        this.amount = amount;
        this.transferDate = transferDate;
        this.transferTransactions = transferTransactions;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public TransferTransactions getTransferTransactions() {
        return transferTransactions;
    }

}
