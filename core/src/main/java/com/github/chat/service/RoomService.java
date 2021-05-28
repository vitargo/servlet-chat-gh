package com.github.chat.service;

import com.github.chat.entity.Room;
import com.github.chat.repository.Repository;

import java.util.List;

public class RoomService implements IService<Room>{

    private final Repository<Room> repository;

    public RoomService(Repository<Room> repository) {
        this.repository = repository;
    }

    @Override
    public Room create(Room room) {
        return this.repository.save(room);
    }

    @Override
    public List<Room> read() {
        return null;
    }

    @Override
    public Room findUser(Room entity) {
        return null;
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void delete(Room entity) {

    }
}
