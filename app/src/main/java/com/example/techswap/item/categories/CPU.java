package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class CPU extends Item {

    private String brand;

    private String socket;

    private String clockSpeedMHz;

    private String numCores;

    public CPU() {
        Details details = new Details();
        details.setCategory("CPU");
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

    private String getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    private void setClockSpeedMHz(String clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }

    private String getNumCores() {
        return numCores;
    }

    private void setNumCores(String numCores) {
        this.numCores = numCores;
    }

}
