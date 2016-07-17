package com.zhangzhihao.SpringMVCSeedProject.Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Role implements Serializable {
    private static final long serialVersionUID = 6177417435897400228L;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;
    @NotNull
    private String name;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "User_Role",
            joinColumns = {@JoinColumn(name = "RoleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "UserName", referencedColumnName = "userName")})
    private List<User> userList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Role_Permission",
            joinColumns = {@JoinColumn(name = "RoleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "PermissionId", referencedColumnName = "id")})
    private List<Permission> permissionList;

    public Role() {
    }

    public Role(String name, String description, List<User> userList, List<Permission> permissionList) {
        this.name = name;
        this.description = description;
        this.userList = userList;
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userList=" + userList +
                ", permissionList=" + permissionList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
