package es.shwebill.persistence.persistenceutil;

import android.arch.persistence.room.TypeConverter;

import es.shwebill.domain.type.Operators;

public class OperatorsEnumConverter {

    @TypeConverter
    public static Operators toOperators(String value) {
        return value == null ? null : Operators.valueOf(value.toUpperCase());
    }

    @TypeConverter
    public static String toString(Operators value) {
        return value == null ? null : value.toString();
    }
}
