package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.TicketOrderAdapter;
import com.delexa.chudobilet.MainClasses.TicketOrder;
import com.delexa.chudobilet.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private TicketOrderAdapter ticketOrderAdapter;


    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_orders, container, false);

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        if (ChudobiletDatabaseHelper.isAuthorized(db) == false) {

            Fragment fragment = new AuthorizationFragment();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }


        recyclerView = (RecyclerView) v.findViewById(R.id.OrderList);


        ticketOrderAdapter = new TicketOrderAdapter(getActivity(), getTicketOrders());
        recyclerView.setAdapter(ticketOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }

    public List<TicketOrder> getTicketOrders() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<TicketOrder> data = ChudobiletDatabaseHelper.getTicketOrders(db);

        return data;
    }


}
