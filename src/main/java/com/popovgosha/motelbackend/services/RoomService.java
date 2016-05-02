package com.popovgosha.motelbackend.services;

import com.popovgosha.motelbackend.domain.Room;
import com.popovgosha.motelbackend.domain.RoomType;

import java.util.List;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
public interface RoomService extends AbstractService<Room, Integer> {

    List<Room> getRoomsByType(Integer idRoomType);

}
