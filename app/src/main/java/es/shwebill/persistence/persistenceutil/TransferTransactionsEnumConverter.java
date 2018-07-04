package es.shwebill.persistence.persistenceutil;

import android.arch.persistence.room.TypeConverter;

import es.shwebill.domain.type.TransferTransactions;

public class TransferTransactionsEnumConverter {

    @TypeConverter
    public static TransferTransactions toTransferTransactions(String value) {
        return value == null ? null : TransferTransactions.valueOf(value.toUpperCase());
    }

    @TypeConverter
    public static String toString(TransferTransactions value) {
        return value == null ? null : value.toString();
    }
}
