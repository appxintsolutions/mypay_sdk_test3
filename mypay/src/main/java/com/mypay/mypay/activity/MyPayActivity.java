package com.mypay.mypay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mypay.mypay.R;
import com.mypay.mypay.helper.MyPayCred;
import com.mypay.mypay.networks.ApiClient;
import com.mypay.mypay.networks.ApiPaths;
import com.mypay.mypay.networks.CheckoutUrl;
import com.mypay.mypay.utills.DetectConnection;
import com.mypay.mypay.utills.GlobalState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPayActivity extends AppCompatActivity {
    //      private String private_key=null;
    //      private String currency=null;
    //      private String is_fallback=null;
    //      private String fallback_url=null;
    //      private String is_test=null;
    //      private String amount=null;
    //      private String purpose=null;
    //      private String order_id=null;
    //      private Context context;
    private Context context;
    ProgressDialog progressDialog;
    private WebView webview;
    private ProgressBar progressBar;
    private String url="https://www.google.com/";
    private MyPayCred myPayCred;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pay);
        webview =  findViewById(R.id.webLayout);
        progressBar =  findViewById(R.id.progressBar);
        context=this;
        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        myPayCred= GlobalState.getInstance().getMyPayCred();
        if(myPayCred!=null){
            callRequestMakeCheckOutUrl(myPayCred);
        }else{
            Toast.makeText(getApplicationContext(), "Invalid Credetial!", Toast.LENGTH_SHORT).show();
        }
    }
    private void callRequestMakeCheckOutUrl(MyPayCred myPayCred) {
        progressDialog.show();
        Call<CheckoutUrl> call = ApiClient.getRetrofit().
                create(ApiPaths.class).
                makeCheckoutUrl(myPayCred.getPrivate_key(),
                        myPayCred.getCurrency(),
                        myPayCred.getIs_fallback(),
                        myPayCred.getFallback_url(),
                        myPayCred.getIs_test(),
                        myPayCred.getAmount(),
                        myPayCred.getPurpose(),
                        myPayCred.getOrder_id());
        call.enqueue(new Callback<CheckoutUrl>() {
            @Override
            public void onResponse(Call<CheckoutUrl> call, Response<CheckoutUrl> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        url=response.body().getCheckout_url();
                        goToWebView(url);
                    }
                }else {
                    String x="sd";
                    // showToast(response.message());
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<CheckoutUrl> call, Throwable t) {
                //showToast(t.getMessage());
                String x="sd";
                progressDialog.dismiss();
            }
        });

    }
    private void goToWebView(String url) {
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
            this.onBackPressed();
        } else {
            if(url.contains("https://mypay.pk/customer/checkout")){
                Log.d("falllbackurlsAYA1",url);
                webview.setWebViewClient(new CustomWebViewClient());
                webview.getSettings().setJavaScriptEnabled(true);
                webview.getSettings().setDomStorageEnabled(true);
                webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
                webview.setWebChromeClient(new WebChromeClient());
                webview.loadUrl(url);
            }else{
                Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(webview.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            Log.d("falllbackurlsAYA2",url);
        }
        //        @Override
//        public void onLoadResource(WebView view, String url) {
//
//            try {
//                view.loadUrl("javascript:(window.onload = function() { " +
//                        "(elem1 = document.getElementById('id1')); elem.parentNode.removeChild(elem1); " +
//                        "(elem2 = document.getElementById('id2')); elem2.parentNode.removeChild(elem2); " +
//                        "})()");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // view.loadUrl("javascript:var footer = document.getElementById(\"footer\"); footer.parentNode.removeChild(footer); var header = document.getElementById(\"header-full\"); header.parentNode.removeChild(header);");
            progressBar.setVisibility(View.GONE);
            view.setVisibility(webview.VISIBLE);
            webview.loadUrl("javascript:(function() { " +
                    "var head = document.getElementsByTagName('footer')[0];"
                    + "head.parentNode.removeChild(head);" +
                    "})()");
//            int index = url.indexOf("FORM?");
//            if (index != -1){
//                String d = URLDecoder.decode(url.substring(index+5));
//                Toast.makeText(mActivity,d,Toast.LENGTH_SHORT).show();
//            }

            super.onPageFinished(view, url);
        }
    }
}