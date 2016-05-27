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

import com.delexa.chudobilet.MainClasses.Event;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.EventActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class SmallCardAdapter extends RecyclerView.Adapter<SmallCardAdapter.SmallCardViewHolder> {

    private LayoutInflater inflater;
    List<Event> data = Collections.emptyList();
    Context context;


    public SmallCardAdapter(Context context, List<Event> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public SmallCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.smallcard_item, parent, false);
        SmallCardViewHolder holder = new SmallCardViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(SmallCardViewHolder holder, int position) {

        Event current = data.get(position);


        Picasso.with(inflater.getContext()) //передаем контекст приложения
                .load(current.getCover())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.ic_menu_camera)
                .into(holder.cover); //ссылка на ImageView

        holder.name.setText(current.getName());


    }

    public int getItemCount() {
        return data.size();
    }


    class SmallCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cover;
        TextView name;


        public SmallCardViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            cover = (ImageView) itemView.findViewById(R.id.imageViewSmallCardCover);
            name = (TextView) itemView.findViewById(R.id.SmallCardName);
        }

        @Override
        public void onClick(View v) {

            int id = data.get(getAdapterPosition()).getId();

            Activity activity = (Activity) v.getContext();
            Intent intent = new Intent(activity, EventActivity.class);

            intent.putExtra("_id", Integer.valueOf(id));
            v.getContext().startActivity(intent);

        }


    }
}
