package com.delexa.chudobilet.DBHelpClasses;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.delexa.chudobilet.DBClasses.InterestEstablishment;
import com.delexa.chudobilet.R;
import com.delexa.chudobilet.SubMenu.EditSubscriptionInterestActivity;
import com.delexa.chudobilet.SubMenu.EventActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class InterestEstablishmentAdapter extends RecyclerView.Adapter<InterestEstablishmentAdapter.InterestEstablishmentViewHolder> {

    private LayoutInflater inflater;
    List<InterestEstablishment> data = Collections.emptyList();
    // Context context;


    public InterestEstablishmentAdapter(Context context, List<InterestEstablishment> data) {

        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public InterestEstablishmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_establishment_item, parent, false);
        InterestEstablishmentViewHolder holder = new InterestEstablishmentViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(InterestEstablishmentViewHolder holder, int position) {

        InterestEstablishment current = data.get(position);


        holder.establishmentName.setText(current.getEstablishment().getName());
        holder.seatName.setText(current.getSeatNames());

    }

    public int getItemCount() {
        return data.size();
    }


    class InterestEstablishmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView establishmentName;
        TextView seatName;


        public InterestEstablishmentViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            establishmentName = (TextView) itemView.findViewById(R.id.textViewInterestEstablishmentName);
            seatName = (TextView) itemView.findViewById(R.id.textViewEstablishmentSeat);

        }


        @Override
        public void onClick(View v) {

            int id = data.get(getAdapterPosition()).getId();

            Activity activity = (Activity) v.getContext();
            Intent intent = new Intent(activity, EditSubscriptionInterestActivity.class);

            intent.putExtra("_id", Integer.valueOf(id));
            v.getContext().startActivity(intent);

        }


    }

}
