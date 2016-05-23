package com.delexa.chudobilet;


import com.delexa.chudobilet.DBClasses.Event;

import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Link {
    @POST("/{url}")
    public Event getJournalInfo(@Path("url") String urlEvent);
}
