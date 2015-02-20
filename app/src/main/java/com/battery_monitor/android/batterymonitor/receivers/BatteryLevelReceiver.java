package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

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
        this.level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        this.scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        String message = "BATTERY LEVEL RECEIVER: level -> " +
                this.level + " scale -> " +
                this.scale + " Life -> " +
                Float.toString(getBatteryPercentage());
        Log.d("BATTERY LEVEL", message);
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
    }

    // retrieve battery level | percentage
    public float getBatteryPercentage() {
        return this.level / (float) this.scale;
    }
}
