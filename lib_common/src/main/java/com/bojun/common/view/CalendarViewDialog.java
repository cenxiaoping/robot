package com.bojun.common.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.style.LineBackgroundSpan;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bojun.common.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import androidx.annotation.NonNull;


/**
 * 简  述  日期选择弹窗
 * 作  者  guanliusheng
 * 包  名  bedsidesystem.bojun.com.bedsinformationsystem.common.dialog
 * 时  间  2019/7/29 0029 10:59
 */
public class CalendarViewDialog extends Dialog {
    MaterialCalendarView dialogCalendarView;
    private Context mContext;


    private int showX;
    private int showY;

    public CalendarViewDialog(@NonNull Context context) {
        super(context, R.style.NormalDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calendarview);
        dialogCalendarView = findViewById(R.id.dialog_calendarView);
        dialogCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                StringBuffer buffer = new StringBuffer();
                int yearOne = day.getYear();
                int monthOne = day.getMonth();
                buffer.append(yearOne).append("年").append(monthOne).append("月");
                return buffer;
            }
        });
        Collection<CalendarDay> c = new ArrayList();
        c.add(CalendarDay.today());
        dialogCalendarView.addDecorator(new EventDecorator(Color.RED, c));
    }

    public MaterialCalendarView getMaterialCalendarView() {
        return dialogCalendarView;
    }

    public void setSelectDate(String selectDate) {
        String[] split = selectDate.split("-");
        dialogCalendarView.setSelectedDate(CalendarDay.from(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
    }

    public void setCurrentDate(String selectDate) {
        String[] split = selectDate.split("-");
        dialogCalendarView.setCurrentDate(CalendarDay.from(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
    }

    @Override
    public void show() {
        this.setCanceledOnTouchOutside(true);
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.x = 155;//设置x坐标
//        layoutParams.y = 75;//设置y坐标

        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().setAttributes(layoutParams);
        getWindow().setDimAmount(0f);

    }


    public void showXY(int showX, int showY) {

        this.setCanceledOnTouchOutside(true);
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = showX;//设置x坐标
        layoutParams.y = showY;//设置y坐标
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setGravity(Gravity.LEFT | Gravity.TOP);
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        getWindow().setDimAmount(0f);
        super.show();
    }


    public class EventDecorator implements DayViewDecorator {

        private int color;
        private HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotTopSpan((float) 3.5, color));

        }
    }


    public class DotTopSpan implements LineBackgroundSpan {

        /**
         * Default radius used
         */
        public static final float DEFAULT_RADIUS = 3;

        private final float radius;
        private final int color;

        /**
         * Create a span to draw a dot using default radius and color
         *
         * @see #DotTopSpan(float, int)
         * @see #DEFAULT_RADIUS
         */
        public DotTopSpan() {
            this.radius = DEFAULT_RADIUS;
            this.color = 0;
        }

        /**
         * Create a span to draw a dot using a specified color
         *
         * @param color color of the dot
         * @see #DotTopSpan(float, int)
         * @see #DEFAULT_RADIUS
         */
        public DotTopSpan(int color) {
            this.radius = DEFAULT_RADIUS;
            this.color = color;
        }

        /**
         * Create a span to draw a dot using a specified radius
         *
         * @param radius radius for the dot
         * @see #DotTopSpan(float, int)
         */
        public DotTopSpan(float radius) {
            this.radius = radius;
            this.color = 0;
        }

        /**
         * Create a span to draw a dot using a specified radius and color
         *
         * @param radius radius for the dot
         * @param color  color of the dot
         */
        public DotTopSpan(float radius, int color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void drawBackground(
                Canvas canvas, Paint paint,
                int left, int right, int top, int baseline, int bottom,
                CharSequence charSequence,
                int start, int end, int lineNum
        ) {
            int oldColor = paint.getColor();
            if (color != 0) {
                paint.setColor(color);
            }
            canvas.drawCircle((left + right) * 4 / 5, top + radius + 2, radius, paint);
            paint.setColor(oldColor);
        }
    }
}
