package com.delexa.chudobilet.MainMenu;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthorizationFragment extends Fragment {


    public AuthorizationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_authorization, container, false);

        Button registration = (Button) v.findViewById(R.id.buttonRegistration);
        Button enter = (Button) v.findViewById(R.id.buttonEnter);
        final EditText email = (EditText) v.findViewById(R.id.editTextAuthEmail);
        final EditText password = (EditText) v.findViewById(R.id.editTextAuthPassword);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://new.chudobilet.ru/registration/"));
                startActivity(browserIntent);
            }
        });


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // запрос на сервер с учетными записями
                // по идее должен быть зарос на сервис. Но пока авторизуюсь через локальную бд (проверяю авторизацию путем удаления пароля)

                SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getContext());
                SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
                ChudobiletDatabaseHelper.authorize(db, email.getText().toString(), password.getText().toString());

                db = chudobiletDatabaseHelper.getWritableDatabase();
                if (ChudobiletDatabaseHelper.isAuthorized(db)) {
                    Toast toast = Toast.makeText(getContext(), "Авторизация прошла успешно!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getContext(), "Ошибка при вводе email или пароля", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });


        return v;
    }

}
