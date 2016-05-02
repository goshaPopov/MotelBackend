package com.popovgosha.motelbackend.repository;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.domain.RoomAccounting;
import com.popovgosha.motelbackend.domain.StatusRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
public interface RoomAccountingRepository extends JpaRepository<RoomAccounting, Long> {

    @Query(value = "SELECT * FROM room_accounting WHERE status=:status", nativeQuery = true)
    List<RoomAccounting> getRoomAccByStatus(@Param("status") StatusRoom status);

    @Query(value = "SELECT * FROM room WHERE id " +
            "NOT LIKE EXISTS(SELECT id_room FROM room_accounting WHERE (exit_date<now()))", nativeQuery = true)
    List<Room> getFreeRoom();

}
