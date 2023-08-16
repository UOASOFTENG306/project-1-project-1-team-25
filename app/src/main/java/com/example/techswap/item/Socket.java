package com.example.techswap.item;

public enum Socket {
    LGA_1700("LGA 1700"), LGA_1200("LGA 1200"), AM5("AM5"), AM4("AM4");

    private String name;
    private Socket(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
