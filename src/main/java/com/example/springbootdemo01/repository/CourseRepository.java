package com.example.springbootdemo01.repository;

import com.example.springbootdemo01.bean.Course;
import com.example.springbootdemo01.bean.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>, JpaSpecificationExecutor<Course> {

    @Query(value="select course_name from t_user_courseteach inner join t_user on t_user.user_id = t_user_courseteach.user_id inner join t_course on t_course.course_id = t_user_courseteach.course_id where t_user.user_id=:user_id",
            nativeQuery = true)
    public Set<String> findAllTeach(@Param("user_id") Long userId);

    @Query(value="select course_name from t_user_coursestudy inner join t_user on t_user.user_id = t_user_coursestudy.user_id inner join t_course on t_course.course_id = t_user_coursestudy.course_id where t_user.user_id=:user_id",
            nativeQuery = true)
    public Set<String> findAllStudy(@Param("user_id") Long userId);

    @Query(value = "select * from t_course where t_course.price = 0",nativeQuery = true)
    public List<Course> findFreeCourse();

    @Query(value = "select * from t_course where t_course.price != 0 ORDER BY t_course.chick DESC",nativeQuery = true)
    public List<Course> findWarCourse();
}
