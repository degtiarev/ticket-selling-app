package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.DBClasses.News;
import com.delexa.chudobilet.DBClasses.TicketOrder;
import com.delexa.chudobilet.DBHelpClasses.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.DBHelpClasses.NewsAdapter;
import com.delexa.chudobilet.DBHelpClasses.TicketOrderAdapter;
import com.delexa.chudobilet.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.NewsList);

        newsAdapter = new NewsAdapter(getActivity(), getNews());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;

    }


    public List<News> getNews() {

        SQLiteOpenHelper chudobiletDatabaseHelper = new ChudobiletDatabaseHelper(getActivity());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<News> data = ChudobiletDatabaseHelper.getNews(db);

        return data;
    }

}
