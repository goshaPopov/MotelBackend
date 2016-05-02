package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.services.RoomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Georgiy Popov on 27.04.2016.
 */
@RestController
public class RoomController {

    private final Logger log = Logger.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/room/by")
    public ResponseEntity<List<Room>> getRoomsByType(@RequestParam("typeId") Integer id){
        log.info("Get all rooms of type #" + id);
        List<Room> rooms = roomService.getRoomsByType(id);
        if (!rooms.isEmpty()){
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> allRooms(){
        log.info("Get all rooms");
        List<Room> rooms = roomService.findAll();
        if (!rooms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoom(@PathVariable("id") Integer id){
        log.info("Get room with #" + id);
        Room room = roomService.findOne(id);
        if (room != null){
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Integer id,
                                           @RequestBody Room room){
        log.info("Update room #" + id);
        Room roomOld = roomService.findOne(id);
        if(roomOld != null){
            roomService.save(room);
            return new ResponseEntity<>(room, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/room/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") Integer id){
        log.info("Delete room #" + id);
        Room room = roomService.findOne(id);
        if(room != null){
            roomService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
