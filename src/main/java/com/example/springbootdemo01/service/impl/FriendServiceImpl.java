package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.Friend;
import com.example.springbootdemo01.repository.FriendRepository;
import com.example.springbootdemo01.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FriendServiceImpl implements IFriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public void addFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findAllByUserIdAndFriendId(userId,friendId);
        if(friendRepository.selectCount(userId,friendId)>0){
            return;
        }
        Friend friend1 = new Friend();
        friend1.setUserId(userId);
        friend1.setFriendId(friendId);
        friend1.setIslike("0");
        friendRepository.save(friend1);
        if(friendRepository.findAllByUserIdAndFriendId(friendId,userId)!=null){
            friendRepository.updateIslike("1",userId,friendId);
            friendRepository.updateIslike("1",friendId,userId);
        }
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        friendRepository.deletefriend(userId,friendId);
        friendRepository.updateIslike("0",friendId,userId);
    }
}
