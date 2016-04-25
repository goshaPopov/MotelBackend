package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.services.GuestService;
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
 * Created by Georgiy Popov on 20.04.2016.
 */
@RestController
public class GuestController {

    private final Logger log = Logger.getLogger(GuestController.class);

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public ResponseEntity<List<Guest>> allGuest(){
        log.info("Get all guests...");
        List<Guest> users = guestService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*
    @RequestMapping(value = "/guest",method = RequestMethod.GET)
    public ResponseEntity<List<Guest>> guestBySFP(
            @RequestParam("secondName") String secondName,
            @RequestParam("firstName") String firstName,
            @RequestParam("patronymic") String patronymic){


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public ResponseEntity<Guest> guestByPassportSeries(
            @RequestParam("passportNumber") String passportNumber){
        Guest guest = guestService.getGuestByPassportNumber(passportNumber);
        if (guest != null){
            return new ResponseEntity<>(guest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    */

    @RequestMapping(value = "/guest/{id}",method = RequestMethod.GET)
    public ResponseEntity<Guest> getGuest(@PathVariable("id") Long id){
        log.info("Get guest #" + id);
        Guest guest = guestService.findOne(id);
        if (guest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @RequestMapping(value = "/guest", method = RequestMethod.POST)
    public ResponseEntity<Guest> newGuest(@Valid @RequestBody Guest guest){
        log.info("Create new guest..." + guest.toString());
        boolean isFreePassport = guestService.isFreePassportSeriesNumber(guest.getPassportData());
        // Checking Guest(Null or Not Null) and passport data.
        if (isFreePassport ){
            Guest newGuest = guestService.save(guest);
            return new ResponseEntity<>(newGuest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/guest/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Guest> updateGuest(@PathVariable("id") Long id,@Valid @RequestBody Guest guest){
        log.info("Update guest #" + id);
        Guest guestOld = guestService.findOne(id);
        boolean isFreePassport = guestService.isFreePassportSeriesNumber(guest.getPassportData());
        if((guestOld != null) && isFreePassport){
            guestService.save(guest);
            return new ResponseEntity<>(guest, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/guest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Guest> deleteGuest(@PathVariable("id") Long id){
        log.info("Delete guest #" + id);
        Guest guest = guestService.findOne(id);
        if (guest == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        guestService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
