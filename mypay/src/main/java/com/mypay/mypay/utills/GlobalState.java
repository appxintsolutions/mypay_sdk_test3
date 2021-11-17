package com.mypay.mypay.utills;

import android.app.Application;

import com.mypay.mypay.helper.MyPayCred;

public class GlobalState extends Application {
    private static GlobalState mInstance;
    private MyPayCred myPayCred;


    public MyPayCred getMyPayCred() {
        return myPayCred;
    }

    public void setMyPayCred(MyPayCred myPayCred) {
        this.myPayCred = myPayCred;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static synchronized GlobalState getInstance() {
        if (mInstance == null) {
            mInstance = new GlobalState();
        }
        return mInstance;
    }
}
