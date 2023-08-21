package com.example.techswap.database;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.ItemFactory;

import java.util.Map;

public class DatabaseUtils {
    public Item mapToItem(Map<String, Object> data) {
        ItemFactory itemFactory = new ItemFactory();
        Details details = new Details();

        details.setCategory(data.get("category_id").toString());
        details.setPrice(Double.parseDouble(data.get("price").toString()));
        details.setDescription(data.get("description").toString());
        details.setQuantity(Integer.parseInt(data.get("quantity").toString()));
        details.setTitle(data.get("title").toString());
        details.setSubtitle(data.get("subtitle").toString());
        details.setSearchTitle(data.get("search_title").toString());

        Item item = itemFactory.getItem(data.get("category_id").toString());
        item.setDetails(details);
        item.setId(Long.parseLong(data.get("item_id").toString()));

        return item;
    }
}