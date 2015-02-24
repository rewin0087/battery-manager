package com.battery_monitor.android.batterymonitor.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.helpers.BatteryDetailCardView;
import com.battery_monitor.android.batterymonitor.utilities.Battery;

/**
 * Created by rewin0087 on 2/17/15.
 */
public class HomeFragment extends BaseFragment {

    private Battery battery;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity().getApplication();
        battery = Battery.getInstance(context);

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        LinearLayout homeContainer = (LinearLayout) rootView.findViewById(R.id.home_container);
        // BATTERY INFORMATION
        BatteryDetailCardView batteryInformationCardView = BatteryDetailCardView.getInstance(context);
        batteryInformationCardView.setTitle("BATTERY INFORMATION");
        batteryInformationCardView.addContent("HEALTH:", battery.gethealthStatus());
        batteryInformationCardView.addContent("LEVEL:", battery.getLevel() + "%");
        batteryInformationCardView.addContent("SCALE:", battery.getScale() + "%");
        batteryInformationCardView.addContent("HEALTH:", battery.gethealthStatus());
        batteryInformationCardView.addContent("VOLTAGE:", battery.getVoltage() + " mV");
        batteryInformationCardView.addContent("PRESENT:", battery.getPresent() + "");
        batteryInformationCardView.addContent("TECHNOLOGY:", battery.getTechnology());
        batteryInformationCardView.addContent("TEMPERATURE:", battery.getTemperature() + "" + (char) 0x00B0 + "C");
        batteryInformationCardView.attachContentViews();
        homeContainer.addView(batteryInformationCardView);
        // CHARGING INFORMATION
        BatteryDetailCardView batteryStatusCardView = BatteryDetailCardView.getInstance(context);
        batteryStatusCardView.setTitle("CHARGING INFORMATION");
        batteryStatusCardView.addContent("STATE:", battery.getChargeState());
        batteryStatusCardView.addContent("PLUGGED:", battery.isChargePlug() + "");
        batteryStatusCardView.addContent("POWER SOURCE:", battery.getChargingConnection());
        batteryStatusCardView.attachContentViews();
        homeContainer.addView(batteryStatusCardView);

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

        return rootView;
    }
}
