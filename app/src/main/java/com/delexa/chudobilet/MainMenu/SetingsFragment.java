package com.delexa.chudobilet.MainMenu;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

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


        editTextBirthday.setOnClickListener(new View.OnClickListener() {
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
                try {
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

}