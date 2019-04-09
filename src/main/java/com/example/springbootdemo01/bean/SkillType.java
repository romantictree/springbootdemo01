package com.example.springbootdemo01.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
 * @Author: 留住风的小树
 * @Description: 技能的类型
 * @time:
 * @Version 1.0
 **/
@Entity
@Table(name="t_skilltype")
public class SkillType implements Serializable {

    private static final long serialVersionUID = 5691860258462221780L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="stype_id")
    private Long stypeId;

    @Column(name="type_name")
    private String typeName;

    @OneToMany(mappedBy = "skillType")
    private Set<Skill> skill = new HashSet<>();

    public Long getStypeId() {
        return stypeId;
    }

    public void setStypeId(Long stypeId) {
        this.stypeId = stypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SkillType{" +
                "stypeId=" + stypeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
