package com.delexa.chudobilet.SubMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.delexa.chudobilet.DBClasses.Establishment;
import com.delexa.chudobilet.DBClasses.Event;
import com.delexa.chudobilet.DBClasses.SeatName;
import com.delexa.chudobilet.DBClasses.TicketOrder;
import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.DBHelpClasses.EventAdapter;
import com.delexa.chudobilet.DBHelpClasses.FavouriteSeatAdapter;
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
        Intent intent = getIntent();
        establishmentName = intent.getStringExtra("establishmentName");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.SeatsList);
        favouriteSeatAdapter = new FavouriteSeatAdapter(this, getSeatNames());
        recyclerView.setAdapter(favouriteSeatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<SeatName> getSeatNames() {

        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<SeatName> data = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);
        return data;

    }


}
