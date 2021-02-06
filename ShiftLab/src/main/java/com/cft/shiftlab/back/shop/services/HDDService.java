package com.cft.shiftlab.back.shop.services;

import com.cft.shiftlab.back.shop.models.HDD;
import com.cft.shiftlab.back.shop.repositories.HDDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class HDDService {
    private final HDDRepository hRepository;

    @Autowired
    public HDDService(HDDRepository hRepository) {
        this.hRepository = hRepository;
    }

    public HDD provideHDD(String id) {
        return hRepository.fetchHDD(id);
    }

    public Collection<HDD> provideHDDs() {
        return hRepository.getAllHDDs();
    }

    public HDD updateProduct(String id, HDD product) {
        return hRepository.updateProduct(id, product);
    }

    public HDD addProduct(HDD product) {
        return hRepository.addProduct(product);
    }

}
