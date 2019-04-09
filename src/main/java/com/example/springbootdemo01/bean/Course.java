package com.example.springbootdemo01.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: 留住风的小树
 * @Description: 付费课程实体类
 * @time: 2019-03-16
 * @Version 1.0
 **/
@Entity
@Table(name="t_course")
public class Course implements Serializable {

    private static final long serialVersionUID = -3765825320710031171L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="course_id")
    private Long courseId;

    @Column(name="course_num")
    private String courseNum;

    @Column(name="course_name")
    private String courseName;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="ctype_id")
    private CourseType ctype;

    @Column(name="price")
    private int price;

    @Column(name="described")
    private String described;

    @OneToMany(mappedBy = "course")
    private Set<Assess> assesses = new HashSet<>();

    @Column(name="score")
    private int score;

    @Column(name="chick")
    private int chick;    //点击次数

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCtype() {
        return ctype;
    }

    public void setCtype(CourseType ctype) {
        this.ctype = ctype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Set<Assess> getAssesses() {
        return assesses;
    }

    public void setAssesses(Set<Assess> assesses) {
        this.assesses = assesses;
    }

    public int getChick() {
        return chick;
    }

    public void setChick(int chick) {
        this.chick = chick;
    }
}
