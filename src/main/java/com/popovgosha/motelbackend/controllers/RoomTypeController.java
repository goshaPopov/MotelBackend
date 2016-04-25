package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.domain.RoomType;
import com.popovgosha.motelbackend.repository.RoomTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Georgiy Popov on 25.04.2016.
 */
@RestController
public class RoomTypeController {

    private final Logger log = Logger.getLogger(RoomTypeController.class);

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @RequestMapping(value = "/roomtype", method = RequestMethod.GET)
    public ResponseEntity<List<RoomType>> allRoomType(){
        log.info("Get all room's type...");
        List<RoomType> rooms = roomTypeRepository.findAll();
        if(rooms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


}
