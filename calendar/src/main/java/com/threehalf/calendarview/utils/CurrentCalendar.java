package com.threehalf.calendarview.utils;

import com.threehalf.calendarview.model.DateData;

import java.util.Calendar;


/**
 *
 */
public class CurrentCalendar {
    public static DateData getCurrentDateData() {
        Calendar calendar = Calendar.getInstance();
        return new DateData(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar.get(calendar.DAY_OF_MONTH));
    }

    /**
     * 是否小于今天
     *
     * @param dateData
     * @return false 大于今天(今天后), true 小于等于今天(包含今天，今天前)
     */
    public static boolean lessThanToday(DateData dateData) {
        Calendar nowCalendar = Calendar.getInstance();
        Calendar  calendar = Calendar.getInstance();
        calendar.set(dateData.getYear(), dateData.getMonth() - 1, dateData.getDay());
        if (calendar.before(nowCalendar)|| calendar.equals(nowCalendar)){
            return true;
        }
        return false;
    }

}
