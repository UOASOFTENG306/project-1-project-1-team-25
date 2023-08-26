package com.example.techswap.interfaces;

import android.content.Context;
import com.example.techswap.item.Item;

import java.util.List;

public interface ICarouselAdapter {

    void updateData(List<Item> items);

    void setContext(Context context);
}
