package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.utils.Constants;
import com.example.varatiamanagement.utils.TimestampConverter;

import java.util.Date;

@Entity(
        tableName = Constants.propertyRentTable,
        foreignKeys = {
                @ForeignKey(
                        entity = PropertyUsage.class,
                        parentColumns = "id",
                        childColumns = "propertyUsage_id"
                ),
                @ForeignKey(
                        entity = Tenant.class,
                        parentColumns = "tenant_id",
                        childColumns = "id"
                )},
        indices = {@Index("id"), @Index(value = {"property_id","tenant_id"})})
public class PropertyRent  {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "propertyUsage_id")
    private int propertyUsageId;

    @ColumnInfo(name = "tenant_id")
    private int tenantId;

    @ColumnInfo(name = "rent_amount")
    private double rentAmount;

    @ColumnInfo(name = "paid_amount")
    private double paidAmount;

    @ColumnInfo(name = "due_date")
    @TypeConverters({TimestampConverter.class})
    private Date dueDate;

    @ColumnInfo(name = "paid_date")
    @TypeConverters({TimestampConverter.class})
    private Date paidDate;

    public PropertyRent(int propertyUsageId, int tenantId, double rentAmount,
                        double paidAmount, Date dueDate, Date paidDate) {
        this.propertyUsageId = propertyUsageId;
        this.tenantId = tenantId;
        this.rentAmount = rentAmount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
        this.paidDate = paidDate;
    }

    public int getId() {
        return id;
    }

    public int getPropertyUsageId() {
        return propertyUsageId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPropertyUsageId(int propertyUsageId) {
        this.propertyUsageId = propertyUsageId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public String toString() {
        return "PropertyRent{" +
                "id=" + id +
                ", propertyUsageId=" + propertyUsageId +
                ", tenantId=" + tenantId +
                ", rentAmount=" + rentAmount +
                ", paidAmount=" + paidAmount +
                ", dueDate=" + dueDate +
                ", paidDate=" + paidDate +
                '}';
    }
}
