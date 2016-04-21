package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.RoomAccounting;
import com.popovgosha.motelbackend.repository.RoomAccountingRepository;
import com.popovgosha.motelbackend.services.RoomAccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Service
public class RoomAccountingServiceImpl implements RoomAccountingService {

    @Autowired
    private RoomAccountingRepository roomAccountingRepository;

    @Override
    public List<RoomAccounting> findAll() {
        return roomAccountingRepository.findAll();
    }

    @Override
    public RoomAccounting findOne(Long id) {
        return roomAccountingRepository.findOne(id);
    }

    @Override
    public RoomAccounting save(RoomAccounting roomAccounting) {
        return roomAccountingRepository.save(roomAccounting);
    }

    @Override
    public void delete(Long id) {
        roomAccountingRepository.delete(id);
    }
}
