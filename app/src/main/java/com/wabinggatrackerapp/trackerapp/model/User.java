package com.wabinggatrackerapp.trackerapp.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private String status;

    private int id;

    @SerializedName("driver_name")
    private String driverName;


    @SerializedName("plate_number")
    private String plateNumber;


    @SerializedName("truck_condition")
	private String truckCondition;

	@SerializedName("truck_type")
    private String truckType;

	@SerializedName("inserted_at")
	private String insertedAt;

    @SerializedName("gate_entered")
    private String gateEntered;


    @SerializedName("cargo")
    private String cargo;

    public boolean isStatusSuccess() {
        return status.equals("success");
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getTruckCondition() {
        return truckCondition;
    }

    public String getTruckType() {
        return truckType;
    }

    public String getInsertedAt() {
        return insertedAt;
    }

    public String getGateEntered() {
        return gateEntered;
    }


    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", id=" + id +
                ", driverName='" + driverName + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", truckCondition='" + truckCondition + '\'' +
                ", truckType='" + truckType + '\'' +
                ", insertedAt='" + insertedAt + '\'' +
                ", gateEntered='" + gateEntered + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
