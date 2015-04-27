package com.adms.elearning.web.bean.user;

import java.util.Date;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adms.elearning.web.bean.base.BaseBean;

@ManagedBean
@SessionScoped
public class UserSession extends BaseBean {

	private static final long serialVersionUID = 2459920705007190713L;

	private String username;
	private Date loginDate;
	
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
