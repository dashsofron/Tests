package com.cft.shiftlab.back.shop.services;

import com.cft.shiftlab.back.shop.exceptions.FormFactorException;
import com.cft.shiftlab.back.shop.models.Computer;
import com.cft.shiftlab.back.shop.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ComputerService {
    private final ComputerRepository cRepository;
    @Autowired
    public ComputerService(ComputerRepository cRepository) {
        this.cRepository = cRepository;
    }

    public Computer provideComputer(String id) {
        return cRepository.fetchComputer(id);
    }

    public Collection<Computer> provideComputers() {
        return cRepository.getAllComputers();
    }

    public Computer updateProduct(String id, Computer product) {
        return cRepository.updateProduct(id, product);
    }
    public Computer addProduct(Computer product) throws FormFactorException {
        return cRepository.addProduct(product);
    }
}


