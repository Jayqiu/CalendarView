package com.threehalf.calendarview.listener;

import android.view.View;

import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.utils.CurrentCalendar;
import com.threehalf.calendarview.view.DefaultCellView;
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

    private View lastMarkView;
    private View lastClickedView;
    private DateData lastClickedDate = CurrentCalendar.getCurrentDateData();

    @Override
    public void onDateClick(View view, DateData date) {

        if (view instanceof DefaultCellView) {

            // 判断上次的点击
            if (lastClickedView != null) {// 上次有点击
                // 节约！
                if (lastClickedView == view)
                    return;
                if (date.getDateType() == 0 || date.getDateType() == 2) {
                    return;
                } else {
                    if (lastClickedDate.equals(CurrentCalendar.getCurrentDateData())) {
                        ((DefaultCellView) lastClickedView).setDateToday();
                    } else {
                        ((DefaultCellView) lastClickedView).setDateNormal();
                    }

                }


            } else {//
                if (date.getDateType() == 0 || date.getDateType() == 2) {
                    return;
                }
            }
            if (lastMarkView != null) {
                if(lastClickedDate.getMarkStyle().isShowBg()){
                    ((DefaultMarkView) lastMarkView).setMarkDate();
                }else {
                    ((DefaultMarkView) lastMarkView).setDateNormal();
                }

                lastMarkView=null;
            }
            ((DefaultCellView) view).setDateChoose();
            lastClickedView = view;
            lastClickedDate = date;
        }
        if ( view instanceof DefaultMarkView ){
            // 判断上次的点击
            if (lastMarkView != null) {
                // 节约！
                if (lastMarkView == view)
                    return;
                if (date.getDateType() == 0 || date.getDateType() == 2) {
                    return;
                } else {
                    if (lastClickedDate.equals(CurrentCalendar.getCurrentDateData())) {
                        ((DefaultMarkView) lastMarkView).setDateToday();
                    } else {
                        if(lastClickedDate.getMarkStyle().isShowBg()){
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
            if (lastClickedView != null) {// 上次有点击
                ((DefaultCellView) lastClickedView).setDateNormal();
                lastClickedView=null;
            }
            ((DefaultMarkView) view).setDateChoose();
            lastMarkView = view;
            lastClickedDate = date;
        }

    }

}
