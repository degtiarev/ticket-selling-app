package com.delexa.chudobilet;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChudobiletDatabaseHelper extends SQLiteOpenHelper {

    private static ChudobiletDatabaseHelper sInstance;

    private static final String DB_NAME = "chudobilet"; // Имя базы данных
    private static final int DB_VERSION = 1; // Версия базы данных


    public static synchronized ChudobiletDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new ChudobiletDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;

    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */

    private ChudobiletDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            createAllTables(db);
            byte[] myPhoto = getBlobFromURL("http://new.chudobilet.ru/media/images/events/856a1b1073d9133a33a35ff006de816a.jpg");
            byte[] cover1 = getBlobFromURL("http://new.chudobilet.ru/media/images/events/c05f81e44660d05b3c20bcf76cc76973.jpg");
            byte[] cover2 = getBlobFromURL("http://new.chudobilet.ru/media/images/events/dd0d82740c770242d151223691a2b2df.jpg");


            insertUser(db, new User("Алексей", "Дегтярев", "Сергеевич", "delexa0@gmail.com", getDateTime("1993-02-04"),
                    "M", "11", "0", "11", "89608519623", "123456789", myPhoto, "фэнтези", null, null, new Date()));

            insertEstablishment(db, new Establishment("Кино", "Большое Кино", "3, ТРК Alimpic, ул. Боевая, 25, Астрахань, Астраханская обл., 414024",
                    new Date()));

            insertEvent(db, new Event(new Establishment("Кино", "Большое Кино", "3, ТРК Alimpic, ул. Боевая, 25, Астрахань, Астраханская обл., 414024",
                    new Date()), "Книга джунглей", "США", "фэнтези, драма, приключения, семейный, ...", " 1 час 50 минут", 6,
                    "Скарлетт Йоханссон, Идрис Эльба, Билл Мюррей, Лупита Нионго, Кристофер Уокен, Джанкарло Эспозито, Нил Сетхи, Бен Кингсли, Ралф Айнесон, " +
                            "Ханна Тойнтон, ...", "Непримиримая борьба с опасным и внушающим страх тигром Шерханом вынуждает Маугли покинуть волчью стаю и отправиться в " +
                    "захватывающее путешествие. На пути мальчика ждут удивительные открытия и запоминающиеся встречи с пантерой Багирой, медведем Балу, питоном Каа и " +
                    "другими обитателями дремучих джунглей.", cover1, "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/992/", new Date()));

            insertEvent(db, new Event(new Establishment("Кино", "Большое Кино", "3, ТРК Alimpic, ул. Боевая, 25, Астрахань, Астраханская обл., 414024",
                    new Date()), "Белоснежка и Охотник 2", "США", "фэнтези, драма, приключения, семейный, ...", " 1 час 50 минут", 6,
                    "Скарлетт Йоханссон, Идрис Эльба, Билл Мюррей, Лупита Нионго, Кристофер Уокен, Джанкарло Эспозито, Нил Сетхи, Бен Кингсли, Ралф Айнесон, " +
                            "Ханна Тойнтон, ...", "Непримиримая борьба с опасным и внушающим страх тигром Шерханом вынуждает Маугли покинуть волчью стаю и отправиться в " +
                    "захватывающее путешествие. На пути мальчика ждут удивительные открытия и запоминающиеся встречи с пантерой Багирой, медведем Балу, питоном Каа и " +
                    "другими обитателями дремучих джунглей.", cover2, "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/1008/", new Date()));


        }

        if (oldVersion < 2) {


        }
    }


    private static void createAllTables(SQLiteDatabase db) {

        // таблица USER
        db.execSQL("CREATE TABLE USER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "FAMILY TEXT, " +
                "PATRONYMIC TEXT, " +
                "EMAIL TEXT, " +
                "DATE NUMERIC, " +
                "SEX TEXT, " +
                "NOTIFICATIONTOPAY TEXT, " +
                "EMAILNOTIFICATIONCHANGESTATUS TEXT, " +
                "NEWSSUBSCRIBER TEXT, " +
                "PHONE TEXT, " +
                "PASSWORD TEXT, " +
                "PHOTO BLOB, " +
                "INTERESTGENRE TEXT, " +
                "INTERESTROLES TEXT, " +
                "INTERESTESTABLISHMENT TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица ORDER
        db.execSQL("CREATE TABLE TICKETORDER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_USERID INTEGER, " +
                "_ESTABLISHMENTID INTEGER, " +
                "PURCHASEDATE NUMERIC, " +
                "STATUS TEXT, " +
                "SERIES TEXT, " +
                "NUMBER TEXT, " +
                "CODE TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица ESTABLISHMENT
        db.execSQL("CREATE TABLE ESTABLISHMENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TYPE TEXT, " +
                "NAME TEXT, " +
                "ADDRESS TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица EVENT
        db.execSQL("CREATE TABLE EVENT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_ESTABLISHMENTID INTEGER, " +
                "NAME TEXT" +
                "COUNTRY TEXT, " +
                "GENRE TEXT, " +
                "AMOUNTTIME TEXT, " +
                "FORAGE TEXT, " +
                "ROLES TEXT, " +
                "ABOUT TEXT, " +
                "COVER BLOB, " +
                "VIDEOLINK TEXT," +
                "TIMESTAMP NUMERIC);");

        // таблица SEAT
        db.execSQL("CREATE TABLE SEAT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "TIMEDATE NUMERIC, " +
                "PRICE REAL, " +
                "NAME TEXT, " +
                "SERVICEPRICE REAL, " +
                "TIMESTAMP NUMERIC);");

        // таблица SUBSCRIPTION
        db.execSQL("CREATE TABLE SUBSCRIPTION (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "AMOUNTSEATS INTEGER, " +
                "TIMESTAMP NUMERIC);");
    }

    private static void insertUser(SQLiteDatabase db, User user) {
        ContentValues userValues = new ContentValues();

        userValues.put("NAME", user.getName());
        userValues.put("FAMILY", user.getFamily());
        userValues.put("PATRONYMIC", user.getPatronymic());
        userValues.put("EMAIL", user.getEmail());
        userValues.put("DATE", getDateTime(user.getDate()));
        userValues.put("SEX", user.getSex());
        userValues.put("NOTIFICATIONTOPAY", user.getNotificationToPay());
        userValues.put("EMAILNOTIFICATIONCHANGESTATUS", user.getEmailNotificationChangeStatus());
        userValues.put("NEWSSUBSCRIBER", user.getNewsSubscriber());
        userValues.put("PHONE", user.getPhone());
        userValues.put("PASSWORD", user.getPassword());
        userValues.put("PHOTO", user.getImage());
        userValues.put("INTERESTGENRE", user.getInterestGenre());
        userValues.put("INTERESTROLES", user.getInterestRoles());
        userValues.put("INTERESTESTABLISHMENT", user.getInterestEstablishment());
        userValues.put("TIMESTAMP", getDateTime(user.getTimeStamp()));

        db.insert("USER", null, userValues);
    }

    private static void insertEstablishment(SQLiteDatabase db, Establishment establishment) {
        ContentValues establishmentValues = new ContentValues();

        establishmentValues.put("TYPE", establishment.getType());
        establishmentValues.put("NAME", establishment.getName());
        establishmentValues.put("ADDRESS", establishment.getAddress());
        establishmentValues.put("TIMESTAMP", getDateTime(establishment.getTimeStamp()));

        db.insert("ESTABLISHMENT", null, establishmentValues);
    }

    private static void insertEvent(SQLiteDatabase db, Event event) {
        ContentValues userValues = new ContentValues();
        int establishmentId = 9999999;

        try {

            Cursor newCursor = db.query("ESTABLISHMENT",
                    new String[]{"_id"},
                    "TYPE = ? AND NAME = ?",
                    new String[]{event.getEstablishment().getType(), event.getEstablishment().getName()},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    establishmentId = newCursor.getInt(0);
                }

                ContentValues eventValues = new ContentValues();

                userValues.put("_ESTABLISHMENTID", establishmentId);
                userValues.put("COUNTRY", event.getCountry());
                userValues.put("GENRE", event.getGenre());
                userValues.put("AMOUNTTIME", event.getAmountTime());
                userValues.put("FORAGE", event.getForAge());
                userValues.put("ROLES", event.getRoles());
                userValues.put("ABOUT", event.getAbout());
                userValues.put("COVER", event.getCover());
                userValues.put("VIDEOLINK", event.getVideoLink());
                userValues.put("TIMESTAMP", getDateTime(event.getTimeStamp()));

                db.insert("ESTABLISHMENT", null, eventValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }


    }


    private static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                //"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                "yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

    private static Date getDateTime(String myDate) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public byte[] getBlobFromURL(String src) {
//        try {
//            java.net.URL url = new java.net.URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//
//
//            return byteArray;
//        } catch (IOException e) {
//            e.printStackTrace();
        return null;
//        }

    }

}
