package com.example.springbootdemo01.service.impl;

import com.example.springbootdemo01.bean.NoFriend;
import com.example.springbootdemo01.repository.NoFriendRepository;
import com.example.springbootdemo01.service.INoFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class NoFriendServiceImpl implements INoFriendService {
    @Autowired
    private NoFriendRepository noFriendRepository;

    @Override
    public void addNoFriend(Long userId,Long friendId) {
        NoFriend n1 = noFriendRepository.findByUserIdAndFriendId(userId,friendId);
        if(n1!=null){
            return;
        }
        NoFriend noFriend = new NoFriend();
        noFriend.setUserId(userId);
        noFriend.setFriendId(friendId);
        noFriendRepository.save(noFriend);
    }
}
