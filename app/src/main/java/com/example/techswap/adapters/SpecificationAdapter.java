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

    /**
     * Constructor for the SpecificationAdapter class.
     * Initializes the adapter with the provided lists of specification and value data.
     *
     * @param specificationList The list of specification data to be displayed in the adapter.
     * @param valueList The list of corresponding value data to be displayed in the adapter.
     */
    public SpecificationAdapter(List<String> specificationList, List<String> valueList) {
        this.specificationList = specificationList;
        this.valueList = valueList;
    }

    /**
     * Called when RecyclerView needs a new {@link SpecificationViewHolder} of the given type to represent an item.
     * This method inflates the layout for the item view and creates a new {@link SpecificationViewHolder} instance
     * to hold the views within the item. It returns the created {@link SpecificationViewHolder} to be used for
     * displaying and managing the specification and value data of the item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new {@link SpecificationViewHolder} that holds an item view of the given view type.
     */
    @NonNull
    @Override
    public SpecificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_specification_row, parent, false);
        return new SpecificationViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the specification and value data at the specified position.
     * Binds the specification data at the given position to the {@link SpecificationViewHolder},
     * and binds the corresponding value data using the provided data source.
     *
     * @param holder The {@link SpecificationViewHolder} to bind the data to.
     * @param position The position of the item within the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull SpecificationViewHolder holder, int position) {
        String specification = specificationList.get(position);
        holder.specificationText.setText(specification);

        String value = valueList.get(position); // Replace with your data source
        holder.valueText.setText(value);
    }

    /**
     * Returns the total number of specification-value pairs in the adapter's data set.
     * If the first specification is empty, it indicates that there is no data to display,
     * and the method returns 0. Otherwise, it returns the minimum of the sizes of the
     * specification and value lists.
     *
     * @return The total number of specification-value pairs in the data set, or 0 if there is no data.
     */
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

        /**
         * Constructor for the ViewHolder used to hold specification-value pairs.
         * Initializes the ViewHolder with the provided item view and associates the specification
         * and value views with the corresponding layout views to be used for displaying data.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public SpecificationViewHolder(@NonNull View itemView) {
            super(itemView);
            specificationText = itemView.findViewById(R.id.specificationText);
            valueText = itemView.findViewById(R.id.specificationValue);
        }
    }
}