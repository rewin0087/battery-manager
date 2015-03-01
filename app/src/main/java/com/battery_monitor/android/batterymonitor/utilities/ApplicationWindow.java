package com.battery_monitor.android.batterymonitor.utilities;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by rewin0087 on 3/1/15.
 */
public class ApplicationWindow {

    public static void floatActivity(Activity activity) {
        Window window = activity.getWindow();

        // Creates the layout for the window and the look of it
        activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        // Params for the window.
        // You can easily set the alpha and the dim behind the window from here
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = 1.0f;    // lower than one makes it more transparent
        params.dimAmount = 0f;  // set it higher if you want to dim behind the window
        window.setAttributes(params);

        // Gets the display size so that you can set the window to a percent of that
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // You could also easily used an integer value from the shared preferences to set the percent
        if (height > width) {
            window.setLayout((int) (width * .9), (int) (height * .7));
        } else {
            window.setLayout((int) (width * .7), (int) (height * .8));
        }
    }

    public static void wakePhone(Activity activity) {
        Window window = activity.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
