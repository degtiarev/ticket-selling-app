package com.delexa.chudobilet.MainMenu.Concert;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class ConcertFragment extends Fragment implements Callback<List<Event>> {

    private String type;

    private RecyclerView recyclerView;
    private EventAdapter concertAdapter;
    private EstablishmentAdapter placeAdapter;


    public ConcertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_event_tab, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.EventList);

//        retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://pastebin.com/raw/")
//                .build();
//        service = retrofit.create(Link.class);

        //  Call<List<Event>> magenta = service.listRepoEve();
        //   magenta.enqueue(this);


        if (getType() == "Места") {
            placeAdapter = new EstablishmentAdapter(getActivity(), getPlaces());
            recyclerView.setAdapter(placeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else if (getType() == "События")

        {
            concertAdapter = new EventAdapter(getActivity(), getConcerts());
            recyclerView.setAdapter(concertAdapter);
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

    public List<Event> getConcerts() {


        List<Event> data = new ArrayList<>();


        try {
            SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
            SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
            Cursor cursor = ChudobiletDatabaseHelper.getEvents(db, "Концерты");

            while (cursor.moveToNext()) {
                Event concert = new Event();


                concert.set_id(cursor.getInt(cursor.getColumnIndex("_id")));

//                byte[] byteArray = cursor.getBlob(cursor.getColumnIndex("COVER"));
//                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
//                movie.setCover(bm);

                concert.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                concert.setGenre(cursor.getString(cursor.getColumnIndex("GENRE")));
                concert.setTime(cursor.getString(cursor.getColumnIndex("AMOUNTTIME")));
                concert.setCountry(cursor.getString(cursor.getColumnIndex("COUNTRY")));
                concert.setForAge(cursor.getString(cursor.getColumnIndex("FORAGE")));
                concert.setYear(cursor.getInt(cursor.getColumnIndex("YEAR")));

                data.add(concert);
            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
        }


        return data;

    }


    public List<Establishment> getPlaces() {


        List<Establishment> data = new ArrayList<>();


        try {
            SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
            SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
            Cursor cursor = ChudobiletDatabaseHelper.getEstablishment(db, "Концерты");

            while (cursor.moveToNext()) {
                Establishment concertPlace = new Establishment();

                concertPlace.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                concertPlace.setName(cursor.getString(cursor.getColumnIndex("NAME")));
                concertPlace.setAddress(cursor.getString(cursor.getColumnIndex("ADDRESS")));

                data.add(concertPlace);
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
        System.out.println("CallListConcert " + t.getLocalizedMessage());
    }

}
