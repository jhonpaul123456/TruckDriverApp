package com.wabinggatrackerapp.trackerapp.model.databse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DriverDAO {
    @Insert
    void insertDriver(Driver driver);

    @Query("SELECT * FROM drivers WHERE username=:username")
    LiveData<List<Driver>> getDriver(String username);
}
