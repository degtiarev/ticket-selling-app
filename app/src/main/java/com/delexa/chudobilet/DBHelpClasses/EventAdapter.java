package com.delexa.chudobilet.DBHelpClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.R;

import java.util.Collections;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private LayoutInflater inflater;
    List<Event> data = Collections.emptyList();

    public EventAdapter(Context context, List<Event> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        EventViewHolder holder = new EventViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        Event current = data.get(position);

//        Picasso.with(inflater.getContext())
//                .load(current.coverURL)
//                .placeholder(R.drawable.no_photo)
//                .error(R.drawable.no_photo)
//                .into(holder.cover);

        holder.filmName.setText(current.getFilmName());
      //  holder.cover.setImageBitmap(current.cover);
        holder.genre.setText(current.getGenre());
        holder.time.setText(current.getTime());
        holder.country.setText(current.getCountry());
        holder.forAge.setText(current.getForAge());
        holder.year.setText(Integer.toString(current.getYear()));



    }

    public int getItemCount() {
        return data.size();
    }

    public int num;

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cover;
        TextView filmName;
        TextView genre;
        TextView time;
        TextView country;
        TextView forAge;
        TextView year;


        public EventViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            cover = (ImageView) itemView.findViewById(R.id.imageViewEventCover);
            filmName = (TextView) itemView.findViewById(R.id.textViewEventName);
            genre = (TextView) itemView.findViewById(R.id.textViewEventGenre);
            time = (TextView) itemView.findViewById(R.id.textViewEventAmountTime);
            country = (TextView) itemView.findViewById(R.id.textViewEventCountry);
            forAge = (TextView) itemView.findViewById(R.id.textViewEventForAge);
            year = (TextView) itemView.findViewById(R.id.textViewEventYear);
        }


        @Override
        public void onClick(View v) {

        }

        public int getPos() {
            return num;
        }

    }

}
