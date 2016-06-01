package com.delexa.chudobilet;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v4.app.TaskStackBuilder;

import com.delexa.chudobilet.API.EstablishmmentAPIUpdater;
import com.delexa.chudobilet.API.EventAPIUpdater;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;


public class MyIntentService extends IntentService {


    public static final String EXTRA_MESSAGE = "Чудобилет";
    public static final int NOTIFICATION_ID = 5453;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {

            while (true) {

                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                EstablishmmentAPIUpdater establishmmentAPIUpdater = new EstablishmmentAPIUpdater(this);
                establishmmentAPIUpdater.update();
                EventAPIUpdater eventAPIUpdater = new EventAPIUpdater(this);
                eventAPIUpdater.update();

                SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(this);
                SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                String result = ChudobiletDatabaseHelper.becameNewEventsByGenre(db);
                if (!result.equals("")) {
                    //  String text = intent.getStringExtra(EXTRA_MESSAGE);
                    showText(result);
                }


            }

        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showText(final String text) {
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
        notificationManager.notify(NOTIFICATION_ID, notification);

    }


}
