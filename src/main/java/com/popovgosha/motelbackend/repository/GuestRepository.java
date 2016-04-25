package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = "SELECT * FROM guest WHERE passport_series_number = ?1",nativeQuery = true)
    Guest guestByPassportNumber(String passport);


}
