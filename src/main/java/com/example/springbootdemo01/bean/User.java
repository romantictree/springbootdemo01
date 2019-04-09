package com.example.springbootdemo01.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: 留住风的小树
 * @Description: 用户的实体类
 * @time:
 * @Version 1.0
 **/
@Entity
@Table(name="t_user")
public class User implements Serializable {

    private static final long serialVersionUID = -4234784897905851389L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_num")
    private String userNum;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="is_vip")
    private boolean isVIP;

    @Column(name="company")
    private String company;

    @Column(name="specialty")
    private String specialty;               //专业

    @Column(name="phoneNumber")
    private Integer phoneNumber;

    @Column(name="wechat")
    private String weChat;

    @Column(name="qq")
    private String QQ;

    @Column(name="birthday")
    private Date birthday;

    @OneToMany(mappedBy = "user")
    private Set<Evaluate> evaluates = new HashSet<>();

    @ManyToMany
    @JoinTable(name="t_user_courseteach",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="course_id"))
    private Set<Course> courseId = new HashSet<>();             //发布的课程

    @ManyToMany
    @JoinTable(name="t_user_coursestudy",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="course_id"))
    private Set<Course> wantCourse = new HashSet<>();           //想学习的课程
    @ManyToMany
    @JoinTable(name="t_user_skillone",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="skill_id"))
    private Set<Skill> studyType = new HashSet<>();       //想学习的技能
    @ManyToMany
    @JoinTable(name="t_user_skilltwo",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="skill_id"))
    private Set<Skill> skillType = new HashSet<>();      //拥有的技能

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="evaluate_id")
    private Evaluate evaluate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Course> getCourseId() {
        return courseId;
    }

    public void setCourseId(Set<Course> courseId) {
        this.courseId = courseId;
    }

    public Set<Course> getWantCourse() {
        return wantCourse;
    }

    public void setWantCourse(Set<Course> wantCourse) {
        this.wantCourse = wantCourse;
    }

    public Set<Skill> getStudyType() {
        return studyType;
    }

    public void setStudyType(Set<Skill> studyType) {
        this.studyType = studyType;
    }

    public Set<Skill> getSkillType() {
        return skillType;
    }

    public void setSkillType(Set<Skill> skillType) {
        this.skillType = skillType;
    }

    public Evaluate getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Evaluate evaluate) {
        this.evaluate = evaluate;
    }

    public Set<Evaluate> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(Set<Evaluate> evaluates) {
        this.evaluates = evaluates;
    }
}
