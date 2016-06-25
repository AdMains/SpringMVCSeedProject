package com.zhangzhihao.SpringMVCSeedProject.Model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Teacher")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher {
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "increment")
	@NotNull
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String passWord;

	public Teacher(String name, String passWord) {
		this.name = name;
		this.passWord = passWord;
	}

	public Teacher() {
	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", passWord='" + passWord + '\'' +
				'}';
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}

