package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class Motherboard extends Item {

    private String brand;

    private String socket;

    private String ramSlots;

    private String formFactor;

    public Motherboard() {
        Details details = new Details();
        details.setCategory("Motherboard");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private String getSocket() {
        return socket;
    }

    private String getRamSlots() {
        return ramSlots;
    }

    private String getFormFactor() {
        return formFactor;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        socket = specificationsList.get(3);
        ramSlots = specificationsList.get(5);
        formFactor = specificationsList.get(7);
    }

}
