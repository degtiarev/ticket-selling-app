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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.delexa.chudobilet.API.Link;
import com.delexa.chudobilet.API.NewsAPI;
import com.delexa.chudobilet.Adapters.ChudobiletDatabaseHelper;
import com.delexa.chudobilet.Adapters.NewsAdapter;
import com.delexa.chudobilet.MainClasses.News;
import com.delexa.chudobilet.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements Callback<List<NewsAPI>>, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> news;
    private View v;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    public Retrofit retrofit;
    public Link service;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.NewsList);


        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
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


        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://pastebin.com/raw/")
                .build();
        service = retrofit.create(Link.class);
        Call<List<NewsAPI>> chudobilet = service.getNewsAPI();
        chudobilet.enqueue(this);

    }

    public List<News> getNews() {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getContext());
        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
        List<News> data = ChudobiletDatabaseHelper.getNews(db);

        return data;
    }

    @Override
    public void onResponse(Call<List<NewsAPI>> call, Response<List<NewsAPI>> response) {

        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(getActivity());
        SQLiteDatabase db = chudobiletDatabaseHelper.getWritableDatabase();
        ChudobiletDatabaseHelper.insertNewsAPI(db, response);

        newsAdapter = new NewsAdapter(getActivity(), getNews());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onFailure(Call<List<NewsAPI>> call, Throwable t) {

        Toast toast = Toast.makeText(getContext(), "Не удается соединиться с сервером! Приложение работает" +
                " в оффлайн режиме! Проверьте подключение!", Toast.LENGTH_SHORT);
        toast.show();
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
        newsAdapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
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
