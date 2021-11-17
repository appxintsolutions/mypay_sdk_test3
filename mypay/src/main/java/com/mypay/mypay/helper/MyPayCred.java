package com.mypay.mypay.helper;

import android.content.Context;

public class MyPayCred {
    private String private_key;
    private String currency;
    private String is_fallback;
    private String fallback_url;
    private String is_test;
    private String amount;
    private String purpose;
    private String order_id;
    private Context context;

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIs_fallback() {
        return is_fallback;
    }

    public void setIs_fallback(String is_fallback) {
        this.is_fallback = is_fallback;
    }

    public String getFallback_url() {
        return fallback_url;
    }

    public void setFallback_url(String fallback_url) {
        this.fallback_url = fallback_url;
    }

    public String getIs_test() {
        return is_test;
    }

    public void setIs_test(String is_test) {
        this.is_test = is_test;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
