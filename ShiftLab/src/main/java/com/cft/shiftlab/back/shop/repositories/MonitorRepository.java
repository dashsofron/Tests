package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Monitor;

import java.util.Collection;

/**
 * interface for work with database
 * Monitor class
 */
public interface MonitorRepository {
    /**
     * get product
     *
     * @param id - product id. Use it for searching
     * @return element, type of Monitor
     */
    Monitor fetchMonitor(String id);

    /**
     * get all products of Monitor type
     *
     * @return Collection of elements,type of Monitor
     */
    Collection<Monitor> getAllMonitors();

    /**
     * for updating
     *
     * @param id      - product id. Use it for searching
     * @param product - element with new values
     * @return the updating product, type of Monitor
     */
    Monitor updateProduct(String id, Monitor product);

    /**
     * for adding
     *
     * @param product - new element
     * @return the new product, type of Monitor
     */
    Monitor addProduct(Monitor product);
}
