package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.Service;
import com.popovgosha.motelbackend.repository.ServiceRepository;
import com.popovgosha.motelbackend.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service findOne(Integer id) {
        return serviceRepository.findOne(id);
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void delete(Integer id) {
        serviceRepository.delete(id);
    }
}
