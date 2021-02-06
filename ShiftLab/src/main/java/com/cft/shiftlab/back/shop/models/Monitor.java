package com.cft.shiftlab.back.shop.models;

/**
 * some type of product
 * particular information - diagonal
 */
public class Monitor extends Product {
    private double diagonal;//inches

    public Monitor(String id, String serialNum, String producer, int price, int amount, double diagonal) {
        setId(id);
        setSeriulNum(serialNum);
        setProducer(producer);
        setPrice(price);
        setAmount(amount);
        setDiagonal(diagonal);
    }

    public Monitor() {

    }

    /**
     * diagonal getter
     *
     * @return the diagonal value, type of Double
     */
    public Double getDiagonal() {
        return diagonal;
    }

    /**
     * diagonal setter
     *
     * @param diagonal - diagonal value for this product
     */
    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

}

