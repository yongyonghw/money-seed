package com.extend.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.extend.api.vo.Room;
import com.extend.api.vo.User;


public interface RoomRepository extends CrudRepository<Room, String>{

	List<User> findByRoomId(String roomId);
}
