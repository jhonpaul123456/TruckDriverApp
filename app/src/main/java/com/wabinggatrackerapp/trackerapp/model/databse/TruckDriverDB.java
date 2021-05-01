package com.wabinggatrackerapp.trackerapp.model.databse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Driver.class}, version = 1, exportSchema = false)
public abstract class TruckDriverDB extends RoomDatabase {

   public abstract DriverDAO driverDAO();

   private static volatile TruckDriverDB INSTANCE;

   public static TruckDriverDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TruckDriverDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TruckDriverDB.class, "truck_driver_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}