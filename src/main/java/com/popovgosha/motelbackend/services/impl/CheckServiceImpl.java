package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.Check;
import com.popovgosha.motelbackend.repository.CheckRepository;
import com.popovgosha.motelbackend.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckRepository checkRepository;

    @Override
    public List<Check> findAll() {
        return checkRepository.findAll();
    }

    @Override
    public Check findOne(Long id) {
        return checkRepository.findOne(id);
    }

    @Override
    public Check save(Check check) {
        return checkRepository.save(check);
    }

    @Override
    public void delete(Long id) {
        checkRepository.delete(id);
    }
}
