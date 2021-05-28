package com.github.chat.service;

import java.util.List;

public interface IRoomUserService {

    public void addUser();

    public List<Long> findAllByUser(Long idUser);

    public List<Long> findAllByChat(Long idChat);

}
