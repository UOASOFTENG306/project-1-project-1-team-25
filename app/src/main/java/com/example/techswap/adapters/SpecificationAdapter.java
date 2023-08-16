package com.example.techswap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.R;

import java.util.List;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.SpecificationViewHolder> {
    private List<String> specificationList;
    private List<String> valueList;

    public SpecificationAdapter(List<String> specificationList, List<String> valueList) {
        this.specificationList = specificationList;
        this.valueList = valueList;
    }

    @NonNull
    @Override
    public SpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.specification_row, parent, false);
        return new SpecificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecificationViewHolder holder, int position) {
        String specification = specificationList.get(position);
        holder.specificationText.setText(specification);

        String value = valueList.get(position); // Replace with your data source
        holder.valueText.setText(value);
    }


    @Override
    public int getItemCount() {
        return specificationList.size();
    }

    public class SpecificationViewHolder extends RecyclerView.ViewHolder {
        TextView specificationText;
        TextView valueText;

        public SpecificationViewHolder(@NonNull View itemView) {
            super(itemView);
            specificationText = itemView.findViewById(R.id.specificationText);
            valueText = itemView.findViewById(R.id.specificationValue);
        }
    }
}