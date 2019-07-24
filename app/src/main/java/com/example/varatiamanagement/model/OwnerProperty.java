package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.enumClass.Type;

@Entity(tableName = "owner_property")
public class OwnerProperty {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "owner_id")
    private  int ownerId;

    @ColumnInfo(name = "name")
    private  String name;

    @TypeConverters(Type.class)
    @ColumnInfo(name = "type")
    private Type type;

    @ColumnInfo(name = "description")
    private  String description;

    @ColumnInfo(name = "address")
    private  String address;

    public OwnerProperty(int ownerId, String name, Type type,
                         String description, String address) {
        this.ownerId = ownerId;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
