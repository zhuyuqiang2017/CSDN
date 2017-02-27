package com.example.recycleviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mListView;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (RecyclerView) findViewById(R.id.list);
        initData();
        MyAdapter adapter = new MyAdapter(MainActivity.this,mDatas);
        mListView.setAdapter(adapter);
        mListView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL_LIST));
        mListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void initData(){
        mDatas = new ArrayList<String>();
        for(int i = 'A';i<='z';i++){
            mDatas.add(""+(char)i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private List<String> Strings;
        private Context mContext;
        public MyAdapter(Context context, List<String>data){
            this.mContext = context;
            this.Strings = data;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item_layout,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(Strings.get(position));
        }

        @Override
        public int getItemCount() {
            return Strings.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView)itemView.findViewById(R.id.tv_info);
            }
        }
    }
}
