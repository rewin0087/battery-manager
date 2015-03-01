package com.battery_monitor.android.batterymonitor.fragments;

import android.os.Bundle;
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

    private View rootView;
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
        battery = new Battery(context);
        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        LinearLayout homeContainer = (LinearLayout) rootView.findViewById(R.id.home_container);
        setButtonOnClickListener();

        // BATTERY INFORMATION
        homeContainer.addView(batteryInformationView());
        // CHARGING INFORMATION
        homeContainer.addView(chargingInformationView());

        return rootView;
    }

    private void setButtonOnClickListener() {
//        Button chargingButton = (Button) rootView.findViewById(R.id.charging_button);
//        Button fullChargedButton = (Button) rootView.findViewById(R.id.fullcharged_button);
//
//        chargingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, ChargingActivity.class);
//                startActivity(i);
//            }
//        });
//
//        fullChargedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, FullChargedActivity.class);
//                startActivity(i);
//            }
//        });
    }

    private BatteryDetailCardView chargingInformationView() {
        BatteryDetailCardView chargingInformationCardView = BatteryDetailCardView.getInstance(context);
        chargingInformationCardView.setTitle("CHARGING INFORMATION");
        chargingInformationCardView.addContent("STATE:", battery.getChargeState());
        chargingInformationCardView.addContent("PLUGGED:", battery.isChargePlug() + "");
        chargingInformationCardView.addContent("POWER SOURCE:", battery.getChargingConnection());
        chargingInformationCardView.attachContentViews();
        return chargingInformationCardView;
    }

    private BatteryDetailCardView batteryInformationView() {
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
        return batteryInformationCardView;
    }
}
