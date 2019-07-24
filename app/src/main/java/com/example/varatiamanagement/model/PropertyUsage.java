package com.example.varatiamanagement.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(foreignKeys = [@ForeignKey(entity = Properties.class,
        parentColumns = "id",
        childColumns = "property_id",
        onDelete = ForeignKey.NO_ACTION)],
        entity = Index() Properties.class,
        parentColumns = "id",
        childColumns = "property_id",
        onDelete = ForeignKey.NO_ACTION)],
        tableName = "properties_table")
public class PropertyUsage {
}
