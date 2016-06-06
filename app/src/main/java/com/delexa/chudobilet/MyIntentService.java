package com.delexa.chudobilet;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;


public class MyIntentService extends IntentService {


    public static final String EXTRA_MESSAGE = "Чудобилет";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {


            while (!isNetworkAvailable()) {

                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            EstablishmmentAPIUpdater establishmmentAPIUpdater = new EstablishmmentAPIUpdater(this);
//            establishmmentAPIUpdater.update();
//            EventAPIUpdater eventAPIUpdater = new EventAPIUpdater(this);
//            eventAPIUpdater.update();
//
//            SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(this);
//            SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
//            String result = ChudobiletDatabaseHelper.becameNewEventsByGenre(db);
//            if (!result.equals("")) {
//                //  String text = intent.getStringExtra(EXTRA_MESSAGE);
//                showText(result, 1);
//            }
                if (isNetworkAvailable()) {
                    showText("Освободились Ваши любимые места на событие \"Белоснежка и Охотник 2\"", 1);
                    showText("Новое событие по интересам: Варкравт", 2);
                    showText("Новое событие по интересам: Книга Джунглей", 3);

                }

            }

        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showText(final String text, int notificationID) {
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT
        );
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Чудобилет")
                .setAutoCancel(true).setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent).
                        setContentText(text)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notification);

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
