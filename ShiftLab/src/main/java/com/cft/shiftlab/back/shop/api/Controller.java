package com.cft.shiftlab.back.shop.api;

import com.cft.shiftlab.back.shop.exceptions.FormFactorException;
import com.cft.shiftlab.back.shop.models.*;
import com.cft.shiftlab.back.shop.services.ComputerService;
import com.cft.shiftlab.back.shop.services.HDDService;
import com.cft.shiftlab.back.shop.services.LaptopService;
import com.cft.shiftlab.back.shop.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * handle requests within service classes for every type of product
 */
@RestController
public class Controller {
    final String get = "/get";
    final String change = "/update";
    final String add = "/create";
    final String getAll = "/get/all";
    final String laptop_ = "/api/laptop";
    final String computer_ = "/api/computer";
    final String hdd_ = "/api/hdd";
    final String monitor_ = "/api/monitor";
    private MonitorService mService;
    private LaptopService lService;
    private ComputerService cService;
    private HDDService hService;

    @Autowired
    public Controller(MonitorService mService, LaptopService lService, ComputerService cService, HDDService hService/* AllService service*/) {
        this.mService = mService;
        this.lService = lService;
        this.cService = cService;
        this.hService = hService;

    }

    /**
     * get methods - providing products by id from database to client methods
     *
     * @param productId need it for getting information from database
     *                  Also need the type of product in any way for creating the right ResponseEntity
     * @return product type entity. Depends of product type, so we need information about type in request
     */
    @GetMapping(laptop_ + get + "/{productId}")
    public ResponseEntity<Laptop> getLaptop(
            @PathVariable String productId) {
        Laptop laptop = lService.provideLaptop(productId);
        return ResponseEntity.ok(laptop);
    }

    @GetMapping(computer_ + get + "/{productId}")
    public ResponseEntity<Product> getComputer(
            @PathVariable String productId) {
        Computer computer = cService.provideComputer(productId);
        return ResponseEntity.ok(computer);
    }

    @GetMapping(hdd_ + get + "/{productId}")
    public ResponseEntity<Product> getHDD(
            @PathVariable String productId) {
        HDD hdd = hService.provideHDD(productId);
        return ResponseEntity.ok(hdd);
    }

    @GetMapping(monitor_ + get + "/{productId}")
    public ResponseEntity<Product> getMonitor(
            @PathVariable String productId) {
        Monitor monitor = mService.provideMonitor(productId);
        return ResponseEntity.ok(monitor);
    }

    /**
     * getAll methods - providing all products from category from database to client methods
     * Also need the type of product in any way for creating the right ResponseEntity
     *
     * @return product collection from requested category. Depends of product type, so we need information about type in request
     */
    @GetMapping(laptop_ + getAll)
    public ResponseEntity<Collection<Laptop>> getLaptops(
    ) {
        Collection<Laptop> laptops = lService.provideLaptops();
        return ResponseEntity.ok(laptops);
    }

    @GetMapping(computer_ + getAll)
    public ResponseEntity<Collection<Computer>> getComputers(
    ) {
        Collection<Computer> computers = cService.provideComputers();
        return ResponseEntity.ok(computers);
    }

    @GetMapping(hdd_ + getAll)
    public ResponseEntity<Collection<HDD>> getHDDs(
    ) {
        Collection<HDD> hdds = hService.provideHDDs();
        return ResponseEntity.ok(hdds);
    }

    @GetMapping(monitor_ + getAll)
    public ResponseEntity<Collection<Monitor>> getMonitor(
    ) {
        Collection<Monitor> monitors = mService.provideMonitors();
        return ResponseEntity.ok(monitors);
    }

    /**
     * update methods - change information about particular product by id
     * Also need the type of product in any way for creating the right ResponseEntity
     *
     * @param productId need it for getting information from database
     * @param product   - object with changes, which needs to be got in database
     * @return product type entity for client. Depends of product type, so we need information about type in request
     */
    @PatchMapping(laptop_ + change + "/{productId}")
    public ResponseEntity<Laptop> updateLaptop(
            @PathVariable String productId,
            @RequestBody Laptop product
    ) {
        Laptop updatedProduct = lService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping(computer_ + change + "/{productId}")
    public ResponseEntity<Computer> updateComputer(
            @PathVariable String productId,
            @RequestBody Computer product
    ) {
        Computer updatedProduct = cService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping(hdd_ + change + "/{productId}")
    public ResponseEntity<HDD> updateHDD(
            @PathVariable String productId,
            @RequestBody HDD product
    ) {
        HDD updatedProduct = hService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping(monitor_ + change + "/{productId}")
    public ResponseEntity<Monitor> updateMonitor(
            @PathVariable String productId,
            @RequestBody Monitor product
    ) {
        Monitor updatedProduct = mService.updateProduct(productId, product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * add methods - for adding new products to some database
     * Also need the type of product in any way for creating the right ResponseEntity
     *
     * @param product - object with changes, which needs to be got in database
     * @return product type entity for client. Depends of product type, so we need information about type in request
     */
    @PostMapping(laptop_ + add)

    public ResponseEntity<Laptop> addLaptop(
            @RequestBody Laptop product) {
        Laptop result = lService.addProduct(product);
        return ResponseEntity.ok(result);
    }

    @PostMapping(computer_ + add)

    public ResponseEntity<Computer> addComputer(
            @RequestBody Computer product) throws FormFactorException {
        Computer result = null;
        result = cService.addProduct(product);
        return ResponseEntity.ok(result);
    }

    @PostMapping(hdd_ + add)

    public ResponseEntity<HDD> addHDD(
            @RequestBody HDD product) {
        HDD result = hService.addProduct(product);
        return ResponseEntity.ok(result);
    }

    @PostMapping(monitor_ + add)

    public ResponseEntity<Monitor> addMonitor(
            @RequestBody Monitor product) {
        Monitor result = mService.addProduct(product);
        return ResponseEntity.ok(result);
    }

}


