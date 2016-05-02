package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.RoomType;
import com.popovgosha.motelbackend.services.RoomTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Georgiy Popov on 25.04.2016.
 */
@RestController
public class RoomTypeController {

    private final Logger log = Logger.getLogger(RoomTypeController.class);

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping(value = "/roomtype", method = RequestMethod.GET)
    public ResponseEntity<List<RoomType>> allRoomType(){
        log.info("Get all room's type...");
        List<RoomType> rooms = roomTypeService.findAll();
        if(!rooms.isEmpty()){
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomtype/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoomType> getRoomType(@PathVariable("id") Integer id){
        log.info("Get room type... #" + id);
        RoomType roomType = roomTypeService.findOne(id);
        if (roomType != null) {
            return new ResponseEntity<>(roomType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST)
    public ResponseEntity<RoomType> newRoomType(@RequestBody RoomType roomType){
        log.info("Create new room type..." + roomType.toString());
        RoomType roomTypePersist = roomTypeService.save(roomType);
        return new ResponseEntity<>(roomTypePersist, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/roomtype/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoomType> updateRoomType(@PathVariable("id") Integer id, @RequestBody RoomType roomType){
        log.info("Update Room type #" + id);
        RoomType roomTypeOld = roomTypeService.findOne(id);
        if (roomTypeOld != null){
            roomTypeService.save(roomType);
            return new ResponseEntity<>(roomType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomtype/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RoomType> deleteRoomType(@PathVariable("id") Integer id){
        log.info("Delete Room type #" + id);
        RoomType roomType = roomTypeService.findOne(id);
        if (roomType != null){
            roomTypeService.delete(id);
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
