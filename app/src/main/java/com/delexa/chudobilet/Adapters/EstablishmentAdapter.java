package com.delexa.chudobilet.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.Establishment;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.EstablishmentActivity;

import java.util.Collections;
import java.util.List;

public class EstablishmentAdapter extends RecyclerView.Adapter<EstablishmentAdapter.EstablishmentViewHolder> {

    // private LayoutInflater inflater;
    List<Establishment> data = Collections.emptyList();

    public EstablishmentAdapter(Context context, List<Establishment> data) {

        // inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public EstablishmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.establishment_item, parent, false);
        EstablishmentViewHolder holder = new EstablishmentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EstablishmentViewHolder holder, int position) {

        Establishment current = data.get(position);

        holder.name.setText(current.getName());
        holder.address.setText(current.getAddress());

    }

    public int getItemCount() {
        return data.size();
    }


    class EstablishmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView address;


        public EstablishmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.textViewEstablishmentName);
            address = (TextView) itemView.findViewById(R.id.textViewEstablishmentAddress);
        }


        @Override
        public void onClick(View v) {


            int id = data.get(getAdapterPosition()).getId();

            Activity activity = (Activity) v.getContext();
            Intent intent = new Intent(activity, EstablishmentActivity.class);

            intent.putExtra("_id", Integer.valueOf(id));
            v.getContext().startActivity(intent);
        }


    }
}
