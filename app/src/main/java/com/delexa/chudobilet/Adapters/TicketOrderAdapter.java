package com.delexa.chudobilet.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delexa.chudobilet.MainClasses.TicketOrder;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.OrderActivity;

import java.util.Collections;
import java.util.List;

public class TicketOrderAdapter extends RecyclerView.Adapter<TicketOrderAdapter.EventViewHolder> {

    //  private LayoutInflater inflater;
    List<TicketOrder> data = Collections.emptyList();
    Context context;


    public TicketOrderAdapter(Context context, List<TicketOrder> data) {

        //  inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        EventViewHolder holder = new EventViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        TicketOrder current = data.get(position);

        holder.name.setText(current.getSeat().getEvent().getName());
        holder.establishment.setText(current.getSeat().getEvent().getEstablishment().getName());
        holder.code.setText(current.getCode());
        holder.seriesNumber.setText(current.getSeries() + " " + current.getNumber());

    }

    public int getItemCount() {
        return data.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView establishment;
        TextView code;
        TextView seriesNumber;


        public EventViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            name = (TextView) itemView.findViewById(R.id.textViewOrderName);
            establishment = (TextView) itemView.findViewById(R.id.textViewOrderEstablishment);
            code = (TextView) itemView.findViewById(R.id.textViewOrderCode);
            seriesNumber = (TextView) itemView.findViewById(R.id.textViewOrderSeriesNumber);

        }


        @Override
        public void onClick(View v) {

            int id = data.get(getAdapterPosition()).getId();

            Activity activity = (Activity) v.getContext();
            Intent intent = new Intent(activity, OrderActivity.class);

            intent.putExtra("_id", Integer.valueOf(id));
            v.getContext().startActivity(intent);

        }


    }

}
