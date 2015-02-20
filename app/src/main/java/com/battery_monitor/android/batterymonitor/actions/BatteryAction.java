package com.battery_monitor.android.batterymonitor.actions;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.battery_monitor.android.batterymonitor.services.BatteryMonitorService;

/**
 * Created by rewin0087 on 2/18/15.
 */
public class BatteryAction {

    public Context context;

    public Intent batteryStatusIntent;

    public static void runService(Context context) {
        Intent batteryServiceIntent = new Intent(context, BatteryMonitorService.class);
        context.startService(batteryServiceIntent);
    }

    public BatteryAction(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        this.context = context;
        this.batteryStatusIntent = context.registerReceiver(null, ifilter);
    }

    public BatteryAction(Intent batteryStatusIntent) {
        this.batteryStatusIntent = batteryStatusIntent;
    }

    public int getChargePlug() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    }

    public int getLevel() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    }

    public int getScale() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    }
    public boolean isCharging() {
        return getStatus() == BatteryManager.BATTERY_STATUS_CHARGING;
    }

    public boolean isFullCharged() {
        return getStatus() == BatteryManager.BATTERY_STATUS_FULL;
    }
    public boolean isChargingOnUSB() {
        return getChargePlug() == BatteryManager.BATTERY_PLUGGED_USB;
    }

    public boolean isChargingOnAC() {
        return getChargePlug() == BatteryManager.BATTERY_PLUGGED_AC;
    }

    public float getCurrentBatteryLevel() {
        return getLevel() / (float) getScale();
    }

    public int getHealth() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
    }

    public int getTemperature() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
    }

    public String getTechnology() {
        return batteryStatusIntent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
    }

    public int getStatus() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    }

    public int getVoltage() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
    }

    public String gethealthStatus() {
        int health = getHealth();
        String status = "";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_COLD:
                status = "COLD";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                status = "DEAD";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                status = "GOOD";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                status = "OVER VOLTAGE";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                status = "OVERHEAT";
                break;
            default:
                break;
        }

        return  status;
    }
}
