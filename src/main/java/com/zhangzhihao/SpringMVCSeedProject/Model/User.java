package com.zhangzhihao.SpringMVCSeedProject.Model;


import com.zhangzhihao.SpringMVCSeedProject.Annotation.AuthorityType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {
	@Id
	@NotNull
	private String userName;
	@NotNull
	private String passWord;
	@NotNull
	private AuthorityType authorityType;

	public User() {
	}

	public User(String userName, String passWord, AuthorityType authorityType) {
		this.userName = userName;
		this.passWord = passWord;
		this.authorityType = authorityType;
	}

	@Override
	public String toString() {
		return "User{" +
				"userName='" + userName + '\'' +
				", passWord='" + passWord + '\'' +
				", authorityType=" + authorityType +
				'}';
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

	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}
}
