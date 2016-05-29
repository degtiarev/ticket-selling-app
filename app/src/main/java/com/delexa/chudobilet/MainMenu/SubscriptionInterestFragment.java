package com.delexa.chudobilet.MainMenu;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.EditSubscriptionInterestActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionInterestFragment extends Fragment {


    public SubscriptionInterestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_subscription_interest, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final ListView listView = (ListView) v.findViewById(R.id.listViewEstablishments);

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<String> data = ChudobiletDatabaseHelper.getEstablishmentListNames(db);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                v.getContext(),
                android.R.layout.simple_list_item_1,
                data);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

                        String establishmentname = ((TextView) (view.findViewById(android.R.id.text1))).getText().toString();


                        Activity activity = (Activity) getContext();
                        Intent intent = new Intent(activity, EditSubscriptionInterestActivity.class);

                        intent.putExtra("establishmentName", String.valueOf(establishmentname));
                        getContext().startActivity(intent);
                    }
                }
        );


        return v;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.toSave:
                // do stuff
                return true;

        }

        return false;
    }

}
