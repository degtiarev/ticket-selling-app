package com.delexa.chudobilet.API;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstablishmmentAPIUpdater implements Callback<List<EstablishmentAPI>> {

    Context context;
    Retrofit retrofit;
    Link service;

    public EstablishmmentAPIUpdater(Context context) {
        this.context = context;
    }

    public void update()
    {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pastebin.com/raw/")
                .build();
        service = retrofit.create(Link.class);
        Call<List<EstablishmentAPI>> chudobilet = service.getEstablishmentAPI();
        chudobilet.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<EstablishmentAPI>> call, Response<List<EstablishmentAPI>> response) {
        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(context);
        SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
        ChudobiletDatabaseHelper.insertEstablishmentAPI(db, response);
    }

    @Override
    public void onFailure(Call<List<EstablishmentAPI>> call, Throwable t) {
        System.out.println("CallListMovie " + t.getLocalizedMessage());
    }

}
