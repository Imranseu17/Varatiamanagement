package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.enumClass.Type;
import com.example.varatiamanagement.utils.Constants;
import com.example.varatiamanagement.utils.Converters;

@Entity(tableName = Constants.ownerPropertyTable)
public class OwnerProperty {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "ownerProperty_id")
    private  int ownerId;

    @ColumnInfo(name = "name")
    private  String name;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "description")
    private  String description;

    @ColumnInfo(name = "address")
    private  String address;

    public OwnerProperty(String name, String type,
                         String description, String address) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OwnerProperty{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
