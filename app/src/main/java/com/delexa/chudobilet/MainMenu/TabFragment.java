package com.delexa.chudobilet.MainMenu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {

    final static String place = "place";
    final static String event = "event";


    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;


    private String type1;
    private String type2;
    private String item;

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


    //   public static int int_items = 3;

    public TabFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // private FragmentTabHost tabHost;

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

}
