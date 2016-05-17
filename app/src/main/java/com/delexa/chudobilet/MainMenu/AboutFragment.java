package com.delexa.chudobilet.MainMenu;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.delexa.chudobilet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RelativeLayout mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_about,
                container, false);

        Button buttonSendReport = (Button) mRelativeLayout.findViewById(R.id.buttonError);
        buttonSendReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"delexa0@gmail.com"});
                intent.putExtra(intent.EXTRA_SUBJECT, "Ошибка в приложении Chudobilet");
                intent.putExtra(intent.EXTRA_TEXT, "Уважаемый разработчик, в приложении Chudobilet я заметил следующую ошибку:");

                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "Не найдено клиентов для отправки email!", Toast.LENGTH_SHORT).show();
                }


            }
        });


        Button buttonSetRating = (Button) mRelativeLayout.findViewById(R.id.buttonSetRating);
        buttonSetRating.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id=Chudobilet");
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getActivity(), "Не найдено приложение GooglePlay", Toast.LENGTH_LONG).show();
                }

            }
        });


        return mRelativeLayout;
        // return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
