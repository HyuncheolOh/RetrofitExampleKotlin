package com.example.retrofitexamplekotlin.data.remote;

import com.example.retrofitexamplekotlin.data.model.SOAnswerResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<SOAnswerResponse> getAnswers(@Query("tagged") String tags);
}