package com.cft.shiftlab.back.shop.models;

/**
 * enum with all available types for laptop value - size
 */
public enum Size {
    INCH_13(13),
    INCH_14(14),
    INCH_15(15),
    INCH_17(17);
    private int size;

    Size(int size) {
        this.size = size;
    }

    /**
     * @return digit - size
     */
    int getSize() {
        return size;
    }
}
