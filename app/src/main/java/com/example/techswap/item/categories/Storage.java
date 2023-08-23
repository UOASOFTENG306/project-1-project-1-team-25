package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

import java.util.HashMap;

public class Storage extends Item {

    private Brand brand;

    private boolean isSolidState;

    private int capacityGB;

    public Storage() {
        Details details = new Details();
        details.setCategory("Storage");
        this.setDetails(details);
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private boolean isSolidState() {
        return isSolidState;
    }

    private void setSolidState(boolean solidState) {
        isSolidState = solidState;
    }

    private int getCapacityGB() {
        return capacityGB;
    }

    private void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    public HashMap<String,String> getSpecifications() {
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());
        specifications.put("Solid state", String.valueOf(isSolidState));
        specifications.put("Capacity", String.valueOf(capacityGB));

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
        isSolidState = Boolean.parseBoolean(specifications.get("Solid state"));
        capacityGB = Integer.parseInt(specifications.get("Capacity"));
    }

}
