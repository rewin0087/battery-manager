package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.battery_monitor.android.batterymonitor.utilities.Battery;

/**
 * Created by rewin0087 on 3/10/15.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Context c = context;
        final Handler handler = new Handler();

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(c, "DEVICE BOOTED", Toast.LENGTH_LONG).show();
                Battery.runService(c);
            }
        };

        handler.postDelayed(runner, 3000);
    }
}
