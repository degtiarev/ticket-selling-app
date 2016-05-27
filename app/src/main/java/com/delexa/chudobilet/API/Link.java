package com.delexa.chudobilet.API;


import com.delexa.chudobilet.MainClasses.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Link {

    @GET("4TLZGzNE")
    Call<List<NewsAPI>> getNewsAPI();




}
