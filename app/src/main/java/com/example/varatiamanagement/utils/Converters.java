package com.example.varatiamanagement.utils;

import android.arch.persistence.room.TypeConverter;

import com.example.varatiamanagement.enumClass.Type;

import java.util.Arrays;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String fromCategoryToString(Type type) {
        if (type == null)
            return null;
        return type.toString();
    }
}
