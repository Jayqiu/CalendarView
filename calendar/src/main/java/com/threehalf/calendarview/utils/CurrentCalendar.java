package com.threehalf.calendarview.utils;

import com.threehalf.calendarview.model.DateData;

import java.util.Calendar;


/**
 *
 */
public class CurrentCalendar {
    public static DateData getCurrentDateData(){
        Calendar calendar = Calendar.getInstance();
        return new DateData(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar.get(calendar.DAY_OF_MONTH));
    }

}
