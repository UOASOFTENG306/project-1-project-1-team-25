package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Other extends Item {

    private Brand brand;

    public Other() {
        Details details = new Details();
        details.setCategory("Other");
        this.setDetails(details);
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    public HashMap<String,String> getSpecifications() {
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
    }
}
