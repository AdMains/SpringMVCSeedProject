package com.zhangzhihao.SpringMVCSeedProject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    private static final long serialVersionUID = 895922977663522702L;

    @Id
    @NotNull
    private String userName;

    @JsonIgnore    //生成json不包含此字段
    @NotNull
    private String passWord;

    //@NotNull
    private AuthorityType authorityType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", optional = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private BankCard bankCard;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userList", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Role> roleList;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName, String passWord, AuthorityType authorityType) {
        this.userName = userName;
        this.passWord = passWord;
        this.authorityType = authorityType;
    }

    public User(String userName, String passWord,  List<Role> roleList) {
        this.userName = userName;
        this.passWord = passWord;
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", bankCard=" + bankCard +
                ", roleList=" + roleList +
                '}';
    }

    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public BankCard getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCard bankCard) {
        this.bankCard = bankCard;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
