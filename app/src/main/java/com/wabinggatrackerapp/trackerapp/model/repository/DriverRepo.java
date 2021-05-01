package com.wabinggatrackerapp.trackerapp.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.wabinggatrackerapp.trackerapp.model.databse.Driver;
import com.wabinggatrackerapp.trackerapp.model.databse.DriverDAO;
import com.wabinggatrackerapp.trackerapp.model.databse.TruckDriverDB;

import java.util.List;

public class DriverRepo {

    TruckDriverDB truckDriverDB;
    DriverDAO driverDAO;

    public DriverRepo(Application application) {
        truckDriverDB = TruckDriverDB.getDatabase(application);
        driverDAO = truckDriverDB.driverDAO();
    }

    public void insert(Driver driver) {
        driverDAO.insertDriver(driver);
    }

    public LiveData<List<Driver>> getDriver(String username) {
        return driverDAO.getDriver(username);
    }
}
