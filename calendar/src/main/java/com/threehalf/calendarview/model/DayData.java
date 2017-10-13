package com.threehalf.calendarview.model;

/**

 */
public class DayData {

    private DateData date;
    private int textColor;
    private int textSize;
    private int select;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public DayData(DateData date){
        this.date = date;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getText(){
        return "" + date.getDay();
    }

    public DateData getDate(){
        return date;
    }

}
