package com.threehalf.calendarview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 *  选中颜色配置
 */
public class CalendarMarkStyle {

    public static final int chooseColor = Color.rgb(45, 153, 244);// 选中颜色
    public static final int lightGrayColor = Color.rgb(245, 245, 245);
    public static final int todayColor = Color.rgb(245, 245, 245);
    public static final int whiteColor = Color.rgb(255, 255, 255);
    public static Drawable choose = new Drawable() {
        private Paint paint;

        {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(chooseColor);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawCircle(canvas.getWidth() / 2,
                    canvas.getHeight() / 2,
                    canvas.getHeight() / 3,
                    paint);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };

    public static Drawable today = new Drawable() {
        private Paint paint;

        {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(todayColor);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawCircle(canvas.getWidth() / 2,
                    canvas.getHeight() / 2,
                    canvas.getHeight() / 3,
                    paint);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };

    public static Drawable white = new Drawable() {
        private Paint paint;

        {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(whiteColor);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawCircle(canvas.getWidth() / 2,
                    canvas.getHeight() / 2,
                    canvas.getHeight() / 3,
                    paint);
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }
    };
}
