package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.HDD;

import java.util.Collection;

public interface HDDRepository {
    /**
     * get product
     *
     * @param id use it for searching
     * @return element, type of HDD
     */
    HDD fetchHDD(String id);

    /**
     * get all products of Monitor type
     *
     * @return Collection of elements,type of HDD
     */
    Collection<HDD> getAllHDDs();

    /**
     * for updating
     *
     * @param id  use it for searching
     * @param product - element with new values
     * @return the updating product, type of HDD
     */
    HDD updateProduct(String id, HDD product);

    /**
     * for adding
     *
     * @param product - new element
     * @return the new product, type of HDD
     */
    HDD addProduct(HDD product);

}
