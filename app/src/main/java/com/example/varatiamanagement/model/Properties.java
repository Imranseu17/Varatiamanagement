package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.utils.Constants;

@Entity(tableName = Constants.propertiesTable)
public class Properties {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "propertyId")
    private int propertyId;

    @ColumnInfo(name = "ownerProperty_id")
    private int ownerPropertyID;


    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "rent_amount")
    private double rentAmount;


    @ColumnInfo(name = "status")
    private String status;


    @ColumnInfo(name = "rent_by")
    private String rentBy;

    @ColumnInfo(name = "rent_priceType")
    private String rentPriceType;

    @ColumnInfo(name = "rent_members")
    private int rentMembers;

    public Properties(int ownerPropertyID, String type, String description, String name,
                      String address, double rentAmount,
                      String status, String rentBy, String rentPriceType, int rentMembers) {
        this.ownerPropertyID = ownerPropertyID;
        this.type = type;
        this.description = description;
        this.name = name;
        this.address = address;
        this.rentAmount = rentAmount;
        this.status = status;
        this.rentBy = rentBy;
        this.rentPriceType = rentPriceType;
        this.rentMembers = rentMembers;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getOwnerPropertyID() {
        return ownerPropertyID;
    }

    public void setOwnerPropertyID(int ownerPropertyID) {
        this.ownerPropertyID = ownerPropertyID;
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

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRentBy() {
        return rentBy;
    }

    public void setRentBy(String rentBy) {
        this.rentBy = rentBy;
    }

    public String getRentPriceType() {
        return rentPriceType;
    }

    public void setRentPriceType(String rentPriceType) {
        this.rentPriceType = rentPriceType;
    }

    public int getRentMembers() {
        return rentMembers;
    }

    public void setRentMembers(int rentMembers) {
        this.rentMembers = rentMembers;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "propertyId=" + propertyId +
                ", ownerPropertyID=" + ownerPropertyID +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rentAmount=" + rentAmount +
                ", status='" + status + '\'' +
                ", rentBy='" + rentBy + '\'' +
                ", rentPriceType='" + rentPriceType + '\'' +
                ", rentMembers='" + rentMembers + '\'' +
                '}';
    }
}
