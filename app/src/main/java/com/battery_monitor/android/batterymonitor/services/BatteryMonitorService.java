package com.battery_monitor.android.batterymonitor.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.battery_monitor.android.batterymonitor.actions.BatteryAction;
import com.battery_monitor.android.batterymonitor.receivers.BatteryStatusReceiver;

/**
 * Created by rewin0087 on 2/19/15.
 */
public class BatteryMonitorService extends Service {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        BatteryStatusReceiver batteryStatusReceiver = new BatteryStatusReceiver();
        context.registerReceiver(batteryStatusReceiver, ifilter);
        BatteryAction battery = new BatteryAction(context);
        Log.d("BATTERY SERVICE", "RUNNING");
        Log.d("isCharging:", battery.isCharging() + "");
        Log.d("isFullCharged:", battery.isFullCharged() + "");
        Log.d("isChargingOnUSB:", battery.isChargingOnUSB() + "");
        Log.d("isChargingOnAC:", battery.isChargingOnAC() + "");
        Log.d("getCurrentBatteryLevel:", battery.getCurrentBatteryLevel() + "");
        Log.d("level:", battery.getLevel() + "");
        Log.d("scale:", battery.getScale() + "");
        Log.d("EXTRA_HEALTH:", battery.gethealthStatus() + "");
        Log.d("EXTRA_TEMPERATURE:", battery.getTemperature() + "");
        Log.d("EXTRA_TECHNOLOGY:", battery.getTechnology() + "");
        Log.d("EXTRA_STATUS:", battery.getStatus() + "");
        Log.d("EXTRA_VOLTAGE:", battery.getVoltage() + "");
        Toast.makeText(context, "BATTERY SERVICE STARTED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
