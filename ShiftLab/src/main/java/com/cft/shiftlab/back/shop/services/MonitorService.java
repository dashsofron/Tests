package com.cft.shiftlab.back.shop.services;

import com.cft.shiftlab.back.shop.models.Computer;
import com.cft.shiftlab.back.shop.models.HDD;
import com.cft.shiftlab.back.shop.models.Laptop;
import com.cft.shiftlab.back.shop.models.Monitor;
import com.cft.shiftlab.back.shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MonitorService {

    private final MonitorRepository mRepository;


    @Autowired
    public MonitorService(MonitorRepository mRepository) {

        this.mRepository = mRepository;

    }


    public Monitor provideMonitor(String id) {
        return mRepository.fetchMonitor(id);
    }


    public Collection<Monitor> provideMonitors() {
        return mRepository.getAllMonitors();
    }


    public Monitor updateProduct(String id, Monitor product) {
        return mRepository.updateProduct(id, product);
    }


    public Monitor addProduct(Monitor product) {
        return mRepository.addProduct(product);
    }


}
