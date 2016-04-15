package com.delexa.chudobilet.Cinema;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private LayoutInflater inflater;
    List<Movie> data = Collections.emptyList();

    public MovieAdapter(Context context, List<Movie> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movie current = data.get(position);

        Picasso.with(inflater.getContext())
                .load(current.coverURL)
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.cover);

        holder.filmName.setText(current.filmName);
        holder.genre.setText(current.genre);
        holder.time.setText(current.time);
        holder.country.setText(current.country);
        holder.forAge.setText(current.forAge);
    }

    public int getItemCount() {
        return data.size();
    }

    public int num;

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cover;
        TextView filmName;
        TextView genre;
        TextView time;
        TextView country;
        TextView forAge;


        public MovieViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            cover = (ImageView) itemView.findViewById(R.id.imageViewCover);
            filmName = (TextView) itemView.findViewById(R.id.textViewFilmName);
            genre = (TextView) itemView.findViewById(R.id.textViewGenre);
            time = (TextView) itemView.findViewById(R.id.textViewFilmTime);
            country = (TextView) itemView.findViewById(R.id.textViewCountry);
            forAge = (TextView) itemView.findViewById(R.id.textViewForAge);
        }


        @Override
        public void onClick(View v) {

        }

        public int getPos() {
            return num;
        }

    }

}
