package com.adms.elearning.web.bean.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adms.common.entity.UserLogin;
import com.adms.elearning.web.bean.base.BaseBean;

@ManagedBean
@SessionScoped
public class LoginSession extends BaseBean {

	private static final long serialVersionUID = -4511395913544909376L;
	
	private UserLogin userLogin;

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
	
}
