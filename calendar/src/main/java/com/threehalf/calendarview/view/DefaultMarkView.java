package com.threehalf.calendarview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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
import com.threehalf.calendarview.listener.OnExpDateClickListener;
import com.threehalf.calendarview.model.DateData;
import com.threehalf.calendarview.model.DayData;
import com.threehalf.calendarview.model.MarkedDates;
import com.threehalf.calendarview.utils.CurrentCalendar;


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
        if (style != null) {// mark
            switch (style.getStyle()) {
                case MarkStyle.DEFAULT:
                    this.setLayoutParams(matchParentParams);
                    this.setOrientation(HORIZONTAL);
                    textView.setTextColor(Color.WHITE);
                    this.setPadding(20, 20, 20, 20);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                    if (dateData.getDateType() == 1) {// 本月显示
                        circleDrawable = new ShapeDrawable(new OvalShape());
                        circleDrawable.getPaint().setColor(style.getColor());
                        textView.setBackgroundDrawable(circleDrawable);
                    }
                    this.addView(textView);
                    return;
                case MarkStyle.BACKGROUND:
                    this.setLayoutParams(matchParentParams);
                    this.setOrientation(HORIZONTAL);
                    textView.setTextColor(Color.WHITE);


                    if (dateData.getDateType() == 1) {// 本月显示
                        circleDrawable = new ShapeDrawable(new OvalShape());
                        circleDrawable.getPaint().setColor(style.getColor());
                        this.setPadding(20, 20, 20, 20);
                        textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 1.0));
                        textView.setBackgroundDrawable(circleDrawable);
                    }
                    this.addView(textView);
                    return;
                case MarkStyle.DOT:
                    this.setLayoutParams(matchParentParams);
                    this.setOrientation(VERTICAL);
                    RelativeLayout relativeLayout = new RelativeLayout(getContext());
                    RelativeLayout.LayoutParams rlLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    rlLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
                    textView.setId(dateData.getDay());


