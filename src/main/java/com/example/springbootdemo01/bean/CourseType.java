package com.example.springbootdemo01.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: 留住风的小树
 * @Description: 付费课程的类型
 * @time:
 * @Version 1.0
 **/
@Entity
@Table(name="t_coursetype")
public class CourseType implements Serializable {

    private static final long serialVersionUID = 2430486093015072577L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ctype_id")
    private Long ctype;

    @Column(name="type_name")
    private String typeName;

    @OneToMany(mappedBy = "ctype")
    private Set<Course> courses = new HashSet<>();

    public Long getCtype() {
        return ctype;
    }

    public void setCtype(Long ctype) {
        this.ctype = ctype;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "ctype=" + ctype +
                ", typeName='" + typeName + '\'' +
                ", courses=" + courses +
                '}';
    }
}
