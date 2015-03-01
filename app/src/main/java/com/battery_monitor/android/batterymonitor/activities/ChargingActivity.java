package com.battery_monitor.android.batterymonitor.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.utilities.ApplicationWindow;

public class ChargingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApplicationWindow.floatActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging);
    }

}
