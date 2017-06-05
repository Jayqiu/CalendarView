package com.threehalf.calendarview.adpter;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.fragment.MonthFragment;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.view.CalendarView;

/**
 * @author jayqiu
 * @describe
 * @date 2016/7/20 14:45
 */
public class CalendarViewFragmentAdapter  extends FragmentStatePagerAdapter {
    private DateData date;
    private int dateCellId;
    private int markCellId;
    private boolean hasTitle = true;

    private Context context;
    private int mCurrentPosition = -1;
    private int positionCount=1000;

    public CalendarViewFragmentAdapter(FragmentManager fm) {
        super(fm);

    }

    public CalendarViewFragmentAdapter setDate(DateData date){
        this.date = date;
        return this;
    }

    public void setPositionCount(int positionCount) {
        this.positionCount = positionCount;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CalendarViewFragmentAdapter setDateCellId(int dateCellRes){
        this.dateCellId =  dateCellRes;
        return this;
    }


    public CalendarViewFragmentAdapter setMarkCellId(int markCellId){
        this.markCellId = markCellId;
        return this;
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        MonthFragment fragment = new MonthFragment();
        fragment.setData(position, dateCellId, markCellId);
        return fragment;
    }

    @Override
    public int getCount() {
        return positionCount ;
    }

    public CalendarViewFragmentAdapter setTitle(boolean hasTitle){
        this.hasTitle = hasTitle;
        return this;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        ((CalendarView) container).measureCurrentView(position);
    }

    /**
     * 重写该方法，为了刷新页面
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        if (object.getClass().getName().equals(MonthFragment.class.getName())) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}
