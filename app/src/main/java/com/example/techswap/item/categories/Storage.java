package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Storage extends Item {

    private Brand brand;

    private boolean isSolidState;

    private int capacityGB;

    public Storage() {
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
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }

}
