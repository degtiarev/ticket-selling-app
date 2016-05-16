package com.delexa.chudobilet.DBHelpClasses;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.delexa.chudobilet.DBClasses.Establishment;
import com.delexa.chudobilet.DBClasses.Event;
import com.delexa.chudobilet.DBClasses.Seat;
import com.delexa.chudobilet.DBClasses.Subscription;
import com.delexa.chudobilet.DBClasses.TicketOrder;
import com.delexa.chudobilet.DBClasses.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChudobiletDatabaseHelper extends SQLiteOpenHelper {

    private static ChudobiletDatabaseHelper sInstance;

    private static final String SHORT_DATE = "yyyy-MM-dd";
    private static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";

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

    public ChudobiletDatabaseHelper(Context context) {
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

            User user = new User("Алексей", "Дегтярев", "Сергеевич", "delexa0@gmail.com", getDateTime("1993-02-04", SHORT_DATE),
                    "M", "11", "0", "11", "89608519623", "123456789", myPhoto, "фэнтези", null, null, new Date());
            Establishment establishment = new Establishment("Кино", "Большое Кино", "3, ТРК Alimpic, ул. Боевая, 25, " +
                    "Астрахань, Астраханская обл., 414024", new Date());
            Event event1 = new Event("Книга джунглей", establishment, "США", "фэнтези, драма, приключения, семейный, ...", 2016, " 1 час 50 минут", "6+",
                    "Скарлетт Йоханссон, Идрис Эльба, Билл Мюррей, Лупита Нионго, Кристофер Уокен, Джанкарло Эспозито, Нил Сетхи, Бен Кингсли, Ралф Айнесон, " +
                            "Ханна Тойнтон, ...", "Непримиримая борьба с опасным и внушающим страх тигром Шерханом вынуждает Маугли покинуть волчью стаю и отправиться в " +
                    "захватывающее путешествие. На пути мальчика ждут удивительные открытия и запоминающиеся встречи с пантерой Багирой, медведем Балу, питоном Каа и " +
                    "другими обитателями дремучих джунглей.", cover1, "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/992/", new Date());
            Event event2 = new Event("Белоснежка и Охотник 2", establishment, "США", "фэнтези, боевик, драма, приключения, ...", 2016, "1 час 55 минут", "6+",
                    "Крис Хемсворт, Сэм Клафлин, Эмили Блант, Джессика Честейн, Шарлиз Терон, Софи Куксон, Ник Фрост, Колин Морган, Ралф Айнесон, Шеридан Смит, ...",
                    "Когда любовь уходит, сердце прекрасной девы обращается в лед. И даже сотни королевств не смогут сдержать поступь ее несметного воинства. Лишь Охотник не ведает страха. Сквозь проклятый лес он идет навстречу своей судьбе.", cover2, "https://www.youtube.com/watch?v=TwNXOE2yxPU",
                    "http://new.chudobilet.ru/event/1008/", new Date());

            insertUser(db, user);
            insertEstablishment(db, establishment);
            insertEvent(db, event1);
            insertEvent(db, event2);

            // дальше не работает
            TicketOrder ticketOrder1 = new TicketOrder();
            TicketOrder ticketOrder2 = new TicketOrder();


            for (int i = 1; i < 21; i++) {
                Seat seat = new Seat(event1, "А" + i, getDateTime("2016-07-01 12:00:00", LONG_DATE), 400, 20, new Date());
                insertSeat(db, seat);

                if (i == 1) {
                    ticketOrder1 = new TicketOrder(user, seat, getDateTime("2016-06-01 13:00:00", LONG_DATE), "неоплачено", "RS", "001468", "123456789012", new Date());
                    insertTicketOrder(db, ticketOrder1);
                }
            }

            for (int i = 1; i < 21; i++) {
                Seat seat = new Seat(event2, "А" + i, getDateTime("2016-07-02 12:00:00", LONG_DATE), 400, 20, new Date());
                insertSeat(db, seat);

                if (i == 1) {
                    ticketOrder2 = new TicketOrder(user, seat, getDateTime("2016-06-01 13:48:00", LONG_DATE), "оплачено", "RS", "001468", "123456789012", new Date());
                    insertTicketOrder(db, ticketOrder2);
                }
            }

            Subscription subscription1 = new Subscription(event1, 0, new Date());
            insertSubscription(db, subscription1);

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
                "NAME TEXT, " +
                "COUNTRY TEXT, " +
                "GENRE TEXT, " +
                "YEAR INTEGER, " +
                "AMOUNTTIME TEXT, " +
                "FORAGE TEXT, " +
                "ROLES TEXT, " +
                "ABOUT TEXT, " +
                "COVER BLOB, " +
                "VIDEOLINK TEXT," +
                "LINK TEXT, " +
                "ISNOTIFIED INTEGER, " +
                "TIMESTAMP NUMERIC);");

        // таблица SEAT
        db.execSQL("CREATE TABLE SEAT (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "NAME TEXT, " +
                "TIMEDATE NUMERIC, " +
                "PRICE REAL, " +
                "SERVICEPRICE REAL," +
                "ISFREE INTEGER, " +
                "TIMESTAMP NUMERIC);");

        // таблица TICKETORDER
        db.execSQL("CREATE TABLE TICKETORDER (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_USERID INTEGER, " +
                "_SEATID INTEGER, " +
                "PURCHASEDATE NUMERIC, " +
                "STATUS TEXT, " +
                "SERIES TEXT, " +
                "NUMBER TEXT, " +
                "CODE TEXT, " +
                "TIMESTAMP NUMERIC);");

        // таблица SUBSCRIPTION
        db.execSQL("CREATE TABLE SUBSCRIPTION (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_EVENTID INTEGER, " +
                "AMOUNTSEATS INTEGER, " +
                "ISNOTIFIED INTEGER, " +
                "TIMESTAMP NUMERIC);");
    }


    private static void insertUser(SQLiteDatabase db, User user) {
        ContentValues userValues = new ContentValues();

        userValues.put("NAME", user.getName());
        userValues.put("FAMILY", user.getFamily());
        userValues.put("PATRONYMIC", user.getPatronymic());
        userValues.put("EMAIL", user.getEmail());
        userValues.put("DATE", getDateTime(user.getDate(), SHORT_DATE));
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
        userValues.put("TIMESTAMP", getDateTime(user.getTimeStamp(), LONG_DATE));

        db.insert("USER", null, userValues);
    }

    private static void insertEstablishment(SQLiteDatabase db, Establishment establishment) {
        ContentValues establishmentValues = new ContentValues();

        establishmentValues.put("TYPE", establishment.getType());
        establishmentValues.put("NAME", establishment.getName());
        establishmentValues.put("ADDRESS", establishment.getAddress());
        establishmentValues.put("TIMESTAMP", getDateTime(establishment.getTimeStamp(), LONG_DATE));

        db.insert("ESTABLISHMENT", null, establishmentValues);
    }

    private static void insertEvent(SQLiteDatabase db, Event event) {

        int establishmentId = Integer.MAX_VALUE;

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

                eventValues.put("_ESTABLISHMENTID", establishmentId);
                eventValues.put("NAME", event.getName());
                eventValues.put("COUNTRY", event.getCountry());
                eventValues.put("GENRE", event.getGenre());
                eventValues.put("YEAR", event.getYear());
                eventValues.put("AMOUNTTIME", event.getAmountTime());
                eventValues.put("FORAGE", event.getForAge());
                eventValues.put("ROLES", event.getRoles());
                eventValues.put("ABOUT", event.getAbout());
                eventValues.put("COVER", event.getCover());
                eventValues.put("VIDEOLINK", event.getVideoLink());
                eventValues.put("LINK", event.getLink());
                eventValues.put("ISNOTIFIED", event.getIsNotified());
                eventValues.put("TIMESTAMP", getDateTime(event.getTimeStamp(), LONG_DATE));

                db.insert("EVENT", null, eventValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertSeat(SQLiteDatabase db, Seat seat) {

        int eventId = Integer.MAX_VALUE;

        try {


            Cursor newCursor = db.query("EVENT",
                    new String[]{"_id"},
                    "NAME = ? AND COUNTRY = ? AND GENRE = ? AND AMOUNTTIME = ? AND YEAR = ?",
                    new String[]{seat.getEvent().getName(), seat.getEvent().getCountry(),
                            seat.getEvent().getGenre(), seat.getEvent().getAmountTime(),
                            Integer.toString(seat.getEvent().getYear())},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    eventId = newCursor.getInt(0);
                }

                ContentValues seatValues = new ContentValues();

                seatValues.put("_EVENTID", eventId);
                seatValues.put("NAME", seat.getName());
                seatValues.put("TIMEDATE", getDateTime(seat.getTimeDate(), LONG_DATE));
                seatValues.put("PRICE", seat.getPrice());
                seatValues.put("SERVICEPRICE", seat.getServicePrice());
                seatValues.put("ISFREE", seat.getIsFree());
                seatValues.put("TIMESTAMP", getDateTime(seat.getTimeStamp(), LONG_DATE));

                db.insert("SEAT", null, seatValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertTicketOrder(SQLiteDatabase db, TicketOrder ticketOrder) {

        int seatId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("SEAT",
                    new String[]{"_id"},
                    "NAME = ? AND TIMEDATE = ? AND PRICE = ? AND SERVICEPRICE = ?",
                    new String[]{ticketOrder.getSeat().getName(), getDateTime(ticketOrder.getSeat().getTimeDate(), LONG_DATE),
                            Double.toString(ticketOrder.getSeat().getPrice()),
                            Double.toString(ticketOrder.getSeat().getServicePrice())},
                    null, null, null);

            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    seatId = newCursor.getInt(0);
                }

                ContentValues ticketOrderValues = new ContentValues();

                ticketOrderValues.put("_USERID", "1");
                ticketOrderValues.put("_SEATID", seatId);
                ticketOrderValues.put("PURCHASEDATE", getDateTime(ticketOrder.getPurchaseDate(), LONG_DATE));
                ticketOrderValues.put("STATUS", ticketOrder.getStatus());
                ticketOrderValues.put("SERIES", ticketOrder.getSeries());
                ticketOrderValues.put("NUMBER", ticketOrder.getNumber());
                ticketOrderValues.put("CODE", ticketOrder.getCode());
                ticketOrderValues.put("TIMESTAMP", getDateTime(ticketOrder.getTimeStamp(), LONG_DATE));

                db.insert("TICKETORDER", null, ticketOrderValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }

    private static void insertSubscription(SQLiteDatabase db, Subscription subscription) {

        int eventId = Integer.MAX_VALUE;

        try {

            Cursor newCursor = db.query("EVENT",
                    new String[]{"_id"},
                    "NAME = ? AND COUNTRY = ? AND GENRE = ? AND AMOUNTTIME = ? AND YEAR = ?",
                    new String[]{subscription.getEvent().getName(), subscription.getEvent().getCountry(),
                            subscription.getEvent().getGenre(), subscription.getEvent().getAmountTime(),
                            Integer.toString(subscription.getEvent().getYear())},
                    null, null, null);


            if (newCursor.getCount() == 1) {
                if (newCursor.moveToFirst()) {
                    eventId = newCursor.getInt(0);
                }

                ContentValues subscriptionValues = new ContentValues();

                subscriptionValues.put("_EVENTID", eventId);
                subscriptionValues.put("AMOUNTSEATS", subscription.getAmountSeats());
                subscriptionValues.put("ISNOTIFIED", subscription.getIsNotified());
                subscriptionValues.put("TIMESTAMP", getDateTime(subscription.getTimeStamp(), LONG_DATE));

                db.insert("SUBSCRIPTION", null, subscriptionValues);
            }

        } catch (SQLiteException e) {
            Log.d("My Logs", "Ошибка доступа к БД!");
        }

    }


    public static Cursor getEvents(SQLiteDatabase db, String event) {

        Cursor newCursor = db.rawQuery("SELECT * FROM EVENT, ESTABLISHMENT " +
                "WHERE ESTABLISHMENT._id = EVENT._ESTABLISHMENTID  AND ESTABLISHMENT.TYPE = '" + event + "' " +
                "GROUP BY EVENT._id", null);

        return newCursor;

    }

    public static Cursor getEstablishment(SQLiteDatabase db, String establishment) {
        Cursor newCursor = db.query("ESTABLISHMENT",
                new String[]{"_id", "NAME", "ADDRESS"},
                "TYPE = ?", new String[]{establishment}, null, null, null);
        return newCursor;

    }


    private static String getDateTime(Date date, String dateFormat) {
        SimpleDateFormat myDateFormat = new SimpleDateFormat(
                dateFormat, Locale.getDefault());
        return myDateFormat.format(date);
    }

    private static Date getDateTime(String myDate, String dateFormat) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
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
