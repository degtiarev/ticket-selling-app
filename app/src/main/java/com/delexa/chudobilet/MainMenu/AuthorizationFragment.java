package com.delexa.chudobilet.MainMenu;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.MainClasses.User;
import com.delexa.chudobilet.R;
import com.squareup.picasso.Picasso;


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

                db = chudobiletDatabaseHelper.getReadableDatabase();
                if (ChudobiletDatabaseHelper.isAuthorized(db)) {
                    Toast toast = Toast.makeText(getContext(), "Авторизация прошла успешно!", Toast.LENGTH_SHORT);
                    toast.show();


                    NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                    View header = navigationView.getHeaderView(0);
                    TextView myName = (TextView) header.findViewById(R.id.textViewName);
                    TextView myEmail = (TextView) header.findViewById(R.id.textViewEmail);
                    ImageView myPhoto = (ImageView) header.findViewById(R.id.imageViewPhoto);
                    db = chudobiletDatabaseHelper.getWritableDatabase();
                    User user = ChudobiletDatabaseHelper.getUser(db);

                    Picasso.with(getActivity()) //передаем контекст приложения
                            .load(user.getImage())
                            .placeholder(R.drawable.no_photo)
                            .error(R.drawable.ic_menu_camera)
                            .into(myPhoto); //ссылка на ImageView

                    myName.setText(user.getName());
                    myEmail.setText(user.getEmail());


                    Fragment fragment = new SetingsFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();


                } else {
                    Toast toast = Toast.makeText(getContext(), "Ошибка при вводе email или пароля", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });


        return v;
    }

}
