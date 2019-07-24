package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.enumClass.RentBy;
import com.example.varatiamanagement.enumClass.Status;
import com.example.varatiamanagement.enumClass.Type;
import com.example.varatiamanagement.utils.Constants;

@Entity(foreignKeys = @ForeignKey(entity = OwnerProperty.class,
        parentColumns = "property_id",
        childColumns = "ownerProperty_id",
        onDelete = ForeignKey.NO_ACTION), tableName = Constants.propertiesTable)
public class Properties {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "property_id")
    private int property_id;

    @ColumnInfo(name = "ownerProperty_id")
    private int ownerPropertyID;

    @TypeConverters(Type.class)
    @ColumnInfo(name = "type")
    private Type type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "rent_price")
    private double rentPrice;

    @TypeConverters(Status.class)
    @ColumnInfo(name = "status")
    private Status status;

    @TypeConverters(RentBy.class)
    @ColumnInfo(name = "rent_by")
    private RentBy rentBy;

    public Properties(int ownerPropertyID, Type type, String description,
                      String name, String address,
                      double rentPrice, Status status,
                      RentBy rentBy) {
        this.ownerPropertyID = ownerPropertyID;
        this.type = type;
        this.description = description;
        this.name = name;
        this.address = address;
        this.rentPrice = rentPrice;
        this.status = status;
        this.rentBy = rentBy;
    }

    public int getProperty_id() {
        return property_id;
    }

    public void setProperty_id(int property_id) {
        this.property_id = property_id;
    }

    public int getOwnerPropertyID() {
        return ownerPropertyID;
    }

    public void setOwnerPropertyID(int ownerPropertyID) {
        this.ownerPropertyID = ownerPropertyID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RentBy getRentBy() {
        return rentBy;
    }

    public void setRentBy(RentBy rentBy) {
        this.rentBy = rentBy;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "property_id=" + property_id +
                ", ownerPropertyID=" + ownerPropertyID +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rentPrice=" + rentPrice +
                ", status=" + status +
                ", rentBy=" + rentBy +
                '}';
    }
}
