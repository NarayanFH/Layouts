package com.example.layouts.Interface;

import com.example.layouts.Models.PlansModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroFitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @GET("getpricingdetails")
    //on below line we are creating a method to post our data.
    Call<PlansModel> getProData();

//    @GET("geteaosi")
//        Call<BasicProModal> getAll();



//    @GET()
//    Call<GetModel> getId(@Field("user_id") GetModel user_id);
//
//    @POST("userflow/getsummary/")
//    Call<GetModel> getPost(@Body GetModel getModel);
//
//    @GET("getfpfamilydata/")
//    Call<GetFamilyModal> getFamilyDetails(@Query("parent_user_id") int parent_user_id, @Query ("fp_log_id") int fp_log_id);
//
//    @POST("getfpgoalsdata/")
//    Call<GetGoalsModal> getGoalsDetails(@Body GetGoalsModal getGoalsModal);
}
