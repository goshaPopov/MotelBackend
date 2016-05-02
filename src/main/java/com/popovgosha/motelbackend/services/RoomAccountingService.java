package com.popovgosha.motelbackend.services;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.domain.RoomAccounting;
import com.popovgosha.motelbackend.domain.StatusRoom;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
public interface RoomAccountingService extends AbstractService<RoomAccounting, Long> {

    List<Room> getRoomByStatus(StatusRoom status);

    List<Room> getFreeRoom();

}
