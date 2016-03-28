package com.zhaoss.changeitemlistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Zhaoss on 2016/3/28.
 */
public class MyRecyclerView extends RecyclerView {

    private OnMoveListener listener;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnMoveListener(OnMoveListener listener){
        this.listener = listener;
    }

    public interface OnMoveListener{
        void onMoveListener(int y);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        return super.fling(velocityX, velocityY/2);
    }

    private float lastY;
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = e.getY();
                float f = lastY-moveY;
                if(f>0 && f<1){
                    f = 1;
                }else if(f<0 && f>-1){
                    f = -1;
                }
                if(listener != null) listener.onMoveListener((int) f);
                lastY = moveY;
                break;
        }
        return super.onTouchEvent(e);
    }
}
