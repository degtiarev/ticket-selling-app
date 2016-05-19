package com.delexa.chudobilet.SubMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.DBClasses.Event;
import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;
import com.squareup.picasso.Picasso;


public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);



        Intent intent = getIntent();
        int _id = intent.getIntExtra("_id", Integer.MAX_VALUE);


        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        final Event event = ChudobiletDatabaseHelper.getEvent(db, _id);

        setTitle(event.getName());

        ImageView cover = (ImageView) findViewById(R.id.imageViewEventCover);
        TextView name = (TextView) findViewById(R.id.textViewEventName);
        TextView genre = (TextView) findViewById(R.id.textViewEventGenre);
        TextView yearCountryAmountTimeForAge = (TextView) findViewById(R.id.textViewEventYearCountryAmountTimeForAge);
        TextView roles = (TextView) findViewById(R.id.textViewEventsRoles);
        TextView rolesConst = (TextView) findViewById(R.id.textViewEventsRolesConst);
        TextView about = (TextView) findViewById(R.id.textViewEventAbout);
        ImageView videoLink = (ImageView) findViewById(R.id.imageViewEventVideoLink);
        videoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(event.getVideoLink())));
            }
        });
        ImageView link = (ImageView) findViewById(R.id.imageViewEventLink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getLink()));
                startActivity(browserIntent);
            }
        });

        Picasso.with(this) //передаем контекст приложения
                .load(event.getCover())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.ic_menu_camera)
                .into(cover); //ссылка на ImageView


        setTextView(event.getName(), name, null);
        setTextView(event.getGenre(), genre, null);
        setTextView(event.getRoles(), roles, rolesConst);
        setTextView(event.getAbout(), about, null);

        String sYearCountryAmountTimeForAge = "";
        if (event.getYear() != 0) sYearCountryAmountTimeForAge += event.getYear() + ", ";
        if (event.getCountry() != null) sYearCountryAmountTimeForAge += event.getCountry() + ", ";
        if (event.getAmountTime() != null)
            sYearCountryAmountTimeForAge += event.getAmountTime() + ", ";
        if (event.getForAge() != null) sYearCountryAmountTimeForAge += event.getForAge();

        setTextView(sYearCountryAmountTimeForAge, yearCountryAmountTimeForAge, null);

    }


    private void setTextView(String text, TextView textView, TextView dependTextView) {
        if (text != null)
            textView.setText(text);
        else {
            textView.setVisibility(View.GONE);
            if (dependTextView != null)
                dependTextView.setVisibility(View.GONE);
        }

    }



}
