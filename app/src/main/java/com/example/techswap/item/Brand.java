package com.example.techswap.item;

public enum Brand {
    Intel("Intel"), AMD("AMD"), Nvidia("Nvidia");

    private String name;
    private Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
