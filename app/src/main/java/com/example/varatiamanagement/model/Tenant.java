package com.example.varatiamanagement.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.utils.Constants;

import java.io.Serializable;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "tenant_id",
        childColumns = "user_id",
        onDelete = ForeignKey.NO_ACTION),tableName = Constants.tenantTable)
public class Tenant implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "tenant_id")
    private int tenantId;

    @ColumnInfo(name = "user_id")
    private int userId;

    public Tenant(int userId) {
        this.userId = userId;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "tenantId=" + tenantId +
                ", userId=" + userId +
                '}';
    }
}
