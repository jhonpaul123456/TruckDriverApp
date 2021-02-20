package com.firstapp.crudappprojectlcnostrd.activities;

import android.os.Bundle;

import com.firstapp.crudappprojectlcnostrd.R;
import com.firstapp.crudappprojectlcnostrd.parents.BigPapaActivity;

public class FetchUsersActivity extends BigPapaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_users);
    }
}