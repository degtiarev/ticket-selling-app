package com.delexa.chudobilet.SubMenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

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

                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("Какое место хотите добавить?");
                alert.setMessage("Введите расположение необходимого места");

                Context context = view.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText sector = new EditText(context);
                sector.setHint("Сектор");
                sector.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(sector);
                final EditText row = new EditText(context);
                row.setHint("Ряд");
                row.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(row);
                final EditText seat = new EditText(context);
                seat.setHint("Место");
                seat.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(seat);

                alert.setView(layout);


                alert.setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        List<SeatName> seatNames = getSeatNames();
                        seatNames.add(new SeatName(sector.getText().toString(), row.getText().toString(), seat.getText().toString()));
                        String s = "";

                        for (SeatName seatName : seatNames) {

                            s += seatName.getSector() + " ";
                            s += seatName.getRow() + " ";
                            s += seatName.getSeat() + ", ";
                        }
                        //    s = s.substring(0, s.length() - 1);

                        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getParent());
                        SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
                        ChudobiletDatabaseHelper.changeFavouriteSeats(db, establishmentName, s);

                        favouriteSeatAdapter = new FavouriteSeatAdapter(getParent(), getSeatNames(), establishmentName);
                        recyclerView.setAdapter(favouriteSeatAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getParent()));


                    }
                });

                alert.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();


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
