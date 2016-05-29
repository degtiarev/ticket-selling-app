package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.API.Link;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.EstablishmentAdapter;
import com.delexa.chudobilet.Adapters.EventAdapter;
import com.delexa.chudobilet.MainClasses.Establishment;
import com.delexa.chudobilet.MainClasses.Event;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class EstablishmentFragment extends Fragment implements Callback<List<Event>>, SearchView.OnQueryTextListener {

    final static String place = "place";
    final static String event = "event";


    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private EstablishmentAdapter establishmentAdapter;
    private List<Event> events;
    private View v;


    private String item;
    private String type;
    private String typeInfo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }


    public Retrofit retrofit;
    public Link service;

    public EstablishmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_event_tab, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.EventList);


        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (typeInfo == place) {
            establishmentAdapter = new EstablishmentAdapter(getActivity(), getCinemas());
            recyclerView.setAdapter(establishmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            setHasOptionsMenu(false);
        } else if (typeInfo == event)

        {
            setHasOptionsMenu(true);
            events = getEvents();
            eventAdapter = new EventAdapter(getActivity(), events);
            recyclerView.setAdapter(eventAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        }


    }


    public List<Event> getEvents() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEvents(db, item);

        return data;
    }


    public List<Establishment> getCinemas() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Establishment> data = ChudobiletDatabaseHelper.getEstablishments(db, item);
        return data;

    }


    @Override
    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {

    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {
        System.out.println("CallListMovie " + t.getLocalizedMessage());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Event> filteredModelList = filter(events, query);
        eventAdapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }


    private List<Event> filter(List<Event> models, String query) {
        query = query.toLowerCase();

        final List<Event> filteredModelList = new ArrayList<>();
        for (Event curEvent : models) {
            final String text = curEvent.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(curEvent);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.main:
                //newGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
