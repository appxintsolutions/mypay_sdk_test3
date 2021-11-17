package com.mypay.mypay.networks;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiPaths {
    //TODO: Request Make A Checkout Url
    @FormUrlEncoded
    @POST("request")
    Call<CheckoutUrl> makeCheckoutUrl(@Field("private_key") String private_key,
                                     @Field("currency") String currency,
                                     @Field("is_fallback") String is_fallback,
                                     @Field("fallback_url") String fallback_url,
                                     @Field("is_test") String is_test,
                                     @Field("amount") String amount,
                                     @Field("purpose") String purpose,
                                     @Field("order_id") String order_id);
}
