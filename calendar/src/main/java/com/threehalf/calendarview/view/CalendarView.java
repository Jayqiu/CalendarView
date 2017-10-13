package com.threehalf.calendarview.view;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.MarkStyle;
import com.threehalf.calendarview.R;
import com.threehalf.calendarview.adpter.CalendarViewFragmentAdapter;
import com.threehalf.calendarview.listener.OnDateClickListener;
import com.threehalf.calendarview.listener.OnExpDateClickListener;
import com.threehalf.calendarview.listener.OnMonthChangeListener;
import com.threehalf.calendarview.listener.OnMonthScrollListener;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.model.MarkedDates;
import com.threehalf.calendarview.utils.CalendarUtil;
import com.threehalf.calendarview.utils.CurrentCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * @author jayqiu
 * @describe
 * @date 2016/7/20 15:08
 */
public class CalendarView extends ViewPager {
    private SlideType slideType = SlideType.ALLSLIDING;//
    private int dateCellViewResId = -1;
    private View dateCellView = null;
    private int markedStyle = -1;
    private int markedCellResId = -1;
    private View markedCellView = null;
    private boolean hasTitle = true;
    private boolean scrollble = true;
    private boolean initted = false;

    private DateData currentDate;
    private CalendarViewFragmentAdapter adapter;

    private int width, height;
    private int currentIndex;
    private int currentPosition;


    public CalendarView(Context context) {
        super(context);
        if (context instanceof FragmentActivity) {
            init((FragmentActivity) context);
        }
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (context instanceof FragmentActivity) {
            init((FragmentActivity) context);
        }
    }

    public void init(FragmentActivity activity) {
        if (initted) {
            return;
        }
        initted = true;
        if (currentDate == null) {
            currentDate = CurrentCalendar.getCurrentDateData();
        }
        // TODO: 15/8/28 Will this cause trouble when achieved?
        if (this.getId() == View.NO_ID) {
            this.setId(R.id.calendarViewPager);
        }
        adapter = new CalendarViewFragmentAdapter(activity.getSupportFragmentManager()).setDate(currentDate);
        adapter.setPositionCount(positionCount);
        this.setAdapter(adapter);
        setOffscreenPageLimit(positionCount);
        setSlideType();
        float density = getContext().getResources().getSystem().getDisplayMetrics().density;
        CellConfig.cellHeight = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;
        CellConfig.cellWidth = getContext().getResources().getSystem().getDisplayMetrics().widthPixels / 7 / density;
    }

    private void setSlideType() {
        switch (slideType) {
            case ONLY_LEFT:
                this.setCurrentItem(0);
                break;
            case ONLY_RIGHT:
                if (positionCount >= 1) {
                    this.setCurrentItem(positionCount - 1);
                }

                break;
            case ALLSLIDING:
                this.setCurrentItem(positionCount / 2);
                break;
        }
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    public CalendarView travelTo(DateData dateData) {
        int tmpePosition;
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateData.getYear(), dateData.getMonth() - 1, dateData.getDay());
        tmpePosition = positionCount / 2;
        if (dateData != null) {
            switch (slideType) {
                case ONLY_LEFT:

                    calendar.add(Calendar.MONTH, tmpePosition);// tmpePosition 月后

                    break;
                case ONLY_RIGHT:
                    if (positionCount % 2 == 0) {// 偶数 取前一个月
                        calendar.add(Calendar.MONTH, -tmpePosition + 1);
                    } else {
                        calendar.add(Calendar.MONTH, -tmpePosition);
                    }
                    break;
                case ALLSLIDING:
                    break;
            }

        }
        dateData = new DateData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        this.currentDate = dateData;
        CalendarUtil.date = dateData;
        this.initted = false;
        CellConfig.currentDate = dateData;
        CellConfig.middlePosition = tmpePosition;
        CellConfig.Month2WeekPos = tmpePosition;
        CellConfig.Week2MonthPos = tmpePosition;
        CellConfig.m2wPointDate = null;
        CellConfig.w2mPointDate = null;
        init((FragmentActivity) getContext());
        return this;
    }

    public static String time(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(calendar.getTime());
        Log.e("TIME=", "==========:" + dateString);
        return dateString;
    }

