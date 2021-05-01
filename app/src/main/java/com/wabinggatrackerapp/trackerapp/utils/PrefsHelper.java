package com.wabinggatrackerapp.trackerapp.utils;

import android.content.SharedPreferences;

public class PrefsHelper {

    enum PrefsKeys {
        id, username, password
    }

    public static final String USER_PREFERENCE_FILE = "com.firstapp.crud.driverPref";
    private final SharedPreferences mSharedPreferences;

    public PrefsHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public void setUserAsLoggedIn(String id, String username, String password) {
        mSharedPreferences.edit()
                .putString(PrefsKeys.id.name(), id)
                .putString(PrefsKeys.username.name(), username)
                .putString(PrefsKeys.password.name(), password)
                .apply();
    }

    public String getId() {
        return mSharedPreferences.getString(PrefsKeys.id.name(), null);
    }

    public String getUsername() {
        return mSharedPreferences.getString(PrefsKeys.username.name(), null);
    }

    public String getPassword() {
        return mSharedPreferences.getString(PrefsKeys.password.name(), null);
    }

    public boolean isUserLoggedIn() {
        boolean id = mSharedPreferences.contains(PrefsKeys.id.name());
        boolean username = mSharedPreferences.contains(PrefsKeys.username.name());
        boolean password = mSharedPreferences.contains(PrefsKeys.password.name());
        return (id && username && password);
    }

    public void logout() {
        mSharedPreferences.edit()
                .remove(PrefsKeys.id.name())
                .remove(PrefsKeys.username.name())
                .remove(PrefsKeys.password.name())
                .apply();
    }
}
