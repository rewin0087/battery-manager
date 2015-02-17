package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by rewin0087 on 2/17/15.
 */
public class BatteryLevelReceiver extends BroadcastReceiver {

    Intent intent;

    Context context;

    int level;

    int scale;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        this.level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        this.scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    }

    public float getBatteryLevel() {
        return this.level / (float) this.scale;
    }
}
