package com.opensource.androidrepository.ui.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/10/30.
 */

public class FocusRectView extends View {
    //    声明Paint对象
    private Paint mPaint = null;
    private int StrokeWidth = 6;
    private Rect rect;//手动绘制矩形
    private int with;
    private int height;

    int left, top, right, bottom;

    public FocusRectView(Activity context){
        super(context);
        init();
    }

    public FocusRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        with = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        //构建对象
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(StrokeWidth);
        mPaint.setColor(Color.RED);
        left = (with-467)/2;
        top = 400;
        right = left + 467;
        bottom = top + 467;
        rect = new Rect(left,top,right,bottom);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rect,mPaint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int count = event.getPointerCount();
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if(count == 1){
                    rect.left  = x - (right - left)/2;
                    rect.top  = y - (bottom - top)/2;
                    rect.right  =x + (right - left)/2;
                    rect.bottom  = y + (bottom - top)/2;
                    invalidate(rect);
                }

            case MotionEvent.ACTION_MOVE:
                if(count == 2){
                    drawRect(event);
                }

                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return  true;//处理了触摸信息，消息不再传递
    }

    private void drawRect(MotionEvent event){
        int x1 = (int)event.getX(0);
        int y1 = (int)event.getY(0);

        int x2 = (int)event.getX(1);
        int y2 = (int)event.getY(1);

        rect.left = left = x1>x2 ? x2 : x1;
        rect.top = top = y1>y2 ? y2 : y1;
        rect.right = right = left + Math.abs(x1 - x2);
        rect.bottom = bottom = top + Math.abs(y1 - y2);
        invalidate(rect);
    }

}
