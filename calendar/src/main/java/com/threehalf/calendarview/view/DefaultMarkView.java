package com.threehalf.calendarview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.threehalf.calendarview.CalendarMarkStyle;
import com.threehalf.calendarview.CellConfig;
import com.threehalf.calendarview.MarkStyle;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.model.DayData;


/**
 * @author jayqiu
 * @describe
 * @date 2016/7/20 15:08
 */
public class DefaultMarkView extends BaseMarkView {
    private TextView textView;
    private AbsListView.LayoutParams matchParentParams;
    private int orientation;

    private View sideBar;
    private TextView markTextView;
    private ShapeDrawable circleDrawable;

    public DefaultMarkView(Context context) {
        super(context);
    }

    public DefaultMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initLayoutWithStyle(DayData day) {
        DateData dateData = day.getDate();
        MarkStyle style = dateData.getMarkStyle();

        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        matchParentParams = new AbsListView.LayoutParams((int) CellConfig.cellWidth, (int) CellConfig.cellHeight);
        switch (style.getStyle()) {
            case MarkStyle.DEFAULT:
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setTextColor(Color.WHITE);
                this.setPadding(20, 20, 20, 20);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                if (dateData.getDateType()==1) {// 本月显示
                    circleDrawable = new ShapeDrawable(new OvalShape());
                    circleDrawable.getPaint().setColor(style.getColor());
                    textView.setBackground(circleDrawable);
                }
                this.addView(textView);
                return;
            case MarkStyle.BACKGROUND:
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setTextColor(Color.WHITE);


                if (dateData.getDateType()==1) {// 本月显示
                    circleDrawable = new ShapeDrawable(new OvalShape());
                    circleDrawable.getPaint().setColor(style.getColor());
                    this.setPadding(20, 20, 20, 20);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                    textView.setBackground(circleDrawable);
                }
                this.addView(textView);
                return;
            case MarkStyle.DOT:
                this.setLayoutParams(matchParentParams);
                this.setOrientation(VERTICAL);
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 2.0));
                this.addView(new PlaceHolderVertical(getContext()));
                this.addView(textView);
                RelativeLayout dot=  new Dot(getContext(), style.getColor());
                this.addView(dot);
                if (dateData.getDateType()==1) {// 本月显示
                    dot.setVisibility(VISIBLE);
                }else {
                    dot.setVisibility(INVISIBLE);
                }



                return;
            case MarkStyle.RIGHTSIDEBAR:
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 3.0));

                this.addView(new PlaceHolderHorizontal(getContext()));
                this.addView(textView);

                if (dateData.getDateType()==1) {// 本月显示
                    PlaceHolderHorizontal barRight = new PlaceHolderHorizontal(getContext());
                    barRight.setBackgroundColor(style.getColor());
                    this.addView(barRight);
                }
                return;
            case MarkStyle.LEFTSIDEBAR:
                this.setLayoutParams(matchParentParams);
                this.setOrientation(HORIZONTAL);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 3.0));



                if (dateData.getDateType()==1) {// 本月显示
                    PlaceHolderHorizontal barLeft = new PlaceHolderHorizontal(getContext());
                    barLeft.setBackgroundColor(style.getColor());
                    this.addView(barLeft);
                }
                this.addView(textView);
                this.addView(new PlaceHolderHorizontal(getContext()));
                return;
            default:
                throw new IllegalArgumentException("Invalid Mark Style Configuration!");
        }
    }

    private void setDateTyepText(DateData dateData) {
        if (dateData.getDateType() == 0 || dateData.getDateType() == 2) {// 非本月
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
            if(dateData.getMarkStyle().isShowBg()){
                setBackgroundDrawable(CalendarMarkStyle.today);
            }else {
                setBackgroundDrawable(null);
            }

        }
    }
    @Override
    public void setDisplayText(DayData day) {
        initLayoutWithStyle(day);
        setDateTyepText(day.getDate());
        textView.setText(day.getText());
    }



    /**
     * 设置今天的颜色
     */
    public void setDateToday() {

        setBackgroundDrawable(CalendarMarkStyle.today);
        textView.setTextColor(Color.rgb(105, 75, 125));
    }

    /**
     * 设置mark
     */
    public void setMarkDate() {
        textView.setTextColor(Color.BLACK);
        setBackgroundDrawable(CalendarMarkStyle.today);
    }

    /**
     * 默认显示
     */
    public void setDateNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundDrawable(null);
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

    class PlaceHolderHorizontal extends View {

        LinearLayout.LayoutParams params;

        public PlaceHolderHorizontal(Context context) {
            super(context);
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0);
            this.setLayoutParams(params);
        }

        public PlaceHolderHorizontal(Context context, AttributeSet attrs) {
            super(context, attrs);
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0);
            this.setLayoutParams(params);
        }
    }

    class PlaceHolderVertical extends View {

        LinearLayout.LayoutParams params;

        public PlaceHolderVertical(Context context) {
            super(context);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0);
            this.setLayoutParams(params);
        }

        public PlaceHolderVertical(Context context, AttributeSet attrs) {
            super(context, attrs);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0);
            this.setLayoutParams(params);
        }
    }

    class Dot extends RelativeLayout {

        public Dot(Context context, int color) {
            super(context);
            this.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
            View dotView = new View(getContext());
            LayoutParams lp = new LayoutParams(10, 10);
            lp.addRule(CENTER_IN_PARENT, TRUE);
            dotView.setLayoutParams(lp);
            ShapeDrawable dot = new ShapeDrawable(new OvalShape());

            dot.getPaint().setColor(color);
            dotView.setBackground(dot);
            this.addView(dotView);
        }
    }
}
