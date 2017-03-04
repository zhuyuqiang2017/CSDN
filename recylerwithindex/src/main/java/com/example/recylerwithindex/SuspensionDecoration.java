package com.example.recylerwithindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class SuspensionDecoration extends RecyclerView.ItemDecoration {
    private List<Bean> mBeans;
    private Context mContext;
    private int bg_color = Color.parseColor("#FFDFDFDF");
    private int text_color = Color.RED;
    private int mTitleHeight;
    private static int mTitleFontSize;
    private Rect mBounds = new Rect();
    public SuspensionDecoration(Context context,List<Bean> mBeans) {
        super();
        this.mBeans = mBeans;
        mContext = context;
        mTitleHeight = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30,mContext.getResources().getDisplayMetrics());
        mTitleFontSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16,mContext.getResources().getDisplayMetrics());
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);;
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth()-parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0;i<childCount;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
//            int position = i;
            if (mBeans == null || mBeans.isEmpty() || position > mBeans.size() - 1 || position < 0 ) {
                continue;//越界
            }
//            Log.d("zhuyuqiang","position ="+position);
            if(position > -1){
                if (position == 0){
//                    Log.d("zhuyuqiang","position =0");
                    drawTitle(child,left,right,position,params,c);
                }else if (!mBeans.get(position).getTag().equals(mBeans.get(position-1).getTag())){
                    drawTitle(child,left,right,position,params,c);
                }else{
                    drawDivider(child,left,right,c);
                }
            }
        }
    }

    private void drawDivider(View child, int left, int right,  Canvas c) {
        final int l = left;
        final int r = right;
        final int t = child.getTop()-2;
        final int b = child.getTop();
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setAntiAlias(true);
        c.drawRect(l,t,r,b,p);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int pos = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
        Log.d("zhuyuqiang","pos = "+pos);
        if (mBeans == null || mBeans.isEmpty() || pos > mBeans.size() - 1 || pos < 0 ) {
            return;//越界
        }

        String tag = mBeans.get(pos).getTag();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;//出现一个奇怪的bug，有时候child为空，所以将 child = parent.getChildAt(i)
        boolean flag = false;//定义一个flag，Canvas是否位移过的标志
        if ((pos + 1) < mBeans.size()) {//防止数组越界（一般情况不会出现）
            if (null != tag && !tag.equals(mBeans.get(pos + 1).getTag())) {//当前第一个可见的Item的tag，不等于其后一个item的tag，说明悬浮的View要切换了
                if (child.getHeight() + child.getTop() < mTitleHeight) {//当第一个可见的item在屏幕中还剩的高度小于title区域的高度时，我们也该开始做悬浮Title的“交换动画”
//                    c.save();//每次绘制前 保存当前Canvas状态，
//                    flag = true;
                    c.translate(0, child.getHeight() + child.getTop() - mTitleHeight);
                }
            }
        }
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + mTitleHeight, mPaint);
        mPaint.setColor(text_color);
        mPaint.setTextSize(mTitleFontSize);
        mPaint.getTextBounds(tag, 0, tag.length(), mBounds);
        c.drawText(tag, child.getPaddingLeft()+10,
                parent.getTop() + mTitleHeight - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
//        if (flag)
//            c.restore();//恢复画布到之前保存的状态
    }

    private void drawTitle(View v,int left ,int right, int position,RecyclerView.LayoutParams param,Canvas c) {
        String text = mBeans.get(position).getTag();
        final int l = left;
        final int r = right;
        final int t = v.getTop()-mTitleHeight;
        final int b = v.getTop();
        Paint mPaint = new Paint();
        mPaint.setColor(bg_color);
        c.drawRect(l,t,r,b,mPaint);
        mPaint.setColor(text_color);
        mPaint.setTextSize(mTitleFontSize);
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        c.drawText(text,v.getLeft()+10,v.getTop()-(mTitleHeight/2-mBounds.height()/2),mPaint);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        if (mBeans == null || mBeans.isEmpty() || position > mBeans.size() - 1 || position < 0 ) {
            return;//越界
        }
        if(position > -1){
            if (position == 0){
                outRect.set(0, mTitleHeight, 0, 0);
            }else if (!mBeans.get(position).getTag().equals(mBeans.get(position-1).getTag())){
                outRect.set(0, mTitleHeight, 0, 0);
            }else{
                outRect.set(0,2,0,0);//保留用来画分割线
            }
        }
    }
}
