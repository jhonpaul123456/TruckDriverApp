package com.firstapp.crudappprojectlcnostrd.model.databse;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drivers")
public class Driver {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private final String username;

    @NonNull
    private final String password;

    public Driver(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }
}
