package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.API.EstablishmentAPI;
import com.delexa.chudobilet.API.Link;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabFragment extends Fragment implements Callback<List<EstablishmentAPI>> {

    final static String place = "place";
    final static String event = "event";


    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;


    private String type1;
    private String type2;
    private String item;

    public Retrofit retrofit;
    public Link service;


    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public TabFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_event, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.eventViewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.eventTabs);

        buildFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return v;
    }


    private void buildFragments() {
        EstablishmentFragment concertFragment = new EstablishmentFragment();
        concertFragment.setType(type1);
        concertFragment.setItem(item);
        concertFragment.setTypeInfo(event);


        EstablishmentFragment concertPlaceFragment = new EstablishmentFragment();
        concertPlaceFragment.setType(type2);
        concertPlaceFragment.setItem(item);
        concertPlaceFragment.setTypeInfo(place);

        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(concertPlaceFragment);
        adapter.addFragment(concertFragment);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pastebin.com/raw/")
                .build();
        service = retrofit.create(Link.class);
        Call<List<EstablishmentAPI>> chudobilet = service.getEstablishmentAPI();
        chudobilet.enqueue(this);
    }

    //region Работа с API
    @Override
    public void onResponse(Call<List<EstablishmentAPI>> call, Response<List<EstablishmentAPI>> response) {
        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getActivity());
        SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
        ChudobiletDatabaseHelper.insertEstablishmentAPI(db, response);
    }

    @Override
    public void onFailure(Call<List<EstablishmentAPI>> call, Throwable t) {
        System.out.println("CallListMovie " + t.getLocalizedMessage());
    }
    //endregion


    class TabAdapter extends FragmentPagerAdapter {

        private List<EstablishmentFragment> fragments = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public int getCount() {
            return fragments.size();
        }

        public CharSequence getPageTitle(int position) {

            return fragments.get(position).getType();
        }

        public void addFragment(EstablishmentFragment fragment) {
            fragments.add(fragment);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(item);
    }
}
