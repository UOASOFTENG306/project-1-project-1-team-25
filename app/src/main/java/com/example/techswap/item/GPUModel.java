package com.example.techswap.item;

public enum GPUModel {
    RTX_4090("RTX 4090"), RTX_4080("RTX 4080"), RTX_4070("RTX 4070"),
    RTX_4070_Ti("RTX 4070 Ti"), RTX_4060("RTX 4060"), RTX_4060_Ti("RTX 4060 Ti"),
    RTX_3090_Ti("RTX 3090 Ti"), RTX_3090("RTX 3090"), RTX_3080_Ti("RTX 3080 Ti"),
    RTX_3080("RTX 3080"), RTX_3070_Ti("RTX 3070 Ti"), RTX_3070("RTX 3070"),

    RX_7900_XTX("RX 7900 XTX"), RX_7900_XT("RX 7900 XT"), RX_7600("RX 7600"),
    RX_6950_XT("RX 6950 XT"), RX_6900_XT("RX 6900 XT"), RX_6800_XT("RX 6800 XT"),
    RX_6800("RX 6800"), RX_6750_XT("RX 6750 XT"), RX_6700_XT("RX 6700 XT"),
    RX_6700("RX 6700"), RX_6650_XT("RX 6650 XT"), RX_6600_XT("RX 6600 XT"),
    RX_6600("RX 6600");

    private String name;
    private GPUModel(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
