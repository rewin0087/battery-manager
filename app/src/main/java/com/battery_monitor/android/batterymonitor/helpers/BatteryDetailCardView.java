package com.battery_monitor.android.batterymonitor.helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.utilities.FontFace;

/**
 * Created by rewin0087 on 2/23/15.
 */
public class BatteryDetailCardView extends CardView {

    public Context context;

    public View view;

    public LayoutInflater inflater;

    public LinearLayout bodyContent;

    public BatteryDetailCardView(Context context) {
        super(context);
        this.context = context;
        loadSettings();
    }

    public static BatteryDetailCardView getInstance(Context context) {
        return new BatteryDetailCardView(context);
    }

    public BatteryDetailCardView loadSettings() {
        setRadius(0f);
        setFocusableInTouchMode(true);
        setCardElevation(20.0f);
        loadInflater();
        loadPartialView();
        loadContentView();
        return this;
    }

    public BatteryDetailCardView loadInflater() {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return this;
    }

    public BatteryDetailCardView loadPartialView() {
        view = inflater.inflate(R.layout.partial_cardview, this, false);
        return this;
    }

    public BatteryDetailCardView loadContentView() {
        bodyContent = (LinearLayout) view.findViewById(R.id.body_content);
        return this;
    }

    public BatteryDetailCardView setTitle(String title) {
        TextView textTitle = (TextView) view.findViewById(R.id.text_title_header);
        textTitle.setTypeface(FontFace.toDigitalLedFontface(context));
        textTitle.setText(title);
        return this;
    }

    public TextView textView(String value) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        layoutParams.setMargins(0, 10, 0, 10);
        textView.setGravity(Gravity.LEFT);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(20f);
        textView.setText(value);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(FontFace.toDigitalLedFontface(context));
        return textView;
    }

    public LinearLayout linearLayout() {
        LinearLayout linearLayoutView = new LinearLayout(this.context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        linearLayoutView.setLayoutParams(layoutParams);
        linearLayoutView.setOrientation(LinearLayout.HORIZONTAL);
        return linearLayoutView;
    }

    public BatteryDetailCardView addContent(String title, String value) {
        LinearLayout linearLayoutView = linearLayout();

        // title
        if(title != null) {
            TextView textView = textView(title);
            textView.setTypeface(FontFace.toDigitalLedFontface(context), Typeface.BOLD);
            linearLayoutView.addView(textView);
        }

        // value
        TextView textView = textView(value);
        textView.setGravity(Gravity.RIGHT);
        linearLayoutView.addView(textView);

        bodyContent.addView(linearLayoutView);
        return this;
    }

    public BatteryDetailCardView attachContentViews() {
        addView(view);
        return this;
    }

}
