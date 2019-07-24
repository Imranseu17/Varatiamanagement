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

import java.text.DateFormat;
import java.util.Date;

@Entity(
        tableName = Constants.propertyUsageTable,
        foreignKeys = {
                @ForeignKey(
                        entity = Properties.class,
                        parentColumns = "propertyUsage_id",
                        childColumns = "property_id"
                ),
                @ForeignKey(
                        entity = Tenant.class,
                        parentColumns = "propertyUsage_id",
                        childColumns = "tenant_id"
                )},
        indices = {@Index("id"), @Index(value = {"property_id","tenant_id"})})
public class PropertyUsage {

        @NonNull
        @ColumnInfo(name = "propertyUsage_id")
        @PrimaryKey(autoGenerate = true)
        private  int id;

        @ColumnInfo(name = "property_id")
        private int propertyId;

        @ColumnInfo(name = "tenant_id")
        private int tenantId;

        @ColumnInfo(name = "start_date")
        @TypeConverters({TimestampConverter.class})
        private Date startDate;

        @ColumnInfo(name = "end_date")
        @TypeConverters({TimestampConverter.class})
        private Date endDate;

        public PropertyUsage(int propertyId, int tenantId, Date startDate, Date endDate) {
                this.propertyId = propertyId;
                this.tenantId = tenantId;
                this.startDate = startDate;
                this.endDate = endDate;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getPropertyId() {
                return propertyId;
        }

        public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
        }

        public int getTenantId() {
                return tenantId;
        }

        public void setTenantId(int tenantId) {
                this.tenantId = tenantId;
        }

        public Date getStartDate() {
                return startDate;
        }

        public void setStartDate(Date startDate) {
                this.startDate = startDate;
        }

        public Date getEndDate() {
                return endDate;
        }

        public void setEndDate(Date endDate) {
                this.endDate = endDate;
        }

        @Override
        public String toString() {
                return "PropertyUsage{" +
                        "id=" + id +
                        ", propertyId=" + propertyId +
                        ", tenantId=" + tenantId +
                        ", startDate=" + startDate +
                        ", endDate=" + endDate +
                        '}';
        }
}
