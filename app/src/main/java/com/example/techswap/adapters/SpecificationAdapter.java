package com.example.techswap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.techswap.R;
import com.example.techswap.interfaces.ISpecificationAdapter;

import java.util.List;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.SpecificationViewHolder> implements ISpecificationAdapter {
    private final List<String> specificationList;
    private final List<String> valueList;

    public SpecificationAdapter(List<String> specificationList, List<String> valueList) {
        this.specificationList = specificationList;
        this.valueList = valueList;
    }

    @NonNull
    @Override
    public SpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_specification_row, parent, false);
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
        if (specificationList.get(0).equals("")) {
            return 0;
        }
        return Integer.min(specificationList.size(), valueList.size());
    }

    public static class SpecificationViewHolder extends RecyclerView.ViewHolder {
        final TextView specificationText;
        final TextView valueText;

        public SpecificationViewHolder(@NonNull View itemView) {
            super(itemView);
            specificationText = itemView.findViewById(R.id.specificationText);
            valueText = itemView.findViewById(R.id.specificationValue);
        }
    }
}