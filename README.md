#CalendarView日历选择器

![](https://github.com/Jayqiu/CalendarView/blob/master/GIF.gif)
### 1.设置模式 CalendarView.SlideType.
	CalendarView.setSlideType(CalendarView.SlideType.ALLSLIDING);

|模式|说明|
|----|----|
|ALLSLIDING|左右都可以滑动|
|ONLY_RIGHT|只能向右滑动|
|ONLY_LEFT|只能向左滑动|

### 2.设置markDate
	//单个添加
	CalendarView.markDate(new DateData(2018, 1, 2).setMarkStyle(new MarkStyle(MarkStyle.DOT, Color.RED, false)));
 	public CalendarView markDate(DateData date) {
        MarkedDates.getInstance().add(date);
        return this;
    }
	//添加mark数组 ArrayList<DateData> date
	 public CalendarView markDate(ArrayList<DateData> date) {
        MarkedDates.getInstance().addAll(date);
        return this;
    }

### 3. MarkStyle

|模式|说明|
|----|----|
|DOT|小圆点|
|BACKGROUND|圆背景|
|LEFTSIDEBAR|左矩形|
|RIGHTSIDEBAR|右矩形|

### 4. 监听
1.具体日期监听

```java
	CalendarView.setOnDateClickListener(new OnExpDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                super.onDateClick(view, date);
                Toast.makeText(MainActivity.this, String.format("%d-%d-%d",date.getYear(), date.getMonth(), 			date.getDay()), Toast.LENGTH_SHORT).show();
            }
        });
```

2.左右滑动监听

```java
CalendarView.setOnMonthScrollListener(new OnMonthScrollListener() {
            @Override
            public void onMonthChange(int year, int month) {
                mTvTitle.setText(year + "年" + month + "月");
            }

            @Override
            public void onMonthScroll(int position, float positionOffset) {

            }
        });
```
### 5.其他设置
	左滑动到指定的position
	CalendarView.toLeft(position)
	右滑动到指定的position
	CalendarView.toRight(position);
	一共多少个月
	CalendarView.setPositionCount(2);
	移动到指定的日子
	CalendarView.travelTo(new DateData(2015, 8, 31));
	//设置是否可以左右滑动
	public void setScrollble(boolean scrollble) {
            this.scrollble = scrollble;
        }

