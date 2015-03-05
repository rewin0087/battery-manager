package com.battery_monitor.android.batterymonitor.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.battery_monitor.android.batterymonitor.R;
import com.battery_monitor.android.batterymonitor.utilities.ApplicationWindow;
import com.battery_monitor.android.batterymonitor.utilities.Session;

public class FullChargedActivity extends ActionBarActivity {

    protected MediaPlayer mp;

    protected AudioManager mAudioManager;

    protected int originalVolume;

    private float MAX_VOLUME = 1f;//0.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApplicationWindow.wakePhone(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_charged);

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

        mp = MediaPlayer.create(this, R.raw.alarm);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setVolume(MAX_VOLUME, MAX_VOLUME);
        mp.setLooping(true);
        mp.start();

        TextView dateTimeText = (TextView) findViewById(R.id.datetime_text);
        Button dismissButton = (Button) findViewById(R.id.dismiss_button);
        Session session = Session.getInstance(this);

        dateTimeText.setText(session.getCurrentTime());
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                finish();
            }
        });
    }

    public void onBackPressed() {
        onResume();
    }
}
