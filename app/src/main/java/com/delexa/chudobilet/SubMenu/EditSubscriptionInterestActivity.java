package com.delexa.chudobilet.SubMenu;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.FavouriteSeatAdapter;
import com.delexa.chudobilet.MainClasses.Establishment;
import com.delexa.chudobilet.MainClasses.SeatName;
import com.delexa.chudobilet.R;

import java.util.Collections;
import java.util.List;

public class EditSubscriptionInterestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavouriteSeatAdapter favouriteSeatAdapter;
    private String establishmentName;

    List<Establishment> data = Collections.emptyList();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription_interest);
        ((AppCompatActivity) this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        establishmentName = intent.getStringExtra("establishmentName");

        setTitle(establishmentName);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<SeatName> seatNames = getSeatNames();
                seatNames.add(new SeatName("1", "1", "1"));
                String s = "";

                for (SeatName seatName : seatNames) {

                    s += seatName.getSector() + " ";
                    s += seatName.getRow() + " ";
                    s += seatName.getSeat() + ", ";
                }

                s = s.substring(0, s.length() - 1);

                SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getParent());
                SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
                ContentValues newValues = new ContentValues();
                newValues.put("FAVOURITESEATS", s);

                db.update("ESTABLISHMENT", newValues, "NAME = '" + establishmentName + "'", null);
                db.close();

                favouriteSeatAdapter = new FavouriteSeatAdapter(getParent(), getSeatNames(), establishmentName);
                recyclerView.setAdapter(favouriteSeatAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));

            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.SeatsList);
        favouriteSeatAdapter = new FavouriteSeatAdapter(this, getSeatNames(), establishmentName);
        recyclerView.setAdapter(favouriteSeatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






    }

    public List<SeatName> getSeatNames() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getParent());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<SeatName> data = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);
        return data;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
