package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.ServiceCheck;
import com.popovgosha.motelbackend.repository.ServiceCheckRepository;
import com.popovgosha.motelbackend.services.ServiceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Service
public class ServiceCheckServiceImpl implements ServiceCheckService {

    @Autowired
    private ServiceCheckRepository serviceCheckRepository;

    @Override
    public List<ServiceCheck> findAll() {
        return serviceCheckRepository.findAll();
    }

    @Override
    public ServiceCheck findOne(Long id) {
        return serviceCheckRepository.findOne(id);
    }

    @Override
    public ServiceCheck save(ServiceCheck serviceCheck) {
        return serviceCheckRepository.save(serviceCheck);
    }

    @Override
    public void delete(Long id) {
        serviceCheckRepository.delete(id);
    }
}
