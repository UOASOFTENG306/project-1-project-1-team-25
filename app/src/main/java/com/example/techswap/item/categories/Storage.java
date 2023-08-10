package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;

public class Storage extends Item {

    private Brand brand;

    private boolean isSolidState;

    private int capacityGB;

    public Storage() {
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isSolidState() {
        return isSolidState;
    }

    public void setSolidState(boolean solidState) {
        isSolidState = solidState;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

}
