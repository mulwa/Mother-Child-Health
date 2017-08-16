package com.example.mulwa.mc_care.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mulwa.mc_care.Pojo.Immunization;
import com.example.mulwa.mc_care.R;

import java.util.List;

/**
 * Created by mulwa on 7/30/17.
 */

public class ImmunizationAdapter extends RecyclerView.Adapter<ImmunizationAdapter.myViewHolder> {
    private List<Immunization> immunizationList;
    private Context context;

    public ImmunizationAdapter(List<Immunization> immunizationList, Context context) {
        this.immunizationList = immunizationList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.immunization_custom_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Immunization immunization = immunizationList.get(position);
        String date_given = immunization.getDate_given();
        holder.m_vaccine_name.setText(immunization.getName());
        holder.m_due_date.setText(immunization.getExpected_date());

        if(date_given =="null"){
            holder.m_date_given.setText("Pending");
            holder.m_date_given.setTextColor(ContextCompat.getColor(context, R.color.deep_orange));
        }else {
            holder.m_date_given.setText(immunization.getDate_given());
            holder.m_date_given.setTextColor(ContextCompat.getColor(context, R.color.text_color));
        }


    }

    @Override
    public int getItemCount() {
        return immunizationList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView m_vaccine_name;
        private TextView m_due_date;
        private TextView m_date_given;

        public myViewHolder(View itemView) {
            super(itemView);
            m_vaccine_name = itemView.findViewById(R.id.tv_vaccine_Name);
            m_due_date = itemView.findViewById(R.id.tv_due_date);
            m_date_given = itemView.findViewById(R.id.tv_date_given);
        }
    }
}
