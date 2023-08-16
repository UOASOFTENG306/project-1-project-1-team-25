package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

import java.util.HashMap;

public class CPU extends Item {

    private Brand brand;

    private Socket socket;

    private int clockSpeedMHz;

    private int numCores;

    public CPU() {
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

    private int getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    private void setClockSpeedMHz(int clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }

    private int getNumCores() {
        return numCores;
    }

    private void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    public HashMap<String,String> getSpecifications() {
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }

}
