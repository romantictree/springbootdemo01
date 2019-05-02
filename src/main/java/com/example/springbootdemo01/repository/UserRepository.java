package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    public User findUserByUsername(String username);

    @Query(value = "select * from t_user inner join t_user_courseteach on t_user.user_id = t_user_courseteach.user_id where 1=1",nativeQuery = true)
    public List<User> findUserWithCourse();

    public User findUserByUsernameAndPassword(String username,String password);

    @Transactional
    @Modifying
    @Query(value="insert into t_user(username,password) values (:username,:password)",nativeQuery = true)
    public void createSelectivity(@Param("username") String username,@Param("password") String password);
}
