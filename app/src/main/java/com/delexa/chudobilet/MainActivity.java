package com.delexa.chudobilet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.delexa.chudobilet.About.AboutFragment;
import com.delexa.chudobilet.Authorization.AuthorizationFragment;
import com.delexa.chudobilet.Cinema.CinemaFragment;
import com.delexa.chudobilet.Concert.ConcertsFragment;
import com.delexa.chudobilet.ForChildren.ForChildrenFragment;
import com.delexa.chudobilet.MasterClass.MasterclassFragment;
import com.delexa.chudobilet.MyOrders.MyOrdersFragment;
import com.delexa.chudobilet.Other.OtherFragment;
import com.delexa.chudobilet.Settings.SettingsFragment;
import com.delexa.chudobilet.Theater.TheatersFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // клик по картинки настроек
        View hView = navigationView.getHeaderView(0);
        ImageView imgSettings = (ImageView) hView.findViewById(R.id.imageViewOptions);
        imgSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();

                getSupportActionBar().setTitle("Settings");

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        ImageView imgProfile = (ImageView) hView.findViewById(R.id.imageViewPhoto);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AuthorizationFragment();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();

                getSupportActionBar().setTitle("Authorization");

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


    }

    // обработка нажатия на кнопку назад
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // выбор из action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // выбор из бокового меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        String title = getString(R.string.app_name);


        switch (id) {
            case R.id.cinema:
                fragment = new CinemaFragment();
                title = getString(R.string.cinema);
                break;

            case R.id.concerts:
                fragment = new ConcertsFragment();
                title = getString(R.string.concerts);
                break;

            case R.id.theaters:
                fragment = new TheatersFragment();
                title = getString(R.string.theaters);
                break;

            case R.id.for_children:
                fragment = new ForChildrenFragment();
                title = getString(R.string.for_children);
                break;

            case R.id.other:
                fragment = new OtherFragment();
                title = getString(R.string.other);
                break;

            case R.id.masterclass:
                fragment = new MasterclassFragment();
                title = getString(R.string.masterclass);
                break;

            case R.id.my_orders:
                fragment = new MyOrdersFragment();
                title = getString(R.string.my_orders);
                break;

            case R.id.other_apps:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://search?q=pub:CHUDOBILET"));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, "Не найдено приложение GooglePlay", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.about:
                fragment = new AboutFragment();
                title = getString(R.string.about);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
