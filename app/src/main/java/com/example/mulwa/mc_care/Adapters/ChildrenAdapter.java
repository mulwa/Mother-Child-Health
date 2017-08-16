package com.example.mulwa.mc_care.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mulwa.mc_care.Child.ChildMedicalHistory;
import com.example.mulwa.mc_care.Pojo.Child;
import com.example.mulwa.mc_care.R;
import com.example.mulwa.mc_care.Service.Constant;

import java.util.List;

/**
 * Created by mulwa on 7/29/17.
 */

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.myViewHolder> {
    private Context context;
    private List<Child> children;

    public ChildrenAdapter(Context context, List<Child> children) {
        this.context = context;
        this.children = children;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_custom_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Child child = children.get(position);
        holder.m_child_name.setText(child.getName());
        holder.m_child_id.setText(child.getPatient_id());
        holder.m_child_status.setText(child.getDate_of_birth());

    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView m_child_name;
        private TextView m_child_id;
        private TextView m_child_status;

        public myViewHolder(View itemView) {
            super(itemView);
            m_child_name = itemView.findViewById(R.id.child_name);
            m_child_id = itemView.findViewById(R.id.child_id);
            m_child_status = itemView.findViewById(R.id.child_status);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position  = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Child child = children.get(position);

                Intent intent = new Intent(context, ChildMedicalHistory.class);
                intent.putExtra(Constant.CHILD_OBJECT, child);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        }
    }
}
