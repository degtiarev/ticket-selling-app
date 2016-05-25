package com.delexa.chudobilet.SubMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.delexa.chudobilet.DBClasses.Event;
import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.DBHelpClasses.EventAdapter;
import com.delexa.chudobilet.R;

import java.util.List;

public class EstablishmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_tab);

        ((AppCompatActivity) this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        int id = intent.getIntExtra("_id", Integer.MAX_VALUE);


        recyclerView = (RecyclerView) findViewById(R.id.EventList);

        eventAdapter = new EventAdapter(this, getEvents(id));
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public List<Event> getEvents(int id) {

        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<Event> data = ChudobiletDatabaseHelper.getEventsByEstablishment(db, id);

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
