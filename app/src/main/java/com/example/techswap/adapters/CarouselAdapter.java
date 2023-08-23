package com.example.techswap.adapters;

import android.util.DisplayMetrics;
import android.content.Context;
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
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.fragments.DetailsFragment;
import com.example.techswap.fragments.ListFragment;
import com.example.techswap.R;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> imageUrlList;
    private List<String> titleList;
    private List<Double> priceList;
    private List<String> subtitleList;
    private List<Item> itemList;

    private Context context;
    private final CarouselType carouselType;

    public enum CarouselType {
        CATEGORY, HORIZONTAL_ITEM, LIST_ITEM, LARGE_LIST_ITEM, CART_ITEM
    }
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DisplayMetrics displayMetrics = parent.getContext().getResources().getDisplayMetrics();
        View view;

        int screenWidth = displayMetrics.widthPixels;
        switch (carouselType) {
            case CATEGORY:
                view = inflater.inflate(R.layout.carousel_item_category, parent, false);
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
                view = inflater.inflate(R.layout.carousel_item_deal, parent, false);
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
                view = inflater.inflate(R.layout.carousel_item_list, parent, false);
                return new CarouselViewHolderListItem(view);

            case LARGE_LIST_ITEM:
                view = inflater.inflate(R.layout.carousel_item_large_list, parent, false);
                return new CarouselViewHolderListItem(view);

            case CART_ITEM:
                view = inflater.inflate(R.layout.cart_item, parent, false);
                return new CarouselViewHolderCartItem(view);

            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }



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

            viewHolder.removeFromCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = viewHolder.getAdapterPosition();
                    DatabaseSetter db = new DatabaseSetter();
                    db.addRemoveItemToCart(User.getCurrentUser().getUsername(), itemList.get(clickedPosition).getId().toString(), false);
                    itemList.remove(clickedPosition);
                    if (callback != null) {
                        callback.onAdapterItemClick(itemList);
                    }
                }
            });
        }

        // Set OnClickListener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                // Handle item click event
                if (carouselType == CarouselType.CATEGORY) {
                    ListFragment fragment;
                    switch (clickedPosition){
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
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public class CarouselViewHolderCategory extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;

        public CarouselViewHolderCategory(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
        }
    }

    public class CarouselViewHolderHorizontalItem extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;
        TextView priceText;

        public CarouselViewHolderHorizontalItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
        }
    }

    public class CarouselViewHolderListItem extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;
        TextView subtitleText;
        TextView priceText;

        public CarouselViewHolderListItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
            subtitleText = itemView.findViewById(R.id.descriptionText);
        }
    }

    public class CarouselViewHolderCartItem extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;
        TextView priceText;
        Button removeFromCartButton;

        public CarouselViewHolderCartItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
            priceText = itemView.findViewById(R.id.itemPrice);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
        }
    }

    public void updateData(List<Item> items) {
        List<String> titleList = new ArrayList<String>();
        List<String> subtitleList = new ArrayList<String>();
        List<Double> priceList = new ArrayList<Double>();
        List<String> imageUrlList = new ArrayList<String>();

        for (Item item : items) {
            titleList.add(item.getDetails().getTitle());
            subtitleList.add(item.getDetails().getSubtitle());
            priceList.add(item.getDetails().getPrice());
            imageUrlList.add(item.getFirstImageUrl());
        }

        this.titleList = titleList;
        this.priceList = priceList;
        this.subtitleList = subtitleList;
        this.imageUrlList = imageUrlList;
        this.itemList = items;

        notifyDataSetChanged();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public interface AdapterCallback {
        void onAdapterItemClick(List<Item> items);
    }
}
