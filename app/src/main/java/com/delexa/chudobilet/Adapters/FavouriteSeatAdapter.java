package com.delexa.chudobilet.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.delexa.chudobilet.MainClasses.SeatName;
import com.delexa.chudobilet.R;

import java.util.Collections;
import java.util.List;

public class FavouriteSeatAdapter extends RecyclerView.Adapter<FavouriteSeatAdapter.EstablishmentViewHolder> {

    List<SeatName> data = Collections.emptyList();

    public FavouriteSeatAdapter(Context context, List<SeatName> data) {

        this.data = data;

    }

    @Override
    public EstablishmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_item, parent, false);
        EstablishmentViewHolder holder = new EstablishmentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EstablishmentViewHolder holder, int position) {

        SeatName current = data.get(position);

        holder.sector.setText(current.getSector());
        holder.row.setText(current.getRow());
        holder.seat.setText(current.getSeat());

    }

    public int getItemCount() {
        return data.size();
    }


    class EstablishmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        EditText sector;
        EditText row;
        EditText seat;


        public EstablishmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            sector = (EditText) itemView.findViewById(R.id.editTextSector);
            row = (EditText) itemView.findViewById(R.id.editTextRow);
            seat = (EditText) itemView.findViewById(R.id.editTextSeat);

        }

        @Override
        public void onClick(View v) {


//            int id = data.get(getAdapterPosition()).getId();
//
//            Activity activity = (Activity) v.getContext();
//            Intent intent = new Intent(activity, EstablishmentActivity.class);
//
//            intent.putExtra("_id", Integer.valueOf(id));
//            v.getContext().startActivity(intent);
        }


    }
}
