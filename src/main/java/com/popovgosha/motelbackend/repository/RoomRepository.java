package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
public interface RoomRepository extends JpaRepository<Room, Integer> {
}
