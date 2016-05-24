package com.delexa.chudobilet.MainMenu;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.EstablishmentActivity;
import com.delexa.chudobilet.SubMenu.SubscriberActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetingsFragment extends Fragment {

    View view;
    private SQLiteDatabase db;
    private Cursor userCursor;

    public SetingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setings, container, false);


        ListView listView = (ListView) view.findViewById(R.id.listView_options);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (id == 0) {
                    Activity activity = (Activity) view.getContext();
                    Intent intent = new Intent(activity, SubscriberActivity.class);
                    view.getContext().startActivity(intent);


                }

//                else if (id==1)
//                {
//
//
//                }

            }
        });


        gettingUserData();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        gettingUserData();
    }


    // закрытие курсора и бд
    @Override
    public void onDestroy() {
        super.onDestroy();
        userCursor.close();
        db.close();
    }


    private void gettingUserData() {
        try {

            ChudobiletDatabaseHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getActivity());
            db = chudobiletDatabaseHelper.getReadableDatabase();
            Cursor newCursor = db.query("USER",
                    new String[]{"NAME", "FAMILY", "PATRONYMIC", "EMAIL", "DATE", "SEX", "NOTIFICATIONTOPAY", "EMAILNOTIFICATIONCHANGESTATUS",
                            "NEWSSUBSCRIBER", "PHONE", "PASSWORD", "TIMESTAMP"},
                    null, null, null, null, null);

            userCursor = newCursor;

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(getActivity(), "Ошибка достура к БД", Toast.LENGTH_SHORT);
            toast.show();
        }

        if (userCursor.getCount() == 1) {
            if (userCursor.moveToFirst()) {

                String name = userCursor.getString(0);
                String family = userCursor.getString(1);
                String patronymic = userCursor.getString(2);
                String email = userCursor.getString(3);
                Date date = getDateTime(userCursor.getString(4));
                String sex = userCursor.getString(5);
                String notificationToPay = userCursor.getString(6);
                String emailNotificationChangeStatus = userCursor.getString(7);
                String newsSubscriber = userCursor.getString(8);
                String phone = userCursor.getString(9);
                String password = userCursor.getString(10);
                String timestamp = userCursor.getString(11);


                EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
                EditText editTextFamily = (EditText) view.findViewById(R.id.editTextFamily);
                EditText editTextPatronimic = (EditText) view.findViewById(R.id.editTextPatronimic);
                EditText editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
                EditText editTextBirthday = (EditText) view.findViewById(R.id.editTextBirthday);
                RadioGroup radio_groupSex = (RadioGroup) view.findViewById(R.id.radio_groupSex);
                CheckBox checkBoxNotificationForPayEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationForPayEmail);
                CheckBox checkBoxNotificationForPaySMS = (CheckBox) view.findViewById(R.id.checkBoxNotificationForPaySMS);
                CheckBox checkBoxNotificationAboutStatusEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutStatusEmail);
                CheckBox checkBoxNotificationAboutNewEventsEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutNewEventsEmail);
                CheckBox checkBoxNotificationAboutNewEventsSMS = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutNewEventsSMS);

                editTextName.setText(name);
                editTextFamily.setText(family);
                editTextPatronimic.setText(patronymic);
                editTextEmail.setText(email);
                editTextBirthday.setText(getDateTime(date));

                if (sex.equals("M")) radio_groupSex.check(R.id.radio_M);
                else radio_groupSex.check(R.id.radio_F);

                if (notificationToPay.charAt(0) == '1')
                    checkBoxNotificationForPayEmail.setChecked(true);
                if (notificationToPay.charAt(1) == '1')
                    checkBoxNotificationForPaySMS.setChecked(true);
                if (emailNotificationChangeStatus.charAt(0) == '1')
                    checkBoxNotificationAboutStatusEmail.setChecked(true);
                if (newsSubscriber.charAt(0) == '1')
                    checkBoxNotificationAboutNewEventsEmail.setChecked(true);
                if (newsSubscriber.charAt(1) == '1')
                    checkBoxNotificationAboutNewEventsSMS.setChecked(true);
            }


        }


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

    private static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);


    }
}