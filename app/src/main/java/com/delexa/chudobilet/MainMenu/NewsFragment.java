package com.delexa.chudobilet.MainMenu;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.delexa.chudobilet.API.NewsAPIUpdater;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.NewsAdapter;
import com.delexa.chudobilet.MainClasses.News;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> news;
    private View v;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public NewsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.NewsList);

        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refreshNews);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        return v;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        news = getNews();
        newsAdapter = new NewsAdapter(getActivity(), news);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        NewsAPIUpdater newsAPIUpdater = new NewsAPIUpdater(getContext());
//        newsAPIUpdater.update();
    }

    public List<News> getNews() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<News> data = ChudobiletDatabaseHelper.getNews(db);

        return data;
    }

    @Override
    public void onRefresh() {
        // говорим о том, что собираемся начать
//        Toast.makeText(getContext(), "Начинаем", Toast.LENGTH_SHORT).show();
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить

                newsAdapter = new NewsAdapter(getActivity(), getNews());
                recyclerView.setAdapter(newsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                Toast.makeText(getContext(), "заканчиваем", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.searchable, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setQueryHint("Поиск");
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<News> filteredModelList = filter(news, query);
       // Log.d("CHUDOBILET", String.valueOf(news.size()) + "-" + String.valueOf(filteredModelList.size()));
        newsAdapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        newsAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<News> filter(List<News> models, String query) {
        query = query.toLowerCase();

        final List<News> filteredModelList = new ArrayList<>();
        for (News curNews : models) {
            final String text = curNews.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(curNews);
            }
        }
        return filteredModelList;
    }


}
