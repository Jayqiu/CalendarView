package com.threehalf.calendarview.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.threehalf.calendarview.view.MonthView;

/**
 *
 */
public class MonthFragment extends Fragment {
    private int cellView = -1;
    private int markView = -1;
    private int pagePosition ;

    private MonthView monthView;

    public void setData(int pagePosition, int cellView, int markView) {
        this.pagePosition = pagePosition;
        this.cellView = cellView;
        this.markView = markView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        LinearLayout ret = new LinearLayout(getContext());
        ret.setBackgroundColor(Color.WHITE);
        ret.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        monthView = new MonthView(getContext());
        monthView.initMonthAdapter(pagePosition, cellView, markView);
        ret.addView(monthView);
        return ret;
    }

}
