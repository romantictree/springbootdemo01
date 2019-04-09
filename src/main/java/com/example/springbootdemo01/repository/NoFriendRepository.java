package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoFriendRepository extends JpaRepository<NoFriend,Long> {
    public NoFriend findByUserIdAndFriendId(Long userId,Long friendId);
}
