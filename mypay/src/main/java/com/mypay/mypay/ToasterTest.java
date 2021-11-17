package com.mypay.mypay;


import android.content.Context;
import android.widget.Toast;

public class ToasterTest {

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static String getPaymentUrl() {
        String urlb = "https://mypay.pk/";
        return urlb;
    }
}