//                    dotLayoutParams.setMargins(0, matchParentParams.height / 2, matchParentParams.height / 2, 0);
                    RelativeLayout dot = new Dot(getContext(), style.getColor());
                    relativeLayout.addView(textView, rlLayoutParams);
                    View  view=new PlaceHolderVertical(getContext());
                    relativeLayout.addView(view, rlLayoutParams);

                    RelativeLayout.LayoutParams dotLayoutParams = new RelativeLayout.LayoutParams(dip2px(getContext(), 8), dip2px(getContext(), 8));
                    dotLayoutParams.setMargins(0, dip2px(getContext(),8), dip2px(getContext(),8), 0);
                    dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
                    dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);

                    relativeLayout.addView(dot, dotLayoutParams);
                    this.addView(relativeLayout);


                    if (dateData.getDateType() == 1) {// 本月显示
                        dot.setVisibility(VISIBLE);
                    } else {
                        dot.setVisibility(INVISIBLE);
                    }


                    return;
                case MarkStyle.RIGHTSIDEBAR:
                    this.setLayoutParams(matchParentParams);
                    this.setOrientation(HORIZONTAL);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 3.0));

                    this.addView(new PlaceHolderHorizontal(getContext()));
                    this.addView(textView);

                    if (dateData.getDateType() == 1) {// 本月显示
                        PlaceHolderHorizontal barRight = new PlaceHolderHorizontal(getContext());
                        barRight.setBackgroundColor(style.getColor());
                        this.addView(barRight);
                    }
                    return;
                case MarkStyle.LEFTSIDEBAR:
                    this.setLayoutParams(matchParentParams);
                    this.setOrientation(HORIZONTAL);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float) 3.0));


                    if (dateData.getDateType() == 1) {// 本月显示
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
        } else {
            this.setLayoutParams(matchParentParams);
            this.setOrientation(VERTICAL);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, (float) 1.0));
            this.addView(textView);
        }

    }

    private void setDateTyepText(DateData dateData) {
        if (dateData.getDateType() == 0 || dateData.getDateType() == 2) {// 非本月
            textView.setTextColor(Color.GRAY);
        } else {
            textView.setTextColor(Color.BLACK);
            if (dateData.getMarkStyle().isShowBg()) {
                setBackgroundDrawable(CalendarMarkStyle.today);
            } else {
                setBackgroundDrawable(null);
            }

        }
    }

    @Override
    public void setDisplayText(DayData day) {
        initLayoutWithStyle(day);
        DateData dayDateDate = day.getDate();
        DateData today = CurrentCalendar.getCurrentDateData();
        MarkStyle style = MarkedDates.getInstance().check(day.getDate());
        if (style != null) {// mark

            setDateTyepText(day.getDate());
            textView.setText(day.getText());
            if (OnExpDateClickListener.lastMarkClickedDate.equals(today)) {
                if (day.getDate().equals(today)) {
                    setDateChoose();
                    OnExpDateClickListener.lastMarkView = this;
                    OnExpDateClickListener.lastMarkClickedDate = day.getDate();
                    return;
                }
            }
            if (OnExpDateClickListener.lastMarkClickedDate.equals(dayDateDate)) {
                OnExpDateClickListener.lastMarkView = this;
                OnExpDateClickListener.lastMarkClickedDate = dayDateDate;
                setDateChoose();
                Log.e("222=========>",dayDateDate.getDayString());
            }else {
                if (dayDateDate.equals(today)) {
                    setDateToday();
                }else {
                    setBackgroundOfVersion(CalendarMarkStyle.today);
                }

            }

        } else {

            if (dayDateDate.getDateType() == 0 || dayDateDate.getDateType() == 2) {// 非本月
                textView.setTextColor(Color.GRAY);
            } else {// 本月
                if (OnExpDateClickListener.lastMarkClickedDate.equals(today)) {
                    if (dayDateDate.equals(today)) {// 今天
                        OnExpDateClickListener.lastMarkView = this;
                        OnExpDateClickListener.lastMarkClickedDate = dayDateDate;
                        setDateChoose();
                    }else {
                        if (OnExpDateClickListener.lastMarkClickedDate.equals(dayDateDate)) {
                            OnExpDateClickListener.lastMarkView = this;
                            OnExpDateClickListener.lastMarkClickedDate = dayDateDate;
                            setDateChoose();
                            Log.e("222=========>",dayDateDate.getDayString());
                        }else {
                            if (!CurrentCalendar.lessThanToday(dayDateDate)) {//
                                textView.setTextColor(Color.GRAY);
                            } else {
                                textView.setTextColor(Color.BLACK);
                            }
                            setBackgroundOfVersion(CalendarMarkStyle.white);
                        }
                    }
                }else {
                    if (OnExpDateClickListener.lastMarkClickedDate.equals(dayDateDate)) {
                        OnExpDateClickListener.lastMarkView = this;
                        OnExpDateClickListener.lastMarkClickedDate = dayDateDate;
                        setDateChoose();
                        Log.e("333=========>",dayDateDate.getDayString());
                    } else {
                        if (dayDateDate.equals(today)) {
                            setDateToday();
                        }else {
                            if (!CurrentCalendar.lessThanToday(dayDateDate)) {//
                                textView.setTextColor(Color.GRAY);
                            } else {
                                textView.setTextColor(Color.BLACK);
                            }
                            setBackgroundOfVersion(CalendarMarkStyle.white);
                        }

                    }
                }

            }
            textView.setText(day.getText());
        }

    }


    /**
     * 设置今天的颜色
     */
    public void setDateToday() {

        setBackgroundOfVersion(CalendarMarkStyle.today);
        textView.setTextColor(Color.rgb(45, 153, 244));
    }

    /**
     * 设置mark
     */
    public void setMarkDate() {
        textView.setTextColor(Color.BLACK);
        setBackgroundOfVersion(CalendarMarkStyle.today);
    }

    /**
     * 默认显示
     */
    public void setDateNormal() {
        textView.setTextColor(Color.BLACK);
        setBackgroundOfVersion(null);
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
            LayoutParams lp = new LayoutParams(dip2px(getContext(), 8), dip2px(getContext(), 8));
            lp.addRule(CENTER_IN_PARENT, TRUE);
            dotView.setLayoutParams(lp);
            ShapeDrawable dot = new ShapeDrawable(new OvalShape());

            dot.getPaint().setColor(color);
            dotView.setBackground(dot);
            this.addView(dotView);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
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
