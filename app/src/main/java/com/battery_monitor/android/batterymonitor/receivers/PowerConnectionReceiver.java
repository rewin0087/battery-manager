package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by rewin0087 on 2/17/15.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    Intent intent;

    Context context;

    int status;

    int chargePlug;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        this.status = this.intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        this.chargePlug = this.intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        this.doAction();
    }

    public void doAction() {
        // DO ACTION | TASK TO TODO
    }

    public boolean isCharging() {
        return (this.status == BatteryManager.BATTERY_STATUS_CHARGING || this.status == BatteryManager.BATTERY_STATUS_FULL);
    }

    public boolean isChargingOnUSB() {
        return this.chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
    }

    public boolean isChargingOnAC() {
        return this.chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
    }
}
