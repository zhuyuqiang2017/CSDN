package com.example.recycleviewdemo;

import android.util.TimingLogger;

/**
 * Created by Administrator on 2017/3/4 0004.
 */

public class ZyqLogger {
    private static TimingLogger time = new TimingLogger("zyq","add item");
    private static ZyqLogger log;
    private ZyqLogger(){}
    public static void i(String msg){
        if (log == null){
            log = new ZyqLogger();
        }
        time.addSplit(msg);
    }
}
