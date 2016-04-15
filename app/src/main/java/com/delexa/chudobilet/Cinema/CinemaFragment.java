package com.delexa.chudobilet.Cinema;


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
import com.delexa.chudobilet.TabFragment;

import java.util.ArrayList;
import java.util.List;


public class CinemaFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    CinemaTabAdapter adapter;


    public static int int_items = 3;

    public CinemaFragment() {
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
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.cinemaTabs);

        buildFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }


    private void buildFragments() {
        TabFragment movieFragment = new TabFragment();
        movieFragment.setText("Фильмы");
        movieFragment.setKategoria("Фильмы");

        TabFragment cinemaFragment = new TabFragment();
        cinemaFragment.setText("Кинотеатры");
        cinemaFragment.setKategoria("Кинотеатры");


        adapter = new CinemaTabAdapter(getChildFragmentManager());
        adapter.addFragment(cinemaFragment);
        adapter.addFragment(movieFragment);

    }


    class CinemaTabAdapter extends FragmentPagerAdapter {

        private List<TabFragment> fragments = new ArrayList<>();

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

            return fragments.get(position).getText();
        }

        public void addFragment(TabFragment fragment) {
            fragments.add(fragment);
        }
    }

}