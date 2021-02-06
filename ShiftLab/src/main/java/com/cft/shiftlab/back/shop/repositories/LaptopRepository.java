package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Laptop;

import java.util.Collection;

public interface LaptopRepository {
    /**
     * get product
     *
     * @param id use it for searching
     * @return element, type of Laptop
     */
    Laptop fetchLaptop(String id);

    /**
     * get all products of Monitor type
     *
     * @return Collection of elements,type of Laptop
     */
    Collection<Laptop> getAllLaptops();

    /**
     * for updating
     *
     * @param id      use it for searching
     * @param product - element with new values
     * @return the updating product, type of Laptop
     */
    Laptop updateProduct(String id, Laptop product);

    /**
     * for adding
     *
     * @param product - new element
     * @return the new product, type of Laptop
     */
    Laptop addProduct(Laptop product);

}
