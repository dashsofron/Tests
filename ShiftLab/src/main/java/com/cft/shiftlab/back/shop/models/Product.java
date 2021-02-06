package com.cft.shiftlab.back.shop.models;

public class Product {
    private String serialNumber;
    private String id;
    private String producer;
    private int price;//rubles
    private int amount;//units

    /**
     * serial number getter
     *
     * @return the serial number value, type of String
     */
    public String getSeriulNum() {
        return serialNumber;
    }

    /**
     * price getter
     *
     * @return the price value, digit
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * amount getter
     *
     * @return the amount value, digit
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * producer getter
     *
     * @return the producer value, type of String
     */
    public String getProducer() {
        return producer;
    }

    /**
     * id getter
     *
     * @return the id value, type of String
     */
    public String getId() {
        return id;
    }

    /**
     * id setter
     *
     * @param id - id value for this product
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * serial number setter
     *
     * @param serialNumber - serial number value for this product
     */
    public void setSeriulNum(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * price setter
     *
     * @param price - price value for this product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * amount setter
     *
     * @param amount - amount value for this product
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * amount setter
     *
     * @param producer - amount value for this product
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }
}
