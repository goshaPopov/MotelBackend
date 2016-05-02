package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = "SELECT * FROM guest WHERE passport_series_number = ?1",nativeQuery = true)
    Guest guestByPassportNumber(String passport);

    @Query(value = "SELECT * FROM guest WHERE passport_series_number LIKE %:pass%", nativeQuery = true)
    List<Guest> searchByPassportData(@Param("pass") String passport);

    @Query(value = "SELECT * FROM guest WHERE full_name LIKE %:fullName%", nativeQuery = true)
    List<Guest> searchByFullName(@Param("fullName") String fullName);
}
