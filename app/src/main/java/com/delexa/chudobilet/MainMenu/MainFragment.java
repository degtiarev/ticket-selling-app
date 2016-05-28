package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.SmallCardAdapter;
import com.delexa.chudobilet.MainClasses.Event;
import com.delexa.chudobilet.R;

import java.util.List;

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

        getActivity().setTitle("Главная");

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

        recyclerView = (RecyclerView) v.findViewById(R.id.MasterclassList);
        LinearLayoutManager horizontalLayoutManagaer6 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Мастер-класс"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer6);

        recyclerView = (RecyclerView) v.findViewById(R.id.OtherList);
        LinearLayoutManager horizontalLayoutManagaer7 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        smallCardAdapter = new SmallCardAdapter(getActivity(), getEvents("Другое"));
        recyclerView.setAdapter(smallCardAdapter);
        recyclerView.setLayoutManager(horizontalLayoutManagaer7);


        TextView recomend = (TextView) v.findViewById(R.id.TextViewRecomend);
        TextView cinema = (TextView) v.findViewById(R.id.TextViewCinema);
        TextView concert = (TextView) v.findViewById(R.id.TextViewConcert);
        TextView theater = (TextView) v.findViewById(R.id.TextViewTheater);
        TextView forChildren = (TextView) v.findViewById(R.id.TextViewForChildren);
        TextView masterClass = (TextView) v.findViewById(R.id.TextViewMasterClass);
        TextView other = (TextView) v.findViewById(R.id.TextViewOther);

        recomend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new SubscriberFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("Фильмы");
                fragment.setType2("Кинотеатры");
                fragment.setItem("Кино");
                getActivity().setTitle("Кино");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        concert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("События");
                fragment.setType2("Места");
                fragment.setItem("Концерты");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        theater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("Репертуар");
                fragment.setType2("Места");
                fragment.setItem("Театры");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        forChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("События");
                fragment.setType2("Места");
                fragment.setItem("Детям");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        masterClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("События");
                fragment.setType2("Места");
                fragment.setItem("Мастерклассы");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabFragment fragment = new TabFragment();
                fragment.setType1("События");
                fragment.setType2("Места");
                fragment.setItem("Другое");

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });


        return v;
    }



    public List<Event> getEvents(String type) {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEvents(db, type);

        return data;
    }

}
