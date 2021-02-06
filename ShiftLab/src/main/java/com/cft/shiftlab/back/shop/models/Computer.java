package com.cft.shiftlab.back.shop.models;

/**
 * some type of product
 * particular information - form factor
 */
public class Computer extends Product {
    private String formFactor;

    public Computer(String id, String serialNum, String producer, int price, int amount, FormFactor formFactor) {
        setId(id);
        setSeriulNum(serialNum);
        setProducer(producer);
        setPrice(price);
        setAmount(amount);
        setFormFactor(formFactor.toString());
    }

    public Computer() {

    }

    /**
     * form factor getter
     *
     * @return the form factor value, type of string
     */
    public String getFormFactor() {
        return formFactor;
    }

    /**
     * form factor setter
     *
     * @param formFactor - form factor value for this product
     */
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * method for checking form factor value. There are in FormFactor enum all available values for product form factor value.
     *
     * @param formFactor - string that should be checked
     * @return boolean value. If string is available - true, otherwise - false.
     */
    public static boolean enumContains(String formFactor) {

        for (FormFactor f : FormFactor.values()) {
            if (f.name().equals(formFactor)) {
                return true;
            }
        }

        return false;
    }
}
