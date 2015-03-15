package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.battery_monitor.android.batterymonitor.utilities.Battery;

/**
 * Created by rewin0087 on 2/17/15.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    private Intent intent;

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        this.doAction();
    }

    public void doAction() {
        Battery.runService(context);

        if(this.intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(this.context, "CONNECTED", Toast.LENGTH_SHORT).show();
        } else if(this.intent.getAction() == Intent.ACTION_POWER_DISCONNECTED) {
            Toast.makeText(this.context, "NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }
    }
}
