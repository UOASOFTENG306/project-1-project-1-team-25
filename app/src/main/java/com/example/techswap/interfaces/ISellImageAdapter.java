package com.example.techswap.interfaces;

import android.content.Context;

import java.util.List;

public interface ISellImageAdapter {

    void setContext(Context context);

    void updateImages(List<String> imageUrlList);

}
