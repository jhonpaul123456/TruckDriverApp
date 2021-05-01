package com.wabinggatrackerapp.trackerapp.model;

public class Shizuka {
    private String status;
    private String id;
    private String name;
    private String mobile;
    private String street;
    private String total;
    private String dates;

    public boolean isStatusSuccess() {
        return status.equals("success");
    }

    public Shizuka() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String mobile) {
        this.street = street;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Shizuka{" +
                "status='" + status + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", street='" + street + '\'' +
                ", total='" + total + '\'' +
                ", dates='" + dates + '\'' +
                '}';
    }
}
