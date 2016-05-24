package com.delexa.chudobilet.SubMenu;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.delexa.chudobilet.DBClasses.InterestEstablishment;
import com.delexa.chudobilet.DBClasses.TicketOrder;
import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.DBHelpClasses.InterestEstablishmentAdapter;
import com.delexa.chudobilet.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubscriptionInterestFragment extends Fragment {


    public SubscriptionInterestFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private InterestEstablishmentAdapter interestEstablishmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_subscription_interest, container, false);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Activity activity = (Activity) view.getContext();
                Intent intent = new Intent(activity, EditSubscriptionInterestActivity.class);

                view.getContext().startActivity(intent);

            }
        });

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        recyclerView = (RecyclerView) v.findViewById(R.id.EventInterestList);

        interestEstablishmentAdapter = new InterestEstablishmentAdapter(getActivity(), getInterestEstablishment());
        recyclerView.setAdapter(interestEstablishmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        ((EditText)v.findViewById(R.id.editTextGenre)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//    /* When focus is lost check that the text field
//    * has valid values.
//    */
//                if (!hasFocus) {
//                    validateInput(v);
//                }
//            }
//        });


        return v;

    }

    public List<InterestEstablishment> getInterestEstablishment() {

        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<InterestEstablishment> data = ChudobiletDatabaseHelper.getInterestEstablishment(db);

        return data;
    }

}
