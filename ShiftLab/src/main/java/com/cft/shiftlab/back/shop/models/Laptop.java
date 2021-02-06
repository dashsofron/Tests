package com.cft.shiftlab.back.shop.models;

/**
 * some type of product
 * particular information - size
 */
public class Laptop extends Product {
    private int size;//inches

    public Laptop(String id, String serialNum, String producer, int price, int amount, Size size) {
        setId(id);
        setSeriulNum(serialNum);
        setProducer(producer);
        setPrice(price);
        setAmount(amount);
        setSize(size.getSize());
    }

    public Laptop() {

    }

    /**
     * size getter
     *
     * @return the size value, digit
     */
    public int getSize() {

        return size;
    }

    /**
     * size setter
     *
     * @param size - size value for this product
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * method for checking size value. There are in Size enum all available values for product size value.
     *
     * @param size - digit that should be checked
     * @return boolean value. If digit is available - true, otherwise - false.
     */
    public static boolean enumContains(int size) {

        for (Size s : Size.values()) {
            if (s.getSize() == size) {
                return true;
            }
        }

        return false;
    }

}
