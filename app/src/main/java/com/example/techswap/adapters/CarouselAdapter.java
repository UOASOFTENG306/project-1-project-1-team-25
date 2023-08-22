package com.example.techswap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techswap.fragments.DetailsFragment;
import com.example.techswap.fragments.ListFragment;
import com.example.techswap.R;
import com.example.techswap.item.Item;

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
        CATEGORY, HORIZONTAL_ITEM, LIST_ITEM, CART_ITEM
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
        switch (carouselType) {
            case CATEGORY:
                View categoryView = inflater.inflate(R.layout.carousel_item_category, parent, false);
                return new CarouselViewHolderCategory(categoryView);
            case HORIZONTAL_ITEM:
                View horizontalItemView = inflater.inflate(R.layout.carousel_item_deal, parent, false);
                return new CarouselViewHolderHorizontalItem(horizontalItemView);
            case LIST_ITEM:
                View listItemView = inflater.inflate(R.layout.carousel_item_best_seller, parent, false);
                return new CarouselViewHolderListItem(listItemView);
            case CART_ITEM:
                View cartItemView = inflater.inflate(R.layout.item_card, parent, false);
                return new CarouselViewHolderCartItem(cartItemView);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String url = imageUrlList.get(position);

        if (carouselType == CarouselType.CATEGORY) {
            CarouselViewHolderCategory viewHolder = (CarouselViewHolderCategory) holder;
            viewHolder.titleText.setText(titleList.get(position));

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.HORIZONTAL_ITEM) {
            CarouselViewHolderHorizontalItem viewHolder = (CarouselViewHolderHorizontalItem) holder;
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.LIST_ITEM) {
            CarouselViewHolderListItem viewHolder = (CarouselViewHolderListItem) holder;
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.subtitleText.setText(subtitleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());

            Glide.with(context).load(url).into(viewHolder.carouselImage);

        } else if (carouselType == CarouselType.CART_ITEM) {
            CarouselViewHolderCartItem viewHolder = (CarouselViewHolderCartItem) holder;
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());

            Glide.with(context).load(url).into(viewHolder.carouselImage);

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



                } else if (carouselType == CarouselType.HORIZONTAL_ITEM || carouselType == CarouselType.LIST_ITEM) {
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

        public CarouselViewHolderCartItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
            priceText = itemView.findViewById(R.id.itemPrice);
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
            imageUrlList.add("https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90");
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
}
