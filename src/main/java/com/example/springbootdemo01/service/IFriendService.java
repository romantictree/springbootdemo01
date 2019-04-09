package com.example.springbootdemo01.service;

public interface IFriendService {

    public void addFriend(Long userId,Long friendId);

    public void deleteFriend(Long userId,Long friendId);
}
