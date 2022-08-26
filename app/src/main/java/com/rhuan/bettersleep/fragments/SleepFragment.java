package com.rhuan.bettersleep.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1573595.clockslider.ClockSlider;
import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.SwipeButton;
import com.rhuan.bettersleep.R;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class SleepFragment extends Fragment {

    public CircularSeekBar circularSeekBar;
    public SwipeButton swipeButton;

    public SleepFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

        View view;
        RelativeLayout sleep;
        RelativeLayout sleep_alarm;
        RelativeLayout sleep_report;
        ImageView bt_alarm;
        AppCompatButton close_sleep_alarm;
        AppCompatButton close_sleep_report;
        AppCompatButton set_alarm;
        AppCompatButton start_sleeping;
        RelativeLayout swipe_up_layout;
        ClockSlider clock_slider;
        TextView bedtime_time;
        TextView wake_up_time;
        TextView time_count;
        TimerTask timer_countdown_task;
        float time;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sleep, container, false);

        bt_alarm = view.findViewById(R.id.bt_alarm);
        sleep = view.findViewById(R.id.sleep_view);
        sleep_alarm = view.findViewById(R.id.sleep_alarm);
        sleep_report = view.findViewById(R.id.sleep_report_view);
        close_sleep_alarm = view.findViewById(R.id.close_button_sleep_alarm);
        close_sleep_report = view.findViewById(R.id.close_button_sleep_report);
        set_alarm = view.findViewById(R.id.btn_set_alarm);
        start_sleeping = view.findViewById(R.id.btn_start_sleeping);
        swipeButton = view.findViewById(R.id.swipe_btn);
        swipe_up_layout = view.findViewById(R.id.swipe_btn_layout);
        circularSeekBar = view.findViewById(R.id.progress_bar);
        clock_slider = view.findViewById(R.id.clock_slider);
        bedtime_time = view.findViewById(R.id.bedtime_time);
        wake_up_time = view.findViewById(R.id.wake_up_time);
        time_count = view.findViewById(R.id.time_count);

        time = Math.abs(clock_slider.getEndHours() - clock_slider.getStartHours());

        circularSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        swipeButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                swipe_up_layout.setVisibility(View.INVISIBLE);
                start_sleeping.setVisibility(View.VISIBLE);
                bt_alarm.setVisibility(View.VISIBLE);
                sleep.setVisibility(View.INVISIBLE);
                sleep_report.setVisibility(View.VISIBLE);
                timer_countdown_task.cancel();
            }
        });

        bt_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleep.setVisibility(View.INVISIBLE);
                sleep_alarm.setVisibility(View.VISIBLE);
            }
        });

        close_sleep_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleep.setVisibility(View.VISIBLE);
                sleep_alarm.setVisibility(View.INVISIBLE);
            }
        });

        close_sleep_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleep.setVisibility(View.VISIBLE);
                circularSeekBar.setVisibility(View.INVISIBLE);
                sleep_report.setVisibility(View.INVISIBLE);
            }
        });

        set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleep_alarm.setVisibility(View.INVISIBLE);
                sleep.setVisibility(View.VISIBLE);
                bt_alarm.setVisibility(View.INVISIBLE);
                start_sleeping.setVisibility(View.INVISIBLE);
                circularSeekBar.setVisibility(View.VISIBLE);
                swipe_up_layout.setVisibility(View.VISIBLE);

                final float[] time_countdown = {0.0f};

                timer_countdown_task = new TimerTask() {
                    @Override
                    public void run() {
                        if (time_countdown[0] == (time * 60 * 60)) {
                            return;
                        }
                        time_countdown[0] += 1;
                        circularSeekBar.setMax(time * 60 * 60);
                        circularSeekBar.setProgress(circularSeekBar.getProgress()+time_countdown[0]);
                        Log.d("TESTE", String.valueOf(circularSeekBar.getMax()));
                        Log.d("TESTE", String.valueOf(circularSeekBar.getProgress()));
                    }
                };
                new Timer().scheduleAtFixedRate(timer_countdown_task, 0, 1000);
            }
        });

        clock_slider.setOnTimeChangedListener(new ClockSlider.OnTimeChangedListener() {
            @Override
            public void onStartChanged(int i, int i1) {

                float time_start = Math.abs(clock_slider.getStartHours());
                int hour_start = (int) time_start;
                float hour_start_float = Math.abs(hour_start);
                float minutes_start = Math.abs(time_start - hour_start_float);
                if (Math.abs(minutes_start * 60) < 9) bedtime_time.setText(String.format("%s:0%sh",hour_start,Math.abs((int)(60*minutes_start))));
                else bedtime_time.setText(String.format("%s:%sh",hour_start,Math.abs((int)(60*minutes_start))));

                float time_end = Math.abs(clock_slider.getEndHours());
                int hour_end = (int) time_end;
                float hour_end_float = Math.abs(hour_end);
                float minutes_end = Math.abs(time_end - hour_end_float);
                if (Math.abs(minutes_end * 60) < 9) wake_up_time.setText(String.format("%s:0%sh",hour_end,Math.abs((int)(60*minutes_end))));
                else wake_up_time.setText(String.format("%s:%sh",hour_end,Math.abs((int)(60*minutes_end))));

                time = Math.abs(clock_slider.getEndHours() - clock_slider.getStartHours());
                int hour = (int) time;
                float hour_float = Math.abs(hour);
                float minutes = Math.abs(time - hour_float);
                if (Math.abs(minutes * 60) < 9) time_count.setText(String.format("%s:0%sh",hour,Math.abs((int)(60*minutes))));
                else time_count.setText(String.format("%s:%sh",hour,Math.abs((int)(60*minutes))));
            }

            @Override
            public void onEndChanged(int i, int i1) {
                float time_start = Math.abs(clock_slider.getStartHours());
                int hour_start = (int) time_start;
                float hour_start_float = Math.abs(hour_start);
                float minutes_start = Math.abs(time_start - hour_start_float);
                if (Math.abs(minutes_start * 60) < 9) bedtime_time.setText(String.format("%s:0%sh",hour_start,Math.abs((int)(60*minutes_start))));
                else bedtime_time.setText(String.format("%s:%sh",hour_start,Math.abs((int)(60*minutes_start))));

                float time_end = Math.abs(clock_slider.getEndHours());
                int hour_end = (int) time_end;
                float hour_end_float = Math.abs(hour_end);
                float minutes_end = Math.abs(time_end - hour_end_float);
                if (Math.abs(minutes_end * 60) < 9) wake_up_time.setText(String.format("%s:0%sh",hour_end,Math.abs((int)(60*minutes_end))));
                else wake_up_time.setText(String.format("%s:%sh",hour_end,Math.abs((int)(60*minutes_end))));

                time = Math.abs(clock_slider.getEndHours() - clock_slider.getStartHours());
                int hour = (int) time;
                float hour_float = Math.abs(hour);
                float minutes = Math.abs(time - hour_float);
                if (Math.abs(minutes * 60) < 9) time_count.setText(String.format("%s:0%sh",hour,Math.abs((int)(60*minutes))));
                else time_count.setText(String.format("%s:%sh",hour,Math.abs((int)(60*minutes))));
            }
        });

        return view;
    }
}