package com.threehalf.calendarview.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.threehalf.calendarview.MarkStyle;
import com.threehalf.calendarview.listener.OnDateClickListener;
import com.threehalf.calendarview.model.DayData;
import com.threehalf.calendarview.model.MarkedDates;
import com.threehalf.calendarview.utils.CurrentCalendar;
import com.threehalf.calendarview.view.BaseCellView;
import com.threehalf.calendarview.view.BaseMarkView;
import com.threehalf.calendarview.view.DefaultCellView;
import com.threehalf.calendarview.view.DefaultMarkView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



/**
 * 日期Adpter
 */
public class CalendarAdapter extends ArrayAdapter implements Observer {
    private ArrayList data;
    private int cellView = -1;
    private int markView = -1;
    public CalendarAdapter(Context context, int resource, ArrayList data) {
        super(context, resource);
        this.data = data;
        MarkedDates.getInstance().addObserver(this);
    }

    public CalendarAdapter setCellViews(int cellView, int markView){
        this.cellView = cellView;
        this.markView = markView;
        return this;
    }


    public View getView(int position, View convertView, ViewGroup viewGroup){
        View ret = null;
        DayData dayData = (DayData) data.get(position);
        MarkStyle style = MarkedDates.getInstance().check(dayData.getDate());


        dayData.getDate().setMarkStyle(style);
        if (markView > 0){
            BaseMarkView baseMarkView = (BaseMarkView) View.inflate(getContext(), markView, null);
            baseMarkView.setDisplayText(dayData);
            ret = baseMarkView;
        } else {
            ret = new DefaultMarkView(getContext());// 默认 MarkView
            ((DefaultMarkView) ret).setDisplayText(dayData);

        }
        if (OnDateClickListener.instance != null) {
            ((BaseMarkView) ret).setOnDateClickListener(OnDateClickListener.instance);
        }
        ((BaseCellView) ret).setDate(dayData.getDate());
        return ret;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public void update(Observable observable, Object data) {
        this.notifyDataSetChanged();
    }
}