    /**
     * 设置mark 时间
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public CalendarView markDate(int year, int month, int day) {
        MarkedDates.getInstance().add(new DateData(year, month, day));
        return this;
    }

    /**
     * 移除mark 时间
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public CalendarView unMarkDate(int year, int month, int day) {
        MarkedDates.getInstance().remove(new DateData(year, month, day));
        return this;
    }

    /**
     * 设置mark 时间
     *
     * @param date
     * @return
     */
    public CalendarView markDate(DateData date) {
        MarkedDates.getInstance().add(date);

        return this;
    }

    /**
     * 设置mark 时间
     *
     * @param date
     * @return
     */
    public CalendarView markDate(ArrayList<DateData> date) {
        MarkedDates.getInstance().addAll(date);
        return this;
    }

    /**
     * 移除mark 时间
     *
     * @param date
     * @return
     */
    public CalendarView unMarkDate(DateData date) {
        MarkedDates.getInstance().remove(date);
        return this;
    }

    /**
     * 全部清除
     *
     * @return
     */
    public CalendarView unMarkAll() {
        MarkedDates.getInstance().removeAdd();
        return this;
    }

    /**
     * 获取mark
     *
     * @return
     */
    public CalendarView getMarkedDates() {
        MarkedDates.getInstance();
        return this;
    }

    public CalendarView setDateCell(int resId) {
        adapter.setDateCellId(resId);
        return this;
    }

    public CalendarView setMarkedStyle(int style, int color) {
        MarkStyle.current = style;
        MarkStyle.defaultColor = color;
        return this;
    }

    public CalendarView setMarkedStyle(int style) {
        MarkStyle.current = style;
        return this;
    }

    public CalendarView setMarkedCell(int resId) {
        adapter.setMarkCellId(resId);
        return this;
    }

    public CalendarView setOnMonthChangeListener(OnMonthChangeListener listener) {
        this.addOnPageChangeListener(listener);
        return this;
    }

    public CalendarView setOnMonthScrollListener(OnMonthScrollListener listener) {
        this.addOnPageChangeListener(listener);
        return this;
    }

    public CalendarView setOnDateClickListener(OnDateClickListener onDateClickListener) {
        OnDateClickListener.instance = onDateClickListener;
        return this;
    }

    public CalendarView hasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        adapter.setTitle(hasTitle);
        return this;
    }


    public void toLeft(int position) {
        if (position >= 1)
            this.setCurrentItem(position - 1);
    }

    public void toRight(int position) {
        this.setCurrentItem(position + 1);
    }


    public void measureCurrentView(int currentIndex) {
        this.currentIndex = currentIndex;
        Log.e("====", "==" + currentIndex);
        requestLayout();
    }

    private int positionCount = 12;

    public void setSlideType(SlideType slideType) {
        this.slideType = slideType;
        setSlideType();
    }

    public SlideType getSlideType() {
        return slideType;
    }

    public enum SlideType {
        ONLY_LEFT, ONLY_RIGHT, ALLSLIDING

    }
    public  void onDestroy(){
        MarkedDates.getInstance().removeAdd();
        OnExpDateClickListener.lastMarkView=null;
        OnExpDateClickListener.lastMarkClickedDate= CurrentCalendar.getCurrentDateData();
    }


    public int getPositionCount() {
        return positionCount;
    }

    public void setPositionCount(int positionCount) {
        this.positionCount = positionCount;
    }

    @Override
    protected void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        width = measureWidth(measureWidthSpec);
        height = measureHeight(measureHeightSpec);
        measureHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(measureWidthSpec, measureHeightSpec);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 0;
        if (specMode == MeasureSpec.AT_MOST) {
            float destiney = getContext().getResources().getSystem().getDisplayMetrics().density;
            result = (int) (CellConfig.cellWidth * 7 * destiney);
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) CellConfig.cellHeight;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        float density = getContext().getResources().getSystem().getDisplayMetrics().density;
        if (specMode == MeasureSpec.AT_MOST) {
            if (CellConfig.ifMonth)
                return (int) (CellConfig.cellHeight * 6 * density);
            else
                return (int) (CellConfig.cellHeight * density);
        } else if (specMode == MeasureSpec.EXACTLY) {
            return specSize;
        } else {
            return (int) CellConfig.cellHeight;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isScrollble() {
        return scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!scrollble)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }


}

