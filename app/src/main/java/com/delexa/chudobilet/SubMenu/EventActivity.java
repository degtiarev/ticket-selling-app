package com.delexa.chudobilet.SubMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        ((AppCompatActivity) this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final int _id = intent.getIntExtra("_id", Integer.MAX_VALUE);


        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        int amountFreeSeats = ChudobiletDatabaseHelper.getAmountOfFreeSeats(db, _id);
        db = chudobiletDatabaseHelper.getReadableDatabase();
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
        ImageView link = (ImageView) findViewById(R.id.imageViewEventLink);
        Button button = (Button) findViewById(R.id.Subscribe);

        if (event.getVideoLink() != null) {
            videoLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(event.getVideoLink())));
                }
            });
        } else videoLink.setVisibility(View.GONE);

        if (event.getLink() != null) {
            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getLink()));
                    startActivity(browserIntent);
                }
            });
        } else link.setVisibility(View.GONE);


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


        if (amountFreeSeats == 0) {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

                    alert.setTitle("Сколько мест необходимо?");
                    alert.setMessage("Введите количество мест");

                    // Set an EditText view to get user input
                    final EditText input = new EditText(v.getContext());
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(EventActivity.this);
                            SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
                            ChudobiletDatabaseHelper.InsertSubscriptionbyEventid(db, _id, Integer.parseInt(input.getText().toString()));

                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });

                    alert.show();
                }

            });
        } else {
            button.setVisibility(View.GONE);

        }


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
