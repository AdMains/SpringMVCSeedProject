package com.zhangzhihao.SpringMVCSeedProject.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Table
@Entity
public class Permission implements Serializable {
    private static final long serialVersionUID = 2683590474689747957L;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    private Long id;
    @NotNull
    private String name;
    private String description;
    private String permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Permission parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Permission> childrenList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permissionList")
    private List<Role> roleList;

    public Permission() {
    }

    public Permission(String name, String description, String permission, Permission parent, List<Permission> childrenList, List<Role> roleList) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.parent = parent;
        this.childrenList = childrenList;
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permission='" + permission + '\'' +
                ", parent=" + parent +
                ", childrenList=" + childrenList +
                ", roleList=" + roleList +
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }

    public List<Permission> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Permission> childrenList) {
        this.childrenList = childrenList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
