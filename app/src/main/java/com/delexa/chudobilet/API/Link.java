package com.delexa.chudobilet.API;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Link {

    @GET("4TLZGzNE")
    Call<List<NewsAPI>> getNewsAPI();

    @GET("iJ5aKtm6")
    Call<List<EstablishmentAPI>> getEstablishmentAPI();

    @GET("009hMdqG")
    Call<List<EventAPI>> getEventAPI();


}
