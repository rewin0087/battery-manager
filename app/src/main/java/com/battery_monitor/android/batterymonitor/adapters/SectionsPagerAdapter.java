package com.battery_monitor.android.batterymonitor.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.fragments.HistoryFragment;
import com.battery_monitor.android.batterymonitor.fragments.HomeFragment;
import com.battery_monitor.android.batterymonitor.fragments.SettingsFragment;

import java.util.Locale;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SectionsPagerAdapter setContext(Context context) {
        this.context = context;

        return this;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
        switch (position) {
            case 0:
                return HomeFragment.newInstance(position);
            case 1:
                return HistoryFragment.newInstance(position);
            case 2:
                return SettingsFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return this.context.getString(R.string.home_section).toUpperCase(l);
            case 1:
                return this.context.getString(R.string.history_section).toUpperCase(l);
            case 2:
                return this.context.getString(R.string.settings_section).toUpperCase(l);
        }

        return  null;
    }
}
