package com.popovgosha.motelbackend.services;

import com.popovgosha.motelbackend.domain.Guest;

import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface GuestService extends AbstractService<Guest, Long> {

    boolean isFreePassportSeriesNumber(String passport);

    List<Guest> searchByPassportData(String passportNumber);

}
