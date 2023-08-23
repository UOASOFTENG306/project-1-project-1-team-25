package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Storage extends Item {

    private String brand;

    private String isSolidState;

    private String capacityGB;

    public Storage() {
        Details details = new Details();
        details.setCategory("Storage");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String isSolidState() {
        return isSolidState;
    }

    private void setSolidState(String solidState) {
        isSolidState = solidState;
    }

    private String getCapacityGB() {
        return capacityGB;
    }

    private void setCapacityGB(String capacityGB) {
        this.capacityGB = capacityGB;
    }

}
