package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.battery_monitor.android.batterymonitor.actions.BatteryAction;

/**
 * Created by rewin0087 on 2/19/15.
 */
public class BatteryStatusReceiver extends BroadcastReceiver {

    private Intent intent;

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.intent = intent;
        this.context = context;
        doAction();
    }

    public void doAction() {
        if(this.intent.getAction() == Intent.ACTION_BATTERY_CHANGED) {
            BatteryAction battery = new BatteryAction(this.intent);
            String message = "isCharging: " + battery.isCharging();
//            Toast.makeText(this.context,"BATTERY STATUS RECEIVER " + message, Toast.LENGTH_LONG).show();
        }
    }
}
