package com.popovgosha.motelbackend.services.impl;

import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.repository.GuestRepository;
import com.popovgosha.motelbackend.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
@Service
public class GuestServiceImpl implements GuestService{

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest findOne(Long id) {
        return guestRepository.findOne(id);
    }

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public void delete(Long id) {
        guestRepository.delete(id);
    }

}
