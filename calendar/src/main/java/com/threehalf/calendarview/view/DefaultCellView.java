package com.threehalf.calendarview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.threehalf.calendarview.CalendarMarkStyle;
import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.model.DayData;

/**
 * @author jayqiu
 * @describe 默认显示
 * @date 2016/7/20 15:08
 */
public class DefaultCellView extends  BaseCellView {
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
        if(day.getDate().getDateType()==0||day.getDate().getDateType() == 2){// 非本月
            textView.setTextColor(Color.RED);
        }else {
            textView.setTextColor(Color.BLACK);
        }
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
        setBackgroundDrawable(CalendarMarkStyle.choose);
        textView.setTextColor(Color.WHITE);
        return true;
    }

    /**
     * 设置今天的颜色
     */
    public void setDateToday() {
        setBackgroundDrawable(CalendarMarkStyle.today);

        textView.setTextColor(Color.rgb(105, 75, 125));
    }


    /**
     * 默认显示
     */
    public void setDateNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundDrawable(null);
    }

    public void setOtherMonthNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundDrawable(null);
    }

    public void setTextColor(String text, int color) {
        textView.setText(text);
        if (color != 0) {
            textView.setTextColor(color);
        }
    }
}
