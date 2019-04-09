package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long>, JpaSpecificationExecutor<Friend> {

    public Friend findAllByUserIdAndFriendId(Long userId,Long friendId);

    @Query("select count(f) from Friend f where f.userId=?1 and f.friendId=?2")
    public int selectCount(Long userId,Long friendId);

    @Modifying
    @Query(value = "update tb_friend set islike=? where userid = ? and friendid = ?", nativeQuery = true)
    public void updateIslike(String islike, Long userid, Long friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid = ? and friendid = ?", nativeQuery = true)
    void deletefriend(Long userid, Long friendid);
}
