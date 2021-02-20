package com.firstapp.crudappprojectlcnostrd.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private String status;

    @SerializedName("driver_name")
    private String driverName;


    @SerializedName("plate_number")
    private String plateNumber;


    @SerializedName("truck_condition")
	private String truckCondition;

	@SerializedName("truck_type")
    private String truckType;

    public boolean isStatusSuccess() {
        return status.equals("success");
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

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", driverName='" + driverName + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", truckCondition='" + truckCondition + '\'' +
                ", truckType='" + truckType + '\'' +
                '}';
    }
}
