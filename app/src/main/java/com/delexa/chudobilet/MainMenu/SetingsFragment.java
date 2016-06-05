package com.delexa.chudobilet.MainMenu;


import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.MainClasses.User;
import com.delexa.chudobilet.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetingsFragment extends Fragment {

    View view;

    Calendar c = Calendar.getInstance();
    int cday, cmonth, cyear;
    Date currentBirthdayDate;

    EditText editTextName;
    EditText editTextFamily;
    EditText editTextPatronimic;
    EditText editTextEmail;
    RadioGroup radio_groupSex;
    CheckBox checkBoxNotificationForPayEmail;
    CheckBox checkBoxNotificationForPaySMS;
    CheckBox checkBoxNotificationAboutStatusEmail;
    CheckBox checkBoxNotificationAboutNewEventsEmail;
    CheckBox checkBoxNotificationAboutNewEventsSMS;

    TextView textViewBirthday;

    public SetingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setings, container, false);

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();

        if (ChudobiletDatabaseHelper.isAuthorized(db) == false) {

            Fragment fragment = new AuthorizationFragment();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();

        }

        setHasOptionsMenu(true);

        db = chudobiletDatabaseHelper.getReadableDatabase();
        User user = ChudobiletDatabaseHelper.getUser(db);

        currentBirthdayDate = user.getDate();

        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextFamily = (EditText) view.findViewById(R.id.editTextFamily);
        editTextPatronimic = (EditText) view.findViewById(R.id.editTextPatronimic);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        textViewBirthday = (TextView) view.findViewById(R.id.editTextBirthday);
        radio_groupSex = (RadioGroup) view.findViewById(R.id.radio_groupSex);
        checkBoxNotificationForPayEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationForPayEmail);
        checkBoxNotificationForPaySMS = (CheckBox) view.findViewById(R.id.checkBoxNotificationForPaySMS);
        checkBoxNotificationAboutStatusEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutStatusEmail);
        checkBoxNotificationAboutNewEventsEmail = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutNewEventsEmail);
        checkBoxNotificationAboutNewEventsSMS = (CheckBox) view.findViewById(R.id.checkBoxNotificationAboutNewEventsSMS);

        editTextName.setText(user.getName());
        editTextFamily.setText(user.getFamily());
        editTextPatronimic.setText(user.getPatronymic());
        editTextEmail.setText(user.getEmail());
        textViewBirthday.setText(getDateTime(user.getDate()));


        if (user.getSex().equals("M")) radio_groupSex.check(R.id.radio_M);
        else radio_groupSex.check(R.id.radio_F);

        if (user.getNotificationToPay().charAt(0) == '1')
            checkBoxNotificationForPayEmail.setChecked(true);
        if (user.getNotificationToPay().charAt(1) == '1')
            checkBoxNotificationForPaySMS.setChecked(true);
        if (user.getEmailNotificationChangeStatus().charAt(0) == '1')
            checkBoxNotificationAboutStatusEmail.setChecked(true);
        if (user.getNewsSubscriber().charAt(0) == '1')
            checkBoxNotificationAboutNewEventsEmail.setChecked(true);
        if (user.getNewsSubscriber().charAt(1) == '1')
            checkBoxNotificationAboutNewEventsSMS.setChecked(true);


        textViewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        String[] names = {"Выйти из профиля"};
        ListView exit = (ListView) view.findViewById(R.id.listViewExitFromProfile);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, names);
        exit.setAdapter(adapter);
        exit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (l == 0) {
                    SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
                    SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                    ChudobiletDatabaseHelper.quitFromProfile(db);

                    TextView myName = (TextView) getActivity().findViewById(R.id.textViewNameUP);
                    TextView myEmail = (TextView) getActivity().findViewById(R.id.textViewEmailUP);
                    ImageView myPhoto = (ImageView) getActivity().findViewById(R.id.imageViewPhoto);
                    TextView enter = (TextView) getActivity().findViewById(R.id.imageViewAuthorise);

                    myPhoto.setImageResource(R.drawable.rounded_avatar);
                    myName.setText(" ");
                    myEmail.setText(" ");
                    enter.setVisibility(View.VISIBLE);


                    Fragment fragment = new AuthorizationFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();


                }
            }
        });


        textViewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setTime(currentBirthdayDate);

                new DatePickerDialog(getContext(), d,
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                        .get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        return view;
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            cday = dayOfMonth;
            cmonth = monthOfYear + 1;
            cyear = year;

            String day = Integer.toString(cday);
            if (day.length() == 1) day = "0" + day;

            String month = Integer.toString(cmonth);
            if (month.length() == 1) month = "0" + month;

            textViewBirthday.setText(cyear + "-" + month + "-" + day);
        }
    };


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.toSave:
                try {

                    RadioButton M = (RadioButton) radio_groupSex.getChildAt(0);
                    RadioButton F = (RadioButton) radio_groupSex.getChildAt(1);

                    User user = new User();
                    user.setFamily(editTextFamily.getText().toString());
                    user.setName(editTextName.getText().toString());
                    user.setPatronymic(editTextPatronimic.getText().toString());
                    user.setEmail(editTextEmail.getText().toString());
                    user.setDate(getDateTime(textViewBirthday.getText().toString()));

                    if (M.isChecked()) user.setSex("M");
                    else user.setSex("F");

                    String notificationToPay = "";
                    if (checkBoxNotificationForPayEmail.isChecked()) notificationToPay += "1";
                    else notificationToPay += "0";
                    if (checkBoxNotificationForPaySMS.isChecked()) notificationToPay += "1";
                    else notificationToPay += "0";
                    user.setNotificationToPay(notificationToPay);

                    if (checkBoxNotificationAboutStatusEmail.isChecked())
                        user.setEmailNotificationChangeStatus("1");
                    else user.setEmailNotificationChangeStatus("0");

                    String newsSubscriber = "";
                    if (checkBoxNotificationAboutNewEventsEmail.isChecked()) newsSubscriber += 1;
                    else newsSubscriber += 0;
                    if (checkBoxNotificationAboutNewEventsSMS.isChecked()) newsSubscriber += 1;
                    else newsSubscriber += 0;
                    user.setNewsSubscriber(newsSubscriber);

                    SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
                    SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                    ChudobiletDatabaseHelper.setUserInfo(db,user);

                } catch (Exception e) {
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Настройки");
    }


    private static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
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

}