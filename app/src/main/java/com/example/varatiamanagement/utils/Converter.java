package com.example.varatiamanagement.utils;

import android.arch.persistence.room.TypeConverter;

import com.example.varatiamanagement.enumClass.Type;

public class Converter {

    @TypeConverter
    public static String fromTypeToString(Type type) {
        if (type == null)
            return null;
        return type.toString();
    }
}
