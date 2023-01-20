package com.example.winter;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        //这个Message msg 就是从另一个线程传递过来的数据
        //解析
        String responseData = msg.obj.toString();
//        System.out.println(responseData);
        jsonDecodeTest(responseData);
    }
}