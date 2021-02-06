package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.exceptions.FormFactorException;
import com.cft.shiftlab.back.shop.models.Computer;

import java.util.Collection;

public interface ComputerRepository {
    /**
     * get product
     *
     * @param id use it for searching
     * @return element, type of Computer
     */
    Computer fetchComputer(String id);

    /**
     * get all products of Monitor type
     *
     * @return Collection of elements,type of Computer
     */
    Collection<Computer> getAllComputers();

    /**
     * for updating
     *
     * @param id      use it for searching
     * @param product - element with new values
     * @return the updating product, type of Computer
     */
    Computer updateProduct(String id, Computer product);

    /**
     * for adding
     *
     * @param product - new element
     * @return the new product, type of Computer
     */
    Computer addProduct(Computer product) throws FormFactorException;

}
