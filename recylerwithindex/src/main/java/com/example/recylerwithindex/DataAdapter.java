package com.example.recylerwithindex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyHolder> {

    private Context mContext;
    private List<Bean> mBeans;
    public DataAdapter(Context context, List<Bean> datas){
        mContext = context;
        mBeans = datas;
    }
    @Override
    public DataAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(DataAdapter.MyHolder holder, int position) {
        holder.tv.setText(mBeans.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.textView);
        }
    }
}
