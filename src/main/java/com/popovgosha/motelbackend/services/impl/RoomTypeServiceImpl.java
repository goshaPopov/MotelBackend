package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.RoomType;
import com.popovgosha.motelbackend.repository.RoomTypeRepository;
import com.popovgosha.motelbackend.services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService{

    @Autowired
    private RoomTypeRepository roomTypeRepository;


    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType findOne(Integer id) {
        return roomTypeRepository.findOne(id);
    }

    @Override
    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Override
    public void delete(Integer id) {
        roomTypeRepository.delete(id);
    }
}
