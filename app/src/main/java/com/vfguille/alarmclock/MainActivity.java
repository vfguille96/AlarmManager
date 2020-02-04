package com.vfguille.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 2020;
    Button button;
    TextView textView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTimePickerDialog();
            }
        });
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendar != null){
                    setAlarmManager();
                    finish();
                }
            }
        });
    }


    /**
     * Establece una alarma en el sistema
     */
    private void setAlarmManager() {
        // Intent personalizado.
        Intent intent = new Intent("com.vfguille.alarmclock");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void createTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                setShowAlarm();
            }
        }, Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void setShowAlarm() {
        textView.setText(String.format("%02d:%02d:00", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
    }
}
