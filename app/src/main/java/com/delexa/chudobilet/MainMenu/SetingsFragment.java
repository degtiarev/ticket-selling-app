package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.MainClasses.User;
import com.delexa.chudobilet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetingsFragment extends Fragment {

    View view;


    public SetingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_setings, container, false);
        setHasOptionsMenu(true);

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        User user = ChudobiletDatabaseHelper.getUser(db);

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

        editTextName.setText(user.getName());
        editTextFamily.setText(user.getFamily());
        editTextPatronimic.setText(user.getPatronymic());
        editTextEmail.setText(user.getEmail());
        editTextBirthday.setText(getDateTime(user.getDate()));

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

        return view;
    }


    private static String getDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

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
                //newGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}