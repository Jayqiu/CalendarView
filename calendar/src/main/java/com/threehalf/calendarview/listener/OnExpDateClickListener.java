package com.threehalf.calendarview.listener;

import android.view.View;

import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.utils.CurrentCalendar;
import com.threehalf.calendarview.view.DefaultMarkView;


/**
 *
 * <p>
 * 分别要对上次的和这次的处理
 * 而今日和其他日也有区别 所以有两个判断
 * 1.对上次的点击判断
 * 2.对这次的点击判断
 */
public class OnExpDateClickListener extends OnDateClickListener {

    public static View lastMarkView;
    public static DateData lastMarkClickedDate = CurrentCalendar.getCurrentDateData();
    @Override
    public void onDateClick(View view, DateData date) {

        if ( view instanceof DefaultMarkView ){
            // 判断上次的点击
            if (lastMarkView != null) {

                if (date.getDateType() == 0 || date.getDateType() == 2) {
                    return;
                } else {
                    if (lastMarkClickedDate.equals(CurrentCalendar.getCurrentDateData())) {
                        ((DefaultMarkView) lastMarkView).setDateToday();
                    } else {
                        if(lastMarkClickedDate.getMarkStyle()!=null &&lastMarkClickedDate.getMarkStyle().isShowBg()){
                            ((DefaultMarkView) lastMarkView).setMarkDate();
                        }else {
                            ((DefaultMarkView) lastMarkView).setDateNormal();
                        }
                    }

                }


            } else {
                if (date.getDateType() == 0 || date.getDateType() == 2) {
                    return;
                }
            }
            ((DefaultMarkView) view).setDateChoose();
            lastMarkView = view;
            lastMarkClickedDate = date;
        }

    }

}
