package com.example.timinglogger;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private int[] mAttr = new int[]{
        android.R.attr.listDivider
    };
    private final int HORIZON = LinearLayout.HORIZONTAL;
    private final int VERTICAL = LinearLayout.VERTICAL;
    private int mOrientation;
    private Drawable mDivider;
    public DividerItemDecoration(Context context,int Orientation) {
        super();
        TypedArray a = context.obtainStyledAttributes(mAttr);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(Orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZON && orientation!= VERTICAL){
            throw new SecurityException("Illegal Argument ");
        }
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        super.onDrawOver(c, parent);
        ZyqLogger.i("onDrawOver");
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        ZyqLogger.i("onDraw");
        if (mOrientation == HORIZON){
            int childCount = parent.getChildCount();
            int top = parent.getPaddingTop();
            int bottom = parent.getHeight()-parent.getPaddingBottom();
            for (int i = 0;i<childCount;i++){
                final View child = parent.getChildAt(i);
                android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = top + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
        if (mOrientation == VERTICAL){
            int childCount = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth()-parent.getPaddingRight();
            for (int i = 0;i<childCount;i++){
                final View child = parent.getChildAt(i);
                android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
