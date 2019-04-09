package com.example.springbootdemo01.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: 留住风的小树
 * @Description: 课程的评价
 * @time: 2019-03-16
 * @Version 1.0
 **/
@Entity
@Table(name="t_assess")
public class Assess implements Serializable {

    private static final long serialVersionUID = -7924164834903546399L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="assess_id")
    private Long assessId;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="course_id")
    private Course course;

    @Column
    private String context;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Long getAssessId() {
        return assessId;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Assess(){
        super();
    }

    @Override
    public String toString() {
        return "Assess{" +
                "assessId=" + assessId +
                ", course=" + course +
                ", context='" + context + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
