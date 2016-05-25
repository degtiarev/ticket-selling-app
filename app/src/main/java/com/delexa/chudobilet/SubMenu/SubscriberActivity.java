package com.delexa.chudobilet.SubMenu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

public class SubscriberActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriber);



        viewPager = (ViewPager) findViewById(R.id.SubscriberViewpager);
        tabLayout = (TabLayout) findViewById(R.id.SubscriberTabs);

        buildFragments();

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }


    private void buildFragments() {
        SubscriptionInterestFragment subscriptionInterestFragment = new SubscriptionInterestFragment();


        EventSubscriptionFragment eventSubscriptionFragment = new EventSubscriptionFragment();


        adapter = new TabAdapter(getSupportFragmentManager());
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
