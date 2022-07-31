package com.example.layouts.Interface;

import com.example.layouts.Models.PlansModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitAPI {
    @GET("getpricingdetails")
    Call<ResponseBody> getpricingdetails();
}
