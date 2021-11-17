package com.mypay.mypay.helper;



import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.widget.Toast;


import com.mypay.mypay.activity.MyPayActivity;
import com.mypay.mypay.utills.GlobalState;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import retrofit2.http.Field;

public class MyPayInitialise {
    private static MyPayInitialise myPayInitialise = null;
    private String private_key;
    private String currency;
    private String is_fallback;
    private String fallback_url;
    private String is_test;
    private String amount;
    private String purpose;
    private String order_id;
    private Context context;

    private MyPayInitialise() {
        private_key=null;
        currency=null;
        is_fallback=null;
        fallback_url=null;
        is_test=null;
        amount=null;
        purpose=null;
        order_id=null;
        context=null;
    }
    public static MyPayInitialise getInstance() {
        if (myPayInitialise == null) {
            myPayInitialise = new MyPayInitialise();
//            StrictMode.ThreadPolicy policy =
//                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);


        }
        return myPayInitialise;
    }
    public static void  setMyPayInitialiseNull () {
        if(myPayInitialise!=null){
            myPayInitialise=null;
        }

    }
    public  void  init (  Context context,
                          String private_key,
                          String currency,
                          String is_fallback,
                          String fallback_url,
                          String is_test,
                          String amount,
                          String purpose,
                          String order_id) {
        this.context=context;
        this.private_key=private_key;
        this.currency=currency;
        this.is_fallback=is_fallback;
        this.fallback_url=fallback_url;
        this.is_test=is_test;
        this.amount=amount;
        this.purpose=purpose;
        this.order_id=order_id;
        if(this.context!=null&&
                this.private_key!=null&&
                this.currency!=null&&
                this.is_fallback!=null&&
                this.fallback_url!=null&&
                this.is_test!=null&&
                this.amount!=null&&
                this.purpose!=null&&
                this.order_id!=null){
            if(this.private_key.isEmpty()||
                    this.currency.isEmpty()||
                    this.is_fallback.isEmpty()||
                    this.fallback_url.isEmpty()||
                    this.is_test.isEmpty()||
                    this.amount.isEmpty()||
                    this.purpose.isEmpty()||
                    this.order_id.isEmpty()){
                Toast.makeText(context,"Invalid Credential",Toast.LENGTH_SHORT).show();
            }else{
                if(checkURL(this.fallback_url)){
                    MyPayCred myPayCred=new MyPayCred();
                    myPayCred.setPrivate_key(this.private_key);
                    myPayCred.setCurrency(this.currency);
                    myPayCred.setIs_fallback(this.is_fallback);
                    myPayCred.setFallback_url(this.fallback_url);
                    myPayCred.setIs_test(this.is_test);
                    myPayCred.setAmount(this.amount);
                    myPayCred.setPurpose(this.purpose);
                    myPayCred.setOrder_id(this.order_id);
                    GlobalState.getInstance().setMyPayCred(myPayCred);
                    context.startActivity(new Intent(context, MyPayActivity.class));
                }   else {
                    Toast.makeText(context,"Invalid FallBack Url",Toast.LENGTH_SHORT).show();
                }

            }
        }else{
            Toast.makeText(context,"Invalid Credential",Toast.LENGTH_SHORT).show();
        }
    }
    private static boolean IsValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            return URLUtil.isValidUrl(urlString) && Patterns.WEB_URL.matcher(urlString).matches();
        } catch (MalformedURLException ignored) {
        }
        return false;
    }
    private static boolean checkURL(CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Pattern URL_PATTERN = Patterns.WEB_URL;
        boolean isURL = URL_PATTERN.matcher(input).matches();
        if (!isURL) {
            String urlString = input + "";
            if (URLUtil.isNetworkUrl(urlString)) {
                try {
                    new URL(urlString);
                    isURL = true;
                } catch (Exception e) {
                }
            }
        }
        return isURL;
    }
}
