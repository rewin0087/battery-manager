package com.battery_monitor.android.batterymonitor.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rewin0087 on 3/1/15.
 */
public class Session {

    public static Session sessionInstance = null;

    public SharedPreferences preferences;

    public SharedPreferences.Editor editor;

    public Context context;

    public static String START_CHARGING = "START_CHARGING";

    public static String CONTINUE_CHARGING = "CONTINUE_CHARGING";

    public static String WILL_CONTINUE_CHARGING = "WILL_CONTINUE_CHARGING";

    public static String DONE_CHARGING = "DONE_CHARGING";

    public static String STOP_CHARGING = "STOP_CHARGING";

    // LABEL ATTRIBUTES
    public static String CHARGING_STATE = "CHARGING_STATE";

    public static String INITIAL_BATTERY_LEVEL = "INITIAL_BATTERY_LEVEL";

    public static String INITIAL_DATETIME = "INITIAL_DATETIME";

    public static String FINAL_BATTERY_LEVEL = "FINAL_BATTERY_LEVEL";

    public static String FINAL_DATETIME = "FINAL_DATETIME";

    public Session(Context context) {
        this.context = context;
        this.preferences = this.context.getSharedPreferences("BATTERY_MONITOR", 0);
        this.editor = this.preferences.edit();
    }

    public static Session getInstance(Context context) {
        if(sessionInstance != null) {
            return sessionInstance;
        }

        return new Session(context);
    }

    public String getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        return ft.format(date).toString();
    }

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        return ft.format(date).toString();
    }

    public Session doneCharging() {
        this.editor.clear();
        save();
        return this;
    }

    public Session setInitialBatteryLevel(int level) {
        this.editor.putInt(INITIAL_BATTERY_LEVEL, level);
        return this;
    }

    public Session setFinalBatteryLevel(int level) {
        this.editor.putInt(FINAL_BATTERY_LEVEL, level);
        return this;
    }

    public Session setInitialDateTime() {
        this.editor.putString(INITIAL_DATETIME, getCurrentDateTime());
        return this;
    }

    public Session setFinalDateTime() {
        this.editor.putString(FINAL_DATETIME, getCurrentDateTime());
        return this;
    }

    public Session save() {
        this.editor.commit();
        return this;
    }

    protected Session setChargingState(String state) {
        this.editor.putString(CHARGING_STATE, state);

        return this;
    }
}
