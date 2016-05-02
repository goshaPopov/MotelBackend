package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.domain.RoomType;
import com.popovgosha.motelbackend.repository.RoomRepository;
import com.popovgosha.motelbackend.repository.RoomTypeRepository;
import com.popovgosha.motelbackend.services.RoomService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findOne(Integer id) {
        return roomRepository.findOne(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Integer id) {
        roomRepository.delete(id);
    }

    @Transactional
    @Override
    public List<Room> getRoomsByType(Integer idRoomType) {
        RoomType roomType = roomTypeRepository.findOne(idRoomType);
        Hibernate.initialize(roomType.getRooms());
        return roomType.getRooms();
    }

}
