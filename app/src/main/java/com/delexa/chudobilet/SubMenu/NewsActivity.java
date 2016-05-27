package com.delexa.chudobilet.SubMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.News;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ((AppCompatActivity)this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent = getIntent();
        final int _id = intent.getIntExtra("_id", Integer.MAX_VALUE);


        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(this);
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        News news = ChudobiletDatabaseHelper.getNews(db, _id);


        ImageView cover = (ImageView) findViewById(R.id.imageViewNewsCover);
        TextView name = (TextView) findViewById(R.id.textViewNewsName);
        TextView date = (TextView) findViewById(R.id.textViewNewsDate);
        TextView about = (TextView) findViewById(R.id.textViewNewsAbout);

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(news.getDate());

        setTitle(news.getName());
        name.setText(news.getName());
        date.setText(s);
        about.setText(news.getAbout());

        Picasso.with(this) //передаем контекст приложения
                .load(news.getCover())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.ic_menu_camera)
                .into(cover); //ссылка на ImageView

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
