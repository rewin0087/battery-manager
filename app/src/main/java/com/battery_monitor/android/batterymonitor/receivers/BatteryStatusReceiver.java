package com.battery_monitor.android.batterymonitor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.battery_monitor.android.batterymonitor.activities.FullChargedActivity;
import com.battery_monitor.android.batterymonitor.utilities.Battery;

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
            Battery battery = new Battery(this.intent);
            battery.context = this.context;
            if(battery.isFullCharged()) {
                Intent i = new Intent(this.context, FullChargedActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.context.startActivity(i);
            }
//            String message = "isCharging: " + battery.isCharging() + " isFullCharged: " + battery.isFullCharged();
//            Toast.makeText(this.context,"BATTERY STATUS RECEIVER " + message, Toast.LENGTH_LONG).show();
        }
    }
}
