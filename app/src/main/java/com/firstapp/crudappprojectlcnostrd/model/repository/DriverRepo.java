package com.firstapp.crudappprojectlcnostrd.model.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firstapp.crudappprojectlcnostrd.model.databse.Driver;
import com.firstapp.crudappprojectlcnostrd.model.databse.DriverDAO;
import com.firstapp.crudappprojectlcnostrd.model.databse.TruckDriverDB;

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
