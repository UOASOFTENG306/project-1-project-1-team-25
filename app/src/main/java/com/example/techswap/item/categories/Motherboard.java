package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

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

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String getSocket() {
        return socket;
    }

    private void setSocket(String socket) {
        this.socket = socket;
    }

    private String getRamSlots() {
        return ramSlots;
    }

    private void setRamSlots(String ramSlots) {
        this.ramSlots = ramSlots;
    }

    private String getFormFactor() {
        return formFactor;
    }

    private void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

}
