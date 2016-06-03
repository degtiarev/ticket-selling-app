package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.API.EstablishmmentAPIUpdater;
import com.delexa.chudobilet.API.EventAPIUpdater;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.EstablishmentAdapter;
import com.delexa.chudobilet.Adapters.EventAdapter;
import com.delexa.chudobilet.MainClasses.Establishment;
import com.delexa.chudobilet.MainClasses.Event;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

// Работа с API
public class EventTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    final static String place = "place";
    final static String event = "event";

    public final static String today = "today";
    public final static String tomorrow = "tomorow";
    public final static String onWeek = "onWeek";
    public final static String onNextWeek = "onNextWeek";
    public final static String onMonth = "Month";
    public final static String onNextMonth = "onNextMonth";
    public final static String then = "then";

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    private EstablishmentAdapter establishmentAdapter;
    private List<Event> events;
    private View v;


    private SwipeRefreshLayout mSwipeRefreshLayout;


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


    public EventTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_event_tab, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.EventList);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refreshTabFragment);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (typeInfo == place) {
            setHasOptionsMenu(false);
            establishmentAdapter = new EstablishmentAdapter(getActivity(), getCinemas());
            recyclerView.setAdapter(establishmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            EstablishmmentAPIUpdater establishmmentAPIUpdater = new EstablishmmentAPIUpdater(getContext());
            establishmmentAPIUpdater.update();

        } else if (typeInfo == event)

        {
            setHasOptionsMenu(true);
            events = getEvents();
            eventAdapter = new EventAdapter(getActivity(), events);
            recyclerView.setAdapter(eventAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            EventAPIUpdater eventAPIUpdater = new EventAPIUpdater(getContext());
            eventAPIUpdater.update();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        setHasOptionsMenu(false);
    }

    @Override
    public void onRefresh() {
        // говорим о том, что собираемся начать
//        Toast.makeText(getContext(), "Начинаем", Toast.LENGTH_SHORT).show();
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить

//                Toast.makeText(getContext(), "заканчиваем", Toast.LENGTH_SHORT).show();
            }
        }, 3000);

        if (typeInfo == place) {
            setHasOptionsMenu(false);
            establishmentAdapter = new EstablishmentAdapter(getActivity(), getCinemas());
            recyclerView.setAdapter(establishmentAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            EstablishmmentAPIUpdater establishmmentAPIUpdater = new EstablishmmentAPIUpdater(getContext());
            establishmmentAPIUpdater.update();


        } else if (typeInfo == event)

        {
            setHasOptionsMenu(true);
            events = getEvents();
            eventAdapter = new EventAdapter(getActivity(), events);
            recyclerView.setAdapter(eventAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            EventAPIUpdater eventAPIUpdater = new EventAPIUpdater(getContext());
            eventAPIUpdater.update();

        }

    }

    //region  Получение данных из БД
    public List<Event> getEvents() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEvents(db, item);

        return data;
    }

    public List<Event> getEventsByDate(String date) {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEventsByDate(db, item, date);

        return data;
    }

    public List<Establishment> getCinemas() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Establishment> data = ChudobiletDatabaseHelper.getEstablishments(db, item);
        return data;

    }
//endregion

    // region Работа поиском
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

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
    // endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.today:
                events = getEventsByDate(today);
                break;

            case R.id.tomorrow:
                events = getEventsByDate(tomorrow);
                break;

            case R.id.onWeek:
                events = getEventsByDate(onWeek);
                break;

            case R.id.onNextWeek:
                events = getEventsByDate(onNextWeek);
                break;

            case R.id.onMonth:
                events = getEventsByDate(onMonth);
                break;

            case R.id.onNextMonth:
                events = getEventsByDate(onNextMonth);
                break;

            case R.id.then:
                events = getEventsByDate(then);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        if (events != null) {
            eventAdapter = new EventAdapter(getActivity(), events);
            recyclerView.setAdapter(eventAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        return true;
    }


}
