package com.zhangzhihao.SpringMVCSeedProject.Model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {
    private static final long serialVersionUID = 7768578674897400228L;
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @NotNull
    private Integer courseID;
    @NotNull
    private String courseName;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherId")
    private Teacher teacher;
}
