package com.example.timinglogger;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.timinglogger.ZyqLogger;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private final Context mContext;
    private List<PackageInfo> packageInfos;

    public DataAdapter(List<PackageInfo> infos, Context context) {
        this.packageInfos = infos;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZyqLogger.i("viewType = "+viewType);
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.icon.setImageDrawable(packageInfos.get(position).applicationInfo.loadIcon(mContext.getPackageManager()));
        holder.label.setText(packageInfos.get(position).applicationInfo.loadLabel(mContext.getPackageManager()));
        if (position == getItemCount()-1){
            ZyqLogger.dump();
        }
    }

    @Override
    public int getItemCount() {
        return packageInfos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView label;
        public MyViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.app_icon);
            label = (TextView)itemView.findViewById(R.id.label);
        }
    }
}
