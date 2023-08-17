package com.example.techswap.item;

public enum FormFactor {
    iTX("iTX"), mATX("mATX"), ATX("ATX"), eATX("eATX");

    private String name;
    private FormFactor(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
