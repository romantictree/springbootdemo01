package com.example.springbootdemo01.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
 * @Author: 留住风的小树
 * @Description: 技能或者用户的评价
 * @time:
 * @Version 1.0
 **/
@Entity
@Table(name="t_evaluate")
public class Evaluate implements Serializable {

    private static final long serialVersionUID = -5621693252079024849L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="evaluate_id")
    private Long evaluateId;

    @Column(name="context")
    private String context;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_id")
    private User user;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
