package com.delexa.chudobilet.API;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPIUpdater implements Callback<List<NewsAPI>> {

    Context context;
    Retrofit retrofit;
    Link service;

    public NewsAPIUpdater(Context context) {
        this.context = context;
    }

    public void update() {

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pastebin.com/raw/")
                .build();
        service = retrofit.create(Link.class);
        Call<List<NewsAPI>> chudobilet = service.getNewsAPI();
        chudobilet.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<NewsAPI>> call, Response<List<NewsAPI>> response) {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(context);
        SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
        ChudobiletDatabaseHelper.insertNewsAPI(db, response);
    }

    @Override
    public void onFailure(Call<List<NewsAPI>> call, Throwable t) {

        Toast toast = Toast.makeText(context, "Не удается соединиться с сервером! Приложение работает" +
                " в оффлайн режиме! Проверьте подключение!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
