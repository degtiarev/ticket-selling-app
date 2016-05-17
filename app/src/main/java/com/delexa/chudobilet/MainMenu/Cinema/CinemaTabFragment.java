package com.delexa.chudobilet.MainMenu.Cinema;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;


public class CinemaTabFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    CinemaTabAdapter adapter;


    public static int int_items = 3;

    public CinemaTabFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private FragmentTabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_cinema, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.cinemaViewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.cinemaTabs);

        buildFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }


    private void buildFragments() {
        CinemaFragment movieFragment = new CinemaFragment();
        movieFragment.setType("Фильмы");

        CinemaFragment cinemaFragment = new CinemaFragment();
        cinemaFragment.setType("Кинотеатры");


        adapter = new CinemaTabAdapter(getChildFragmentManager());
        adapter.addFragment(cinemaFragment);
        adapter.addFragment(movieFragment);

    }


    class CinemaTabAdapter extends FragmentPagerAdapter {

        private List<CinemaFragment> fragments = new ArrayList<>();

        public CinemaTabAdapter(FragmentManager fm) {
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

        public void addFragment(CinemaFragment fragment) {
            fragments.add(fragment);
        }
    }

}