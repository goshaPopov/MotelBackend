package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.domain.RoomAccounting;
import com.popovgosha.motelbackend.domain.StatusRoom;
import com.popovgosha.motelbackend.services.RoomAccountingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Georgiy Popov on 28.04.2015.
 */
@RestController
public class RoomAccController {

    private final Logger log = Logger.getLogger(RoomAccController.class);

    @Autowired
    private RoomAccountingService roomAccService;

    @RequestMapping(value = "/roomacc/search", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> searchRoom(@Param("stat") StatusRoom statusRoom){
        List<Room> rooms = roomAccService.getRoomByStatus(statusRoom);
        if ((rooms != null) && !(rooms.isEmpty())){
            new ResponseEntity<>(rooms, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomacc", method = RequestMethod.GET)
    public ResponseEntity<List<RoomAccounting>> allRooms(){
        log.info("Get all rooms");
        List<RoomAccounting> rooms = roomAccService.findAll();
        if (rooms != null && !(rooms.isEmpty())){
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomacc/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoomAccounting> getRoomAcc(@PathVariable("id") Long id){
        log.info("Get Room Accounting unit #" + id);
        RoomAccounting roomAcc = roomAccService.findOne(id);
        if (roomAcc != null){
            return new ResponseEntity<>(roomAcc, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomacc/", method = RequestMethod.POST)
    public ResponseEntity<RoomAccounting> updateRoomAcc(@RequestBody RoomAccounting roomAcc){
        log.info("Create room accounting #");
        roomAccService.save(roomAcc);
        return new ResponseEntity<>(roomAcc, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomacc/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoomAccounting> updateRoomAcc(@PathVariable("id") Long id, @RequestBody RoomAccounting roomAcc){
        log.info("Update room accounting #" + id);
        RoomAccounting roomAccOld = roomAccService.findOne(id);
        if (roomAccOld != null){
            roomAccService.save(roomAcc);
            return new ResponseEntity<>(roomAcc, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/roomacc/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RoomAccounting> updateRoomAcc(@PathVariable("id") Long id){
        log.info("Delete RoomAcc #" + id);
        RoomAccounting roomAcc = roomAccService.findOne(id);
        if (roomAcc != null){
            roomAccService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
