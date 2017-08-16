package com.example.mulwa.mc_care.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mulwa.mc_care.Pojo.Expert;
import com.example.mulwa.mc_care.R;

import java.util.List;

/**
 * Created by mulwa on 7/27/17.
 */

public class ExperAdapter extends RecyclerView.Adapter<ExperAdapter.myViewHolder> {
    private List<Expert> experts;
    private Context context;

    public ExperAdapter(List<Expert> experts, Context context) {
        this.experts = experts;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialist_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        final Expert expert = experts.get(position);
        holder.name.setText(expert.getName());
        holder.specialist.setText(expert.getSpecialist());
        holder.mobile.setText(expert.getMobileNumber());
        holder.since.setText(expert.getDateRegistered());

        holder.ib_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"calling "+expert.getMobileNumber(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return experts.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView  specialist;
        private TextView  mobile;
        private TextView since;
        private ImageView ib_call;

        public myViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            specialist = itemView.findViewById(R.id.tv_specialist);
            mobile = itemView.findViewById(R.id.tv_mobileNo);
            since  =  itemView.findViewById(R.id.tv_since);;
            ib_call = itemView.findViewById(R.id.im_call);

        }
    }
}
