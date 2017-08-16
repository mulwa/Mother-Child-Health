package com.example.mulwa.mc_care.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mulwa.mc_care.FilteredSpecialist;
import com.example.mulwa.mc_care.Pojo.Specialization;
import com.example.mulwa.mc_care.R;
import com.example.mulwa.mc_care.Service.Constant;

import java.util.List;

/**
 * Created by mulwa on 7/25/17.
 */

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.myViewHolder> {
    private List<Specialization> experts;
    private Context context;

    public SpecializationAdapter(List<Specialization> experts, Context context) {
        this.experts = experts;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialist_custom_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Specialization specialization = experts.get(position);
        holder.specialist_area.setText(specialization.getName());

    }

    @Override
    public int getItemCount() {
        return experts.size();
    }

    public  class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView specialist_area;

        public myViewHolder(View itemView) {
            super(itemView);
            specialist_area = (TextView) itemView.findViewById(R.id.tvSpecialization);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            // Check if an item was deleted, but the user clicked it before the UI removed it
            if(position != RecyclerView.NO_POSITION){
                Specialization specialization = experts.get(position);
//                Log.d("clicked","by me"+specialization.getName());

                Intent intent = new Intent(context, FilteredSpecialist.class);
                intent.putExtra(Constant.SPECIALIST, specialization.getName());
                context.startActivity(intent);


            }
        }
    }
}
