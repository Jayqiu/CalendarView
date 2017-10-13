package com.threehalf.calendarview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

import com.threehalf.calendarview.adpter.CalendarAdapter;
import com.threehalf.calendarview.model.MonthData;
import com.threehalf.calendarview.model.MonthWeekData;


/**
 */
public class MonthView extends GridView {
    private MonthData monthData;
    private CalendarAdapter adapter;
    private MonthWeekData monthWeekData;

    public MonthView(Context context) {
        this(context,null);
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setNumColumns(7);
        this.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    public void initMonthAdapter(int pagePosition, int cellView, int markView) {
        Log.e("===pagePosition==", pagePosition + "");
        getMonthWeekData(pagePosition);
        adapter = new CalendarAdapter(getContext(), 1, monthWeekData.getData()).setCellViews(cellView, markView);
        this.setAdapter(adapter);

    }

    private void getMonthWeekData(int position) {
        monthWeekData = new MonthWeekData(position);
    }

}
