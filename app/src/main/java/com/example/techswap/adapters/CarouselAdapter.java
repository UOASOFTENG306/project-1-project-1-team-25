package com.example.techswap.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techswap.R;
import com.example.techswap.database.Database;
import com.example.techswap.fragments.DetailsFragment;
import com.example.techswap.fragments.ListFragment;
import com.example.techswap.interfaces.ICarouselAdapter;
import com.example.techswap.interfaces.IDatabase;
import com.example.techswap.item.Item;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICarouselAdapter {

    private final CarouselType carouselType;
    IDatabase db = new Database();
    private List<String> imageUrlList;
    private List<String> titleList;
    private List<Double> priceList;
    private List<String> subtitleList;
    private List<Item> itemList;
    private Context context;
    private AdapterCallback callback;

    public CarouselAdapter(CarouselType carouselType, AdapterCallback callback) {
        this.imageUrlList = new ArrayList<>();
        this.titleList = new ArrayList<>();
        this.subtitleList = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.carouselType = carouselType;
        this.callback = callback;
    }

    public CarouselAdapter(CarouselType carouselType) {
        this.imageUrlList = new ArrayList<>();
        this.titleList = new ArrayList<>();
        this.subtitleList = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.carouselType = carouselType;
    }

    public CarouselAdapter(List<String> imageUrlList, List<String> titleList, List<Double> priceList, List<String> subtitleList, List<Item> itemList, CarouselType carouselType) {
        this.imageUrlList = imageUrlList;
        this.titleList = titleList;
        this.subtitleList = subtitleList;
        this.priceList = priceList;
        this.itemList = itemList;
        this.carouselType = carouselType;
    }

    /**
     * Called when RecyclerView needs a new {@link RecyclerView.ViewHolder} of the given type to represent
     * an item.
     * This method creates and returns a new ViewHolder based on the specified view type. It inflates the
     * appropriate layout for the item view based on the carousel type, calculates item width, and adjusts
     * the layout parameters accordingly to achieve the desired appearance for the carousel item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds an item view of the given view type.
     * @throws IllegalArgumentException if the provided view type is not recognized or supported.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DisplayMetrics displayMetrics = parent.getContext().getResources().getDisplayMetrics();
        View view;

        int screenWidth = displayMetrics.widthPixels;
        switch (carouselType) {
            case CATEGORY:
                view = inflater.inflate(R.layout.adapter_item_category, parent, false);
                int numberOfItemsVisible = 3; // Display two items at a time

                // Calculate the total padding to be used on both sides of the item
                int horizontalPadding = view.getResources().getDimensionPixelSize(R.dimen.item_horizontal_padding);

                // Calculate the item width by considering padding and dividing by the number of items
                int itemWidth = (screenWidth - horizontalPadding * (numberOfItemsVisible - 1)) / numberOfItemsVisible;


                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = itemWidth;
                view.setLayoutParams(layoutParams);

                return new CarouselViewHolderCategory(view);
            case HORIZONTAL_ITEM:
                view = inflater.inflate(R.layout.adapter_item_deals, parent, false);
                int numberOfItemsVisible2 = 2; // Display two items at a time

                // Calculate the total padding to be used on both sides of the item
                int horizontalPadding2 = view.getResources().getDimensionPixelSize(R.dimen.item_horizontal_padding);

                // Calculate the item width by considering padding and dividing by the number of items
                int itemWidth2 = (screenWidth - horizontalPadding2 * (numberOfItemsVisible2 - 1)) / numberOfItemsVisible2;


                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                layoutParams2.width = itemWidth2;
                view.setLayoutParams(layoutParams2);

                return new CarouselViewHolderHorizontalItem(view);
            case LIST_ITEM:
                view = inflater.inflate(R.layout.adapter_item_list, parent, false);
                return new CarouselViewHolderListItem(view);

            case LARGE_LIST_ITEM:
                view = inflater.inflate(R.layout.adapter_item_large_list, parent, false);
                return new CarouselViewHolderListItem(view);

            case CART_ITEM:
                view = inflater.inflate(R.layout.adapter_item_cart, parent, false);
                return new CarouselViewHolderCartItem(view);

            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method binds the data to the given ViewHolder based on the carousel type.
     * It populates the ViewHolder's views with appropriate data such as titles, prices,
     * and images. Depending on the carousel type, it also sets click listeners for various
     * actions, including navigation to detailed views and handling cart item removal.
     *
     * @param holder   The ViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String url = imageUrlList.get(position);
        String priceString;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);

        if (carouselType == CarouselType.CATEGORY) {
            CarouselViewHolderCategory viewHolder = (CarouselViewHolderCategory) holder;
            viewHolder.titleText.setText(titleList.get(position));

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.HORIZONTAL_ITEM) {
            CarouselViewHolderHorizontalItem viewHolder = (CarouselViewHolderHorizontalItem) holder;
            viewHolder.titleText.setText(titleList.get(position));

            priceString = "$" + df.format(priceList.get(position));
            viewHolder.priceText.setText(priceString);

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.LIST_ITEM || carouselType == CarouselType.LARGE_LIST_ITEM) {
            CarouselViewHolderListItem viewHolder = (CarouselViewHolderListItem) holder;
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.subtitleText.setText(subtitleList.get(position));

            priceString = "$" + df.format(priceList.get(position));
            viewHolder.priceText.setText(priceString);

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.CART_ITEM) {
            CarouselViewHolderCartItem viewHolder = (CarouselViewHolderCartItem) holder;
            viewHolder.titleText.setText(titleList.get(position));

            priceString = "$" + df.format(priceList.get(position));
            viewHolder.priceText.setText(priceString);

            Glide.with(context).load(url).into(viewHolder.carouselImage);

            viewHolder.removeFromCartButton.setOnClickListener(v -> {
                int clickedPosition = viewHolder.getAdapterPosition();
                db.addRemoveItemToCart(itemList.get(clickedPosition).getId(), false);
                itemList.remove(clickedPosition);
                if (callback != null) {
                    callback.onAdapterItemClick(itemList);
                }
            });
        }

        // Set OnClickListener for the item view
        holder.itemView.setOnClickListener(v -> {
            int clickedPosition = holder.getAdapterPosition();
            // Handle item click event
            if (carouselType == CarouselType.CATEGORY) {
                ListFragment fragment;
                switch (clickedPosition) {
                    case 0:
                        fragment = ListFragment.listCategory("CPU");
                        break;
                    case 1:
                        fragment = ListFragment.listCategory("GPU");
                        break;
                    case 2:
                        fragment = ListFragment.listCategory("Motherboard");
                        break;
                    case 3:
                        fragment = ListFragment.listCategory("Storage");
                        break;
                    case 4:
                        fragment = ListFragment.listCategory("Memory");
                        break;
                    case 5:
                        fragment = ListFragment.listCategory("Power");
                        break;
                    case 6:
                        fragment = ListFragment.listCategory("Case");
                        break;
                    default:
                        fragment = ListFragment.listCategory("Other");
                        break;
                }

                FragmentTransaction transaction = ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction();

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                transaction.replace(R.id.mainFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            } else if (carouselType == CarouselType.HORIZONTAL_ITEM || carouselType == CarouselType.LIST_ITEM || carouselType == CarouselType.LARGE_LIST_ITEM) {
                DetailsFragment fragment = DetailsFragment.newInstance(itemList.get(clickedPosition));
                FragmentTransaction transaction = ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction();

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                transaction.replace(R.id.mainFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    /**
     * Returns the total number of items in the adapter's data set.
     * This method provides the count of items that will be displayed within the RecyclerView.
     *
     * @return The total number of items in the adapter's data set.
     */
    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    /**
     * Updates the data displayed in the adapter and triggers a data set change notification.
     * This method takes a list of items and extracts relevant information such as titles, subtitles,
     * prices, and image URLs to update the data displayed in the adapter. After updating the data, it
     * calls {@link #notifyDataSetChanged()} to inform the RecyclerView to refresh its view with the new data.
     *
     * @param items The list of items containing the updated data to be displayed in the adapter.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<Item> items) {
        List<String> titleList = new ArrayList<>();
        List<String> subtitleList = new ArrayList<>();
        List<Double> priceList = new ArrayList<>();
        List<String> imageUrlList = new ArrayList<>();

        // Update each item with details
        for (Item item : items) {
            titleList.add(item.getDetails().getTitle());
            subtitleList.add(item.getDetails().getSubtitle());
            priceList.add(item.getDetails().getPrice());
            imageUrlList.add(item.getFirstImageUrl());
        }

        // Update data in carousel
        this.titleList = titleList;
        this.priceList = priceList;
        this.subtitleList = subtitleList;
        this.imageUrlList = imageUrlList;
        this.itemList = items;

        notifyDataSetChanged();
    }

    /**
     * Sets the context for the adapter.
     * This method allows setting the context to be used for various purposes within the adapter,
     * such as accessing resources or services associated with the provided context.
     *
     * @param context The context to be set for the adapter.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    public enum CarouselType {
        CATEGORY, HORIZONTAL_ITEM, LIST_ITEM, LARGE_LIST_ITEM, CART_ITEM
    }

    public interface AdapterCallback {

        /**
         * Callback method triggered when an item within the adapter is clicked.
         * This method is intended to be implemented by classes that utilize the adapter to handle
         * item click events. It receives a list of items that reflects the updated state after
         * an item has been interacted with.
         *
         * @param items The list of items reflecting the updated state after an item has been clicked.
         */
        void onAdapterItemClick(List<Item> items);
    }

    public static class CarouselViewHolderCategory extends RecyclerView.ViewHolder {
        final ImageView carouselImage;
        final TextView titleText;

        /**
         * Constructor for the ViewHolder used in the Category carousel type.
         * This constructor initializes the views within the ViewHolder for displaying items of the
         * Category carousel type. It associates the layout views with the corresponding variables to
         * be used for populating data and handling interactions.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public CarouselViewHolderCategory(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
        }
    }

    public static class CarouselViewHolderHorizontalItem extends RecyclerView.ViewHolder {
        final ImageView carouselImage;
        final TextView titleText;
        final TextView priceText;

        /**
         * Constructor for the ViewHolder used in the Horizontal Item carousel type.
         * This constructor initializes the views within the ViewHolder for displaying items of the
         * Horizontal Item carousel type. It associates the layout views with the corresponding variables
         * to be used for populating data and handling interactions.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public CarouselViewHolderHorizontalItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
        }
    }

    public static class CarouselViewHolderListItem extends RecyclerView.ViewHolder {
        final ImageView carouselImage;
        final TextView titleText;
        final TextView subtitleText;
        final TextView priceText;

        /**
         * Constructor for the ViewHolder used in the List Item carousel type.
         * This constructor initializes the views within the ViewHolder for displaying items of the
         * List Item carousel type. It associates the layout views with the corresponding variables
         * to be used for populating data and handling interactions.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public CarouselViewHolderListItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
            subtitleText = itemView.findViewById(R.id.descriptionText);
        }
    }

    public static class CarouselViewHolderCartItem extends RecyclerView.ViewHolder {
        final ImageView carouselImage;
        final TextView titleText;
        final TextView priceText;
        final Button removeFromCartButton;

        /**
         * Constructor for the ViewHolder used in the Cart Item carousel type.
         * This constructor initializes the views within the ViewHolder for displaying items of the
         * List Item carousel type. It associates the layout views with the corresponding variables
         * to be used for populating data and handling interactions.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public CarouselViewHolderCartItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
            priceText = itemView.findViewById(R.id.itemPrice);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
        }
    }
}
