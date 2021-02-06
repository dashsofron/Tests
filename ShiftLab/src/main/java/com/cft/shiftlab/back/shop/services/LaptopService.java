package com.cft.shiftlab.back.shop.services;

import com.cft.shiftlab.back.shop.models.Laptop;
import com.cft.shiftlab.back.shop.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class LaptopService {
    private final LaptopRepository lRepository;
    @Autowired
    public LaptopService(LaptopRepository lRepository) {
        this.lRepository = lRepository;
    }
    public Laptop provideLaptop(String id) {
        return lRepository.fetchLaptop(id);
    }
    public Collection<Laptop> provideLaptops() {
        return lRepository.getAllLaptops();
    }
    public Laptop updateProduct(String id, Laptop product) {
        return lRepository.updateProduct(id, product);
    }
    public Laptop addProduct(Laptop product) {
        return lRepository.addProduct(product);
    }
}
