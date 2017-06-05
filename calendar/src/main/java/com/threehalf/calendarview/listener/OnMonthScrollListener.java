package com.threehalf.calendarview.listener;

import android.support.v4.view.ViewPager;

import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.utils.CalendarUtil;
import com.threehalf.calendarview.utils.ExpCalendarUtil;


/**
 *
 *
 * add a onMonthScroll . the aim is for cool effect
 */
public abstract class OnMonthScrollListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        onMonthScroll(position,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        CellConfig.middlePosition = position;
        DateData date;
        if (CellConfig.ifMonth)
            date = ExpCalendarUtil.position2Month(position);
        else
            date = ExpCalendarUtil.position2Week(position);
        onMonthChange(date.getYear(), date.getMonth());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public abstract void onMonthChange(int year, int month);

    public abstract void onMonthScroll(int position,float positionOffset);
}
