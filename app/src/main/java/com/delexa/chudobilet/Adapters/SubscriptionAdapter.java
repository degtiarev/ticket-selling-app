package com.delexa.chudobilet.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.Subscription;
import com.delexa.chudobilet.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.SubscriptionViewHolder> {

    private LayoutInflater inflater;
    List<Subscription> data = Collections.emptyList();
    Context context;


    public SubscriptionAdapter(Context context, List<Subscription> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public SubscriptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_item, parent, false);
        SubscriptionViewHolder holder = new SubscriptionViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(SubscriptionViewHolder holder, int position) {

        Subscription current = data.get(position);

        Picasso.with(inflater.getContext()) //передаем контекст приложения
                .load(current.getEvent().getCover())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.ic_menu_camera)
                .into(holder.cover); //ссылка на ImageView


        holder.name.setText(current.getEvent().getName());
        holder.genre.setText(current.getEvent().getGenre());
        holder.forAge.setText(current.getEvent().getForAge());
        holder.amountSeats.setText(Integer.toString(current.getAmountSeats()));

        if (current.getEvent().getYear() != 0)
            holder.year.setText(Integer.toString(current.getEvent().getYear()));


    }

    public int getItemCount() {
        return data.size();
    }


    class SubscriptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cover;
        TextView name;
        TextView genre;
        TextView amountSeats;
        TextView forAge;
        TextView year;

        public SubscriptionViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            cover = (ImageView) itemView.findViewById(R.id.imageViewSubscriptionCover);
            name = (TextView) itemView.findViewById(R.id.textViewSubscriptionName);
            genre = (TextView) itemView.findViewById(R.id.textViewSubscriptionGenre);
            amountSeats = (TextView) itemView.findViewById(R.id.textViewSubscriptionAmountSeats);
            forAge = (TextView) itemView.findViewById(R.id.textViewSubscriptionForAge);
            year = (TextView) itemView.findViewById(R.id.textViewSubscriptionYear);

        }


        @Override
        public void onClick(View v) {

//            int id = data.get(getAdapterPosition()).getId();
//
//            Activity activity = (Activity) v.getContext();
//            Intent intent = new Intent(activity, SubscriptionActivity.class);
//
//            intent.putExtra("_id", Integer.valueOf(id));
//            v.getContext().startActivity(intent);

        }


    }

}
