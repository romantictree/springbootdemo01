package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.Skill;
import com.example.springbootdemo01.bean.SkillType;
import com.example.springbootdemo01.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long>, JpaSpecificationExecutor<Skill> {
       public Set<Skill> findBySkillType(SkillType skillType);

       @Query(value = "select skill_name from t_user_skillone inner join t_user on t_user.user_id = t_user_skillone.user_id inner join t_skill on t_skill.skill_id = t_user_skillone.skill_id where t_user.user_id=:user_id",
               nativeQuery = true)
       public Set<String> findAllByUser(@Param("user_id") Long userId);

       @Query(value = "select skill_name from t_user_skilltwo inner join t_user on t_user.user_id = t_user_skilltwo.user_id inner join t_skill on t_skill.skill_id = t_user_skilltwo.skill_id where t_user.user_id=:user_id",
               nativeQuery = true)
       public Set<String> findAllWantByUser(@Param("user_id") Long userId);
}
