package com.cft.shiftlab.back.shop.models;

/**
 * some type of product
 * particular information - volume
 */
public class HDD extends Product {
    private int volume;//TBs

    public HDD(String id, String serialNum, String producer, int price, int amount, int volume) {
        setId(id);
        setSeriulNum(serialNum);
        setProducer(producer);
        setPrice(price);
        setAmount(amount);
        setVolume(volume);
    }

    public HDD() {

    }

    /**
     * volume getter
     *
     * @return the volume value,digit
     */
    public int getVolume() {
        return volume;
    }

    /**
     * volume setter
     *
     * @param volume - volume value for this product
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }
}