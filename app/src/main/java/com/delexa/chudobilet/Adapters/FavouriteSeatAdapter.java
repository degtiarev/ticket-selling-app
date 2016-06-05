package com.delexa.chudobilet.Adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        TextView sector;
        TextView row;
        TextView seat;
        ImageView delete;


        public EstablishmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            sector = (TextView) itemView.findViewById(R.id.editTextSector);
            row = (TextView) itemView.findViewById(R.id.editTextRow);
            seat = (TextView) itemView.findViewById(R.id.editTextSeat);

            delete = (ImageView) itemView.findViewById(R.id.delete_img);
            delete.setOnClickListener(this);

        }

        @Override
        public void onClick(final View v) {
            final SeatName seatName = data.get(getAdapterPosition());


            if (v.getId() == R.id.delete_img) {

                SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(v.getContext());
                SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                ChudobiletDatabaseHelper.deleteSeatBySeatNamendEstablishmentName(db, establishmentName, seatName);

                db = chudobiletDatabaseHelper.getReadableDatabase();
                data = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);
                notifyDataSetChanged();

            } else {

                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());

                alert.setTitle("Редактирование места");
                alert.setMessage("Введите расположение необходимого места");

                Context context = v.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText sector = new EditText(context);
                sector.setHint("Сектор");
                sector.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(sector);
                final EditText row = new EditText(context);
                row.setHint("Ряд");
                row.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(row);
                final EditText seat = new EditText(context);
                seat.setHint("Место");
                seat.setInputType(InputType.TYPE_CLASS_NUMBER);
                layout.addView(seat);

                alert.setView(layout);

                sector.setText(seatName.getSector());
                row.setText(seatName.getRow());
                seat.setText(seatName.getSeat());


                alert.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // нужно старое место и заменить на новое

                        SQLiteOpenHelper chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(v.getContext());
                        SQLiteDatabase db = chudobiletDatabaseHelper.getReadableDatabase();
                        List<SeatName> seatNames = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);

                        String newseats = "";


//                        Log.v("Проверка мест", "Сектор начальное= " + seatName.getSector());
//                        Log.v("Проверка мест", "Сектор конечное= " + sector.getText().toString());


                        for (SeatName seatName1 : seatNames) {

                            if (seatName.getRow().equals(seatName1.getRow()) &&
                                    seatName.getSector().equals(seatName1.getSector()) &&
                                    seatName.getSeat().equals(seatName1.getSeat())) {

                                newseats += sector.getText().toString() + " ";
                                newseats += row.getText().toString() + " ";
                                newseats += seat.getText().toString() + ", ";


                            } else {
                                newseats += seatName1.getSector() + " ";
                                newseats += seatName1.getRow() + " ";
                                newseats += seatName1.getSeat() + ", ";
                            }
                        }

                        chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(v.getContext());
                        db = chudobiletDatabaseHelper.getWritableDatabase();
                        ChudobiletDatabaseHelper.changeFavouriteSeats(db, establishmentName, newseats);

                        chudobiletDatabaseHelper = ChudobiletDatabaseHelper.getInstance(v.getContext());
                        db = chudobiletDatabaseHelper.getReadableDatabase();
                        data = ChudobiletDatabaseHelper.getEstablishmentFavouriteSeatsbyEstablishmentid(db, establishmentName);
                        notifyDataSetChanged();

                    }
                });

                alert.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }

                });

                alert.show();

            }
        }


    }
}
