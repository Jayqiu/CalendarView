package com.threehalf.calendarview.listener;

import android.view.View;

import com.threehalf.calendarview.model.DateData;


/**
 *
 */
public abstract class OnDateClickListener {
    public static OnDateClickListener instance;

    public abstract void onDateClick(View view,DateData date);
}
