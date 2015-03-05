package com.battery_monitor.android.batterymonitor;

import android.app.Application;

import com.battery_monitor.android.batterymonitor.utilities.Battery;
import com.battery_monitor.android.batterymonitor.utilities.FontFace;

/**
 * Created by rewin0087 on 3/5/15.
 */
public class BatteryMonitorApplication extends Application {

    public static BatteryMonitorApplication instance;

    public BatteryMonitorApplication() {
        this.instance = this;
    }

    public static BatteryMonitorApplication getInstance() {
        return new BatteryMonitorApplication();
    }

    @Override
    public void onCreate() {
        Battery.getInstance(this).setAppTheme();
        FontFace.setAppFontToLed(this);
        super.onCreate();
    }
}
