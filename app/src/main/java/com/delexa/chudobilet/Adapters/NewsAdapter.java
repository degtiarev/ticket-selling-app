package com.delexa.chudobilet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.News;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.NewsActivity;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private LayoutInflater inflater;
    List<News> data = Collections.emptyList();
    Context context;


    public NewsAdapter(Context context, List<News> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        News current = data.get(position);


        Picasso.with(inflater.getContext()) //передаем контекст приложения
                .load(current.getCover())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.ic_menu_camera)
                .into(holder.cover); //ссылка на ImageView

        holder.name.setText(current.getName());
        holder.inShort.setText(current.getInShort());

        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(current.getDate());

        holder.date.setText(s);

    }

    public int getItemCount() {
        return data.size();
    }


    public void animateTo(List<News> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<News> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final News model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<News> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final News model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<News> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final News model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public News removeItem(int position) {
        final News model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, News model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final News model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }


    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cover;
        TextView name;
        TextView inShort;
        TextView date;


        public NewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            cover = (ImageView) itemView.findViewById(R.id.imageViewNewsCover);
            name = (TextView) itemView.findViewById(R.id.textViewNewsName);
            inShort = (TextView) itemView.findViewById(R.id.textViewNewsInShort);
            date = (TextView) itemView.findViewById(R.id.textViewNewsDate);

        }


        @Override
        public void onClick(View v) {

            int id = data.get(getAdapterPosition()).getId();

            Activity activity = (Activity) v.getContext();
            Intent intent = new Intent(activity, NewsActivity.class);

            intent.putExtra("_id", Integer.valueOf(id));
            v.getContext().startActivity(intent);
        }


    }

}
