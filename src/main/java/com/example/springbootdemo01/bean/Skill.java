package com.example.springbootdemo01.bean;

import javax.persistence.*;
import java.io.Serializable;

/*
 * @Author: 留住风的小树
 * @Description: 技能的实体类
 * @time:
 * @Version 1.0
 **/
@Entity
@Table(name="t_skill")
public class Skill implements Serializable {

    private static final long serialVersionUID = 2895197584367322569L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="skill_id")
    private Long skillId;

    @Column(name="skill_num")
    private String skillNum;

    @Column(name="skill_name")
    private String skillName;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="stype_id")
    private  SkillType skillType;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillNum() {
        return skillNum;
    }

    public void setSkillNum(String skillNum) {
        this.skillNum = skillNum;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skillId=" + skillId +
                ", skillNum='" + skillNum + '\'' +
                ", skillName='" + skillName + '\'' +
                ", skillType='" + skillType + '\'' +
                '}';
    }
}
