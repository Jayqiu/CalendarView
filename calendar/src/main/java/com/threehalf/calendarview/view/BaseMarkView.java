package com.threehalf.calendarview.view;

import android.content.Context;
import android.util.AttributeSet;

import com.threehalf.calendarview.listener.OnDateClickListener;
import com.threehalf.calendarview.model.DateData;


/**
 *
 */
public abstract class BaseMarkView extends BaseCellView{
    private OnDateClickListener clickListener;
    private DateData date;

    public BaseMarkView(Context context) {
        super(context);
    }

    public BaseMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
