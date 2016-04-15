package com.delexa.chudobilet;


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
import android.widget.TextView;

import com.delexa.chudobilet.Cinema.Movie;
import com.delexa.chudobilet.Cinema.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TabFragment extends Fragment implements Callback<List<Movie>> {

    private String kategoria;
    private String text;
    private TextView tmpTextView;

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    public Retrofit retrofit;
    //public Link service;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.MeropriatiaList);

//        retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://pastebin.com/raw/")
//                .build();
//        service = retrofit.create(Link.class);

        //  Call<List<Movie>> magenta = service.listRepoEve();
        //   magenta.enqueue(this);


        // adapter = new MovieAdapter(getActivity(), getData(getKategoria().toString()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //  tmpTextView.setText(text);
        return v;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        //tmpTextView.setText(text);
    }


    public List<Movie> getData(String kat) {

        List<Movie> data = new ArrayList<>();

        ArrayList id_merop = new ArrayList();
        ArrayList iconURL = new ArrayList();
        ArrayList name = new ArrayList();
        ArrayList opisanie = new ArrayList();
        ArrayList reiting = new ArrayList();

//        try {
//            SQLiteOpenHelper magentaDatabaseHelper = new MagentaDatabaseHelper(getActivity(), "magenta", null, 1);
//            SQLiteDatabase db = magentaDatabaseHelper.getReadableDatabase();
//            Cursor cursor = db.query("MEROPRIATIA", new String[]{"_id_meropr","KATEGORIA", "NAME","OPISANIE", "REITING", "PICTURE"},
//                    "KATEGORIA = ?",
//                    new String[] {kat},
//                    null, null, null);
//
//            while (cursor.moveToNext()) {
//                id_merop.add(cursor.getString(cursor.getColumnIndex("_id_meropr")));
//                iconURL.add(cursor.getString(cursor.getColumnIndex("PICTURE")));
//                name.add(cursor.getString(cursor.getColumnIndex("NAME")));
//                opisanie.add(cursor.getString(cursor.getColumnIndex("OPISANIE")));
//                reiting.add(cursor.getString(cursor.getColumnIndex("REITING")));
//            }
//            cursor.close();
//            db.close();
//
//        } catch (SQLiteException e) {
//        }

        for (int i = 0; i < id_merop.size() && i < name.size() && i < opisanie.size(); i++) {

            Movie current = new Movie();

//            current._id_meropr = Integer.parseInt(id_merop.get(i).toString());
//            current.pictureURL = iconURL.get(i).toString();
//            current.name = name.get(i).toString();
//            current.opisanie = opisanie.get(i).toString();
//            current.reiting = reiting.get(i).toString();
//            data.add(current);

        }
        return data;
    }


    @Override
    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

        // System.out.println("Мероприятия");
        //System.out.println(response.body());
//        SQLiteOpenHelper magentaDatabaseHelper = new MagentaDatabaseHelper(App.instance.getApplicationContext(),"magenta",null,1);
//        SQLiteDatabase db = magentaDatabaseHelper.getWritableDatabase();
//        db.execSQL("delete from "+ "MEROPRIATIA");
//
//        for(Movie k: response.body()){
//            System.out.println(k._id_meropr);
//            String insertQuery = "INSERT INTO MEROPRIATIA" +
//                    " ( _id_meropr, KATEGORIA, NAME,OPISANIE,REITING,PICTURE) VALUES " +
//                    "("+"'"+k._id_meropr+"', "+"'"+k.kategoria+"', "+"'"+k.name+"', "+"'"+k.opisanie+"', "+"'"+k.reiting+"', "+"'"+k.pictureURL+"'"+")";
//            db.execSQL(insertQuery);
//
//        }
//        db.close();
    }

    @Override
    public void onFailure(Call<List<Movie>> call, Throwable t) {
        System.out.println("CallListMovie " + t.getLocalizedMessage());
    }
}
