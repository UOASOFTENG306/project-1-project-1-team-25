package com.example.techswap.database;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.ItemFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DatabaseUtils {
    @SuppressWarnings("unchecked")
    public Item mapToItem(Map<String, Object> data) {
        ItemFactory itemFactory = new ItemFactory();
        Details details = new Details();

        String category, description, title, subtitle, searchTitle, id;
        double price;

        category = Objects.requireNonNull(data.get("category_id")).toString();
        price = Double.parseDouble(Objects.requireNonNull(data.get("price")).toString());
        description = Objects.requireNonNull(data.get("description")).toString();
        title = Objects.requireNonNull(data.get("title")).toString();
        subtitle = Objects.requireNonNull(data.get("subtitle")).toString();
        searchTitle = Objects.requireNonNull(data.get("search_title")).toString();
        id = Objects.requireNonNull(data.get("item_id")).toString();

        details.setCategory(category);
        details.setPrice(price);
        details.setDescription(description);
        details.setTitle(title);
        details.setSubtitle(subtitle);
        details.setSearchTitle(searchTitle);

        Item item = itemFactory.getItem(category);
        item.setDetails(details);
        item.setId(id);
        item.setImageUrls((List<String>) data.get("images"));
        item.setSpecifications((List<String>) data.get("specifications"));
        item.setSpecificationsTitleList((List<String>) data.get("specifications_id"));

        return item;
    }
}
