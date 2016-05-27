package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.SmallCardAdapter;
import com.delexa.chudobilet.MainClasses.Event;
import com.delexa.chudobilet.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private RecyclerView recyclerView;

    private SmallCardAdapter smallCardAdapter;


    public MainFragment() {
        // Inflate the layout for this fragment

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_main, container, false);


        recyclerView = (RecyclerView) v.findViewById(R.id.RecomendedList);
        LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Кино"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer1);

        recyclerView = (RecyclerView) v.findViewById(R.id.CinemaList);
        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Кино"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer2);

        recyclerView = (RecyclerView) v.findViewById(R.id.ConcertsList);
        LinearLayoutManager horizontalLayoutManagaer3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Концерты"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer3);

        recyclerView = (RecyclerView) v.findViewById(R.id.TheaterList);
        LinearLayoutManager horizontalLayoutManagaer4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Театры"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer4);

        recyclerView = (RecyclerView) v.findViewById(R.id.ForChildrenList);
        LinearLayoutManager horizontalLayoutManagaer5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Детям"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer5);

        recyclerView = (RecyclerView) v.findViewById(R.id.ForChildrenList);
        LinearLayoutManager horizontalLayoutManagaer6 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Мастер-класс"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer6);

        recyclerView = (RecyclerView) v.findViewById(R.id.OtherList);
        LinearLayoutManager horizontalLayoutManagaer7 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Другое"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer7);


        return v;
    }

    public List<Event> getEvents(String type) {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEvents(db, type);

        return data;
    }

}
