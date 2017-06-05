package com.threehalf.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.threehalf.calendarview.MarkStyle;
import com.threehalf.calendarview.listener.OnExpDateClickListener;
import com.threehalf.calendarview.listener.OnMonthScrollListener;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private CalendarView expCalendarView;
    private TextView mTvLeft;
    private TextView mTvRight;
    private TextView mTvTitle;
    private int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expCalendarView = ((CalendarView) findViewById(R.id.calendar_exp));
        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mTvTitle = (TextView) findViewById(R.id.tv_time);

        expCalendarView.setSlideType(CalendarView.SlideType.ONLY_RIGHT);
//        mTvTitle.setText(2017+"年"+4+"月");

        expCalendarView.markDate(new DateData(2016, 8, 2).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED, true)));
//        expCalendarView.markDate(new DateData(2017, 6, 3).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED,false)));
//        expCalendarView.markDate(new DateData(2017, 7, 7).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED,false)));
//        expCalendarView.travelTo(new DateData(2017, 8, 31));
        expCalendarView.setOnDateClickListener(new OnExpDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                super.onDateClick(view, date);
                Toast.makeText(MainActivity.this, String.format("%d-%d-%d",date.getYear(), date.getMonth(), date.getDay()), Toast.LENGTH_SHORT).show();
            }
        });
        expCalendarView.setOnMonthScrollListener(new OnMonthScrollListener() {
            @Override
            public void onMonthChange(int year, int month) {
                mTvTitle.setText(year + "年" + month + "月");
            }

            @Override
            public void onMonthScroll(int position, float positionOffset) {
                MainActivity.this.position = position;
            }
        });
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expCalendarView.toLeft(position);
            }
        });
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expCalendarView.toRight(position);
            }
        });
        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expCalendarView.setPositionCount(2);
                expCalendarView.travelTo(new DateData(2015, 8, 31));

            }
        });
    }

}
