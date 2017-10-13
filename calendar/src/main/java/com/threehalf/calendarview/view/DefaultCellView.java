package com.threehalf.calendarview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.threehalf.calendarview.CalendarMarkStyle;
import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.listener.OnExpDateClickListener;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.model.DayData;
import com.threehalf.calendarview.utils.CurrentCalendar;

/**
 * @author jayqiu
 * @describe 默认显示
 * @date 2016/7/20 15:08
 */
public class DefaultCellView extends BaseCellView {
    private AbsListView.LayoutParams matchParentParams;
    public TextView textView;

    public DefaultCellView(Context context) {
        super(context);
        initLayout();
    }

    public DefaultCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {
        matchParentParams = new AbsListView.LayoutParams((int) CellConfig.cellWidth, (int) CellConfig.cellHeight);
        this.setLayoutParams(matchParentParams);
        this.setOrientation(VERTICAL);
        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
        this.addView(textView);
    }

    @Override
    public void setDisplayText(DayData day) {
        DateData dayDateDate = day.getDate();
//        if (dayDateDate.getDateType() == 0 || dayDateDate.getDateType() == 2) {// 非本月
//            textView.setTextColor(Color.GRAY);
//            setBackgroundOfVersion(CalendarMarkStyle.white);
//        } else {// 本月
//            DateData today = CurrentCalendar.getCurrentDateData();
//            if (OnExpDateClickListener.lastClickedDate.equals(today)) {
//                if (dayDateDate.equals(today)) {// 今天
//                    OnExpDateClickListener.lastClickedView = this;
//                    OnExpDateClickListener.lastClickedDate = dayDateDate;
//                    setDateChoose();
//                    Log.e("1111=========>",dayDateDate.getDayString());
//                }else {
//                    if (OnExpDateClickListener.lastClickedDate.equals(dayDateDate)) {
//                        OnExpDateClickListener.lastClickedView = this;
//                        OnExpDateClickListener.lastClickedDate = dayDateDate;
//                        setDateChoose();
//                        Log.e("222=========>",dayDateDate.getDayString());
//                    } else {
//                        if (!CurrentCalendar.lessThanToday(dayDateDate)) {//
//                            textView.setTextColor(Color.GRAY);
//                        } else {
//                            textView.setTextColor(Color.BLACK);
//                        }
//                        setBackgroundOfVersion(CalendarMarkStyle.white);
//                    }
//
//                }
//
//            } else {
//
//                if (OnExpDateClickListener.lastClickedDate.equals(dayDateDate)) {
//                    OnExpDateClickListener.lastClickedView = this;
//                    OnExpDateClickListener.lastClickedDate = dayDateDate;
//                    setDateChoose();
//                    Log.e("333=========>",dayDateDate.getDayString());
//                } else {
//                    if (!CurrentCalendar.lessThanToday(dayDateDate)) {//
//                        textView.setTextColor(Color.GRAY);
//                    } else {
//                        textView.setTextColor(Color.BLACK);
//                    }
//                    setBackgroundOfVersion(CalendarMarkStyle.white);
//                }
//
//            }
//
//
//        }
        textView.setText(day.getText());
    }

    @Override
    protected void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        super.onMeasure(measureWidthSpec, measureHeightSpec);
    }

    /**
     * 设置选中的颜色
     *
     * @return
     */
    public boolean setDateChoose() {
        setBackgroundOfVersion(CalendarMarkStyle.choose);
        textView.setTextColor(Color.WHITE);
        return true;
    }

    /**
     * 设置今天的颜色
     */
    public void setDateToday() {
        setBackgroundOfVersion(CalendarMarkStyle.today);
        textView.setTextColor(Color.rgb(45, 153, 244));
    }


    /**
     * 默认显示
     */
    public void setDateNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundOfVersion(null);

    }

    public void setOtherMonthNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundOfVersion(null);

    }

    public void setTextColor(String text, int color) {
        textView.setText(text);
        if (color != 0) {
            textView.setTextColor(color);
        }
    }

    /**
     * 在API16以前使用setBackgroundDrawable，在API16以后使用setBackground
     * API16<---->4.1
     *
     * @param drawable
     */
    private void setBackgroundOfVersion(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //Android系统大于等于API16，使用setBackground
            setBackground(drawable);
        } else {
            //Android系统小于API16，使用setBackground
            setBackgroundDrawable(drawable);
        }
    }
}
