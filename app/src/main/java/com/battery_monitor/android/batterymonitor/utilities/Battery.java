package com.battery_monitor.android.batterymonitor.utilities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.services.BatteryMonitorService;

/**
 * Created by rewin0087 on 2/18/15.
 */
public class Battery {

    public Context context;

    public Intent batteryStatusIntent;

    public static Battery batteryInstance = null;

    public static void runService(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (BatteryMonitorService.class.getName().equals(service.service.getClassName())) {
                Log.d("BATTERY SERVICE", "INSTANTIATED ALREADY");
            } else {
                Log.d("BATTERY SERVICE", "INSTANTIATING SERVICE");
                // start service
                Intent batteryServiceIntent = new Intent(context, BatteryMonitorService.class);
                context.startService(batteryServiceIntent);
            }
        }
    }

    public Battery(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        this.context = context;
        this.batteryStatusIntent = context.registerReceiver(null, ifilter);
    }

    public static Battery getInstance(Context context) {
        if(batteryInstance != null) {
            Log.d("BATTERY STATIC INSTANCE", "RETURNING INSTANTIATED BATTERY OBJECT");
            return batteryInstance;
        }

        batteryInstance = new Battery(context);
        Log.d("BATTERY STATIC INSTANCE", "INITIATE INSTANCE BATTERY OBJECT");
        return batteryInstance;
    }

    public Battery(Intent batteryStatusIntent) {
        this.batteryStatusIntent = batteryStatusIntent;
    }

    public int getChargePlug() {
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    }

    public boolean isChargePlug() {
        return getChargePlug() != 0 ? true : false;
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

    public boolean isDisCharging() {
        return getStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING;
    }

    public boolean isNotCharging() {
        return getStatus() == BatteryManager.BATTERY_STATUS_NOT_CHARGING;
    }

    public boolean isStatusUnknown() {
        return getStatus() == BatteryManager.BATTERY_STATUS_UNKNOWN;
    }

    public boolean isFullCharged() {
        return getStatus() == BatteryManager.BATTERY_STATUS_FULL;
    }

    public String getChargeState() {
        String state = "";

        if(isCharging()) {
            state = "CHARGING";
        } else if(isFullCharged()) {
            state = "FULL CHARGED";
        } else if(isDisCharging()) {
            state = "DISCHARGING";
        } else if(isNotCharging()) {
            state = "NOT CHARGING";
        } else if(isStatusUnknown()) {
            state = "STATUS UNKNOWN";
        } else {
            state = "NO STATUS";
        }

        return state;
    }

    public String getChargingConnection() {
        String connection = "";

        if(isChargingOnAC()) {
            connection = "AC CHARGER";
        } else if(isChargingOnUSB()) {
            connection = "USB PORT";
        } else if(isChargingOnWireless()) {
            connection = "WIRELESS";
        } else {
            connection = "NOT CONNECTED";
        }

        return connection;
    }

    public boolean isChargingOnWireless() {
        return getChargePlug() == BatteryManager.BATTERY_PLUGGED_WIRELESS;
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
        return batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;
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

    public boolean getPresent() {
        return batteryStatusIntent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
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
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                status = "UNKNOWN";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                status = "UNSPECIFIED FAILURE";
                break;
            default:
                status = "NO STATUS";
                break;
        }

        return  status;
    }

    public void setAppTheme() {
        Log.d("BATTERY LEVEL", "LEVEL: " + getLevel());
        if(getLevel() >= 100 && getLevel() >= 60) {
            // 100% to 60%
            context.setTheme(R.style.Theme_Green);
            Log.d("BATTERY THEME", "SETTING TO GREEN WITH LEVEL:" + getLevel());
        } else if(getLevel() < 60 && getLevel() >= 40) {
            // 59% to 40%
            context.setTheme(R.style.Theme_Blue);
            Log.d("BATTERY THEME", "SETTING TO BLUE WITH LEVEL:" + getLevel());
        } else if(getLevel() < 40 && getLevel() >= 20) {
            // 39% to 20%
            context.setTheme(R.style.Theme_Orange);
            Log.d("BATTERY THEME", "SETTING TO ORANGE WITH LEVEL:" + getLevel());
        } else if (getLevel() < 20) {
            // 20% below
            context.setTheme(R.style.Theme_Red);
            Log.d("BATTERY THEME", "SETTING TO RED WITH LEVEL:" + getLevel());
        }
    }
}
