package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
