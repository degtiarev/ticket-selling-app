package com.delexa.chudobilet.MainMenu;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

public class SubscriberFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;


    //   public static int int_items = 3;

    public SubscriberFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.fragment_subscriber, container, false);


        viewPager = (ViewPager) v.findViewById(R.id.SubscriberViewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.SubscriberTabs);

        buildFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;

    }


    private void buildFragments() {
        SubscriptionInterestFragment subscriptionInterestFragment = new SubscriptionInterestFragment();


        EventSubscriptionFragment eventSubscriptionFragment = new EventSubscriptionFragment();


        adapter = new TabAdapter(getChildFragmentManager());
        adapter.addFragment(subscriptionInterestFragment);
        adapter.addFragment(eventSubscriptionFragment);

    }

    class TabAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();

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

            if (position == 0) {

                return "По интересам";
            } else {

                return "По событиям";

            }

        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }
    }


}
