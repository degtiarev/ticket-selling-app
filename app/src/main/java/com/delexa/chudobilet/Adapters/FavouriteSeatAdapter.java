package com.delexa.chudobilet.Adapters;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.delexa.chudobilet.MainClasses.SeatName;
import com.delexa.chudobilet.R;

import java.util.Collections;
import java.util.List;

public class FavouriteSeatAdapter extends RecyclerView.Adapter<FavouriteSeatAdapter.EstablishmentViewHolder> {

    List<SeatName> data = Collections.emptyList();
    String establishmentName;

    public FavouriteSeatAdapter(Context context, List<SeatName> data, String establishmentName) {

        this.data = data;
        this.establishmentName = establishmentName;

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
        ImageView delete;


        public EstablishmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            sector = (EditText) itemView.findViewById(R.id.editTextSector);
            row = (EditText) itemView.findViewById(R.id.editTextRow);
            seat = (EditText) itemView.findViewById(R.id.editTextSeat);

            delete = (ImageView) itemView.findViewById(R.id.delete_img);
            delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            SeatName seatName = data.get(getAdapterPosition());


            if (v.getId() == R.id.delete_img) {

                SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(v.getContext());
                SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                ChudobiletDatabaseHelper.deleteSeatBySeatNamendEstablishmentName(db, establishmentName, seatName);

                db = chudobiletDatabaseHelper.getReadableDatabase();
                data = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);
                notifyDataSetChanged();

            }

        }
    }


}
