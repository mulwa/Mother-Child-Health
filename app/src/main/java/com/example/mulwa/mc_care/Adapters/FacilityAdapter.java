package com.example.mulwa.mc_care.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mulwa.mc_care.Pojo.Clinics;
import com.example.mulwa.mc_care.R;

import java.util.List;

/**
 * Created by mulwa on 7/27/17.
 */

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.myViewHolder> {
    private List<Clinics> facilities;
    private Context context;

    public FacilityAdapter(List<Clinics> facilities, Context context) {
        this.facilities = facilities;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_custom_layout,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Clinics facility  = facilities.get(position);
        holder.tvFacilityName.setText(facility.getClinicName());
        holder.tvFacityType.setText(facility.getClinicType());
        holder.tvMobileNo.setText(facility.getMobileNumber());
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFacilityName;
        private TextView tvFacityType;
        private TextView tvMobileNo;
        private ImageButton imCall;

        public myViewHolder(View itemView) {
            super(itemView);

            tvFacilityName = (TextView) itemView.findViewById(R.id.ed_facility_name);
            tvFacityType = (TextView) itemView.findViewById(R.id.ed_facility_type);
            tvMobileNo = (TextView) itemView.findViewById(R.id.ed_facility_mobile_no);
            imCall = (ImageButton) itemView.findViewById(R.id.ib_call);
        }
    }
}
