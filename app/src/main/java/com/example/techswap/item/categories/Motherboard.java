package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.FormFactor;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

import java.util.HashMap;

public class Motherboard extends Item {

    private Brand brand;

    private Socket socket;

    private int ramSlots;

    private FormFactor formFactor;

    public Motherboard() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private Socket getSocket() {
        return socket;
    }

    private void setSocket(Socket socket) {
        this.socket = socket;
    }

    private int getRamSlots() {
        return ramSlots;
    }

    private void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    private FormFactor getFormFactor() {
        return formFactor;
    }

    private void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public HashMap<String,String> getSpecifications() {
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }

}