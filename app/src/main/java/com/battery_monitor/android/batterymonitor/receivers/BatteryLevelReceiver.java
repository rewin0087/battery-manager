package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.battery_monitor.android.batterymonitor.activities.ChargingActivity;

/**
 * Created by rewin0087 on 2/17/15.
 */
public class BatteryLevelReceiver extends BroadcastReceiver {

    private Intent intent;

    private Context context;

    private int level;

    private int scale;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;

        Intent i = new Intent(this.context, ChargingActivity.class);
        this.context.startActivity(i);
    }

    // retrieve battery level | percentage
    public float getBatteryPercentage() {
        return this.level / (float) this.scale;
    }
}
