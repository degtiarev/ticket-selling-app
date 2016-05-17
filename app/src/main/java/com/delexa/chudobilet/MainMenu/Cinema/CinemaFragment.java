package com.delexa.chudobilet.MainMenu.Cinema;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.DBHelpClasses.Establishment;
import com.delexa.chudobilet.DBHelpClasses.EstablishmentAdapter;
import com.delexa.chudobilet.DBHelpClasses.Event;
import com.delexa.chudobilet.DBHelpClasses.EventAdapter;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CinemaFragment extends Fragment implements Callback<List<Event>> {

    private String type;

    private RecyclerView recyclerView;
    private EventAdapter movieAdapter;
    private EstablishmentAdapter cinemaAdapter;

    // public Retrofit retrofit;
    //public Link service;

    public CinemaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_cinema_tab, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.CinemaEventList);

//        retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://pastebin.com/raw/")
//                .build();
//        service = retrofit.create(Link.class);

        //  Call<List<Event>> magenta = service.listRepoEve();
        //   magenta.enqueue(this);



        if (getType() == "Кинотеатры") {
            cinemaAdapter = new EstablishmentAdapter(getActivity(), getCinemas());
            recyclerView.setAdapter(cinemaAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (getType() == "Фильмы" )

        {
            movieAdapter = new EventAdapter(getActivity(), getMovies());
            recyclerView.setAdapter(movieAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return v;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Event> getMovies() {


        List<Event> data = new ArrayList<>();


        try {
            SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
            SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
            Cursor cursor = ChudobiletDatabaseHelper.getEvents(db,"Кино");

            while (cursor.moveToNext()) {
                Event movie = new Event();


                movie.set_id(cursor.getInt(cursor.getColumnIndex("_id")));

//                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex("COVER"));
//                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
//                movie.setCover(bm);

                movie.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                movie.setGenre(cursor.getString(cursor.getColumnIndex("GENRE")));
                movie.setTime(cursor.getString(cursor.getColumnIndex("AMOUNTTIME")));
                movie.setCountry(cursor.getString(cursor.getColumnIndex("COUNTRY")));
                movie.setForAge(cursor.getString(cursor.getColumnIndex("FORAGE")));
                movie.setYear(cursor.getInt(cursor.getColumnIndex("YEAR")));

                data.add(movie);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
        }


        return data;

    }


    public List<Establishment> getCinemas() {


        List<Establishment> data = new ArrayList<>();


        try {
            SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
            SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
            Cursor cursor = ChudobiletDatabaseHelper.getEstablishment(db,"Кино");

            while (cursor.moveToNext()) {
                Establishment cinema = new Establishment();

                cinema.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                cinema.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                cinema.setAddress(cursor.getString(cursor.getColumnIndex("ADDRESS")));

                data.add(cinema);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {

        }


        return data;

    }


    @Override
    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

        // System.out.println("Мероприятия");
        //System.out.println(response.body());
//        SQLiteOpenHelper magentaDatabaseHelper = new MagentaDatabaseHelper(App.instance.getApplicationContext(),"magenta",null,1);
//        SQLiteDatabase db = magentaDatabaseHelper.getWritableDatabase();
//        db.execSQL("delete from "+ "MEROPRIATIA");
//
//        for(Event k: response.body()){
//            System.out.println(k._id_meropr);
//            String insertQuery = "INSERT INTO MEROPRIATIA" +
//                    " ( _id_meropr, KATEGORIA, NAME,OPISANIE,REITING,PICTURE) VALUES " +
//                    "("+"'"+k._id_meropr+"', "+"'"+k.type+"', "+"'"+k.name+"', "+"'"+k.opisanie+"', "+"'"+k.reiting+"', "+"'"+k.pictureURL+"'"+")";
//            db.execSQL(insertQuery);
//
//        }
//        db.close();
    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {
        System.out.println("CallListMovie " + t.getLocalizedMessage());
    }
}
