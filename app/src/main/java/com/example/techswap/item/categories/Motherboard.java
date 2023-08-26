package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Motherboard extends Item {

    private String brand;

    private String socket;

    private String ramSlots;

    private String formFactor;

    public Motherboard() {
        this.details.setCategory("Motherboard");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(socket);
        valuesList.add(ramSlots);
        valuesList.add(formFactor);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        socket = valuesList.get(1);
        ramSlots = valuesList.get(2);
        formFactor = valuesList.get(3);
    }

}
