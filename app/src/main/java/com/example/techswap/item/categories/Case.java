package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.FormFactor;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Case extends Item {

    private Brand brand;

    private FormFactor formFactor;

    public Case() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private FormFactor getFormFactor() {
        return formFactor;
    }

    private void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public HashMap<String,String> getSpecifications() {
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());
        specifications.put("Form factor", formFactor.toString());

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
        formFactor = FormFactor.valueOf(specifications.get("Form factor"));
    }
}
