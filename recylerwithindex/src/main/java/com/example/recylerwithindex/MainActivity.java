package com.example.recylerwithindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_list)
    RecyclerView mRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRv.setAdapter(new DataAdapter(MainActivity.this,BeanGenerator.getAllBens()));
        mRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRv.addItemDecoration(new SuspensionDecoration(MainActivity.this,BeanGenerator.getAllBens()));
    }
}
