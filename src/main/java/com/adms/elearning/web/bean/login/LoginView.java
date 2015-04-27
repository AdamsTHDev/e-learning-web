package com.adms.elearning.web.bean.login;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.adms.common.entity.UserSession;
import com.adms.elearning.web.bean.base.BaseBean;
import com.adms.elearning.web.util.MessageUtils;
import com.adms.elearning.web.util.PropertyConfig;
import com.adms.utils.EncryptionUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ManagedBean
@ViewScoped
public class LoginView extends BaseBean {

	private static final long serialVersionUID = -5671223478971772749L;

	private String loginId;
	
//	reg = ^(?=.*\d).{4,8}$
	private String password;
	
	private String firstName;
	private String lastName;
	
	private boolean firstLogin = false;
	
	private final PropertyConfig cfg = PropertyConfig.getInstance();
	
	public String doLogin() {
		boolean flag = false;
		try {
//			TODO Authentication Method
//			flag = callAuthenService();
			
			if(StringUtils.isBlank(loginId)) {
				flag = false;
				((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmLogin:loginId")).setValid(false);
				MessageUtils.getInstance().addErrorMessage("loginMsg", "Enter your ID.");
			} else {
				//TODO check is first Login.
				firstLogin = false;
				
				if(firstLogin) {
					if(StringUtils.isNoneBlank(firstName) && StringUtils.isNoneBlank(lastName)) {
						//TODO Insert to DB
						System.out.println("username: " + loginId);
						System.out.println("firstName: " + firstName);
						System.out.println("lastName: " + lastName);
						
						flag = true;
					} else {
						if(StringUtils.isBlank(firstName)) {
							((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmLogin:firstName")).setValid(false);
						}
						if(StringUtils.isBlank(lastName)) {
							((UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmLogin:lastName")).setValid(false);
						}
						MessageUtils.getInstance().addErrorMessage("loginMsg", "Enter your First name & Last name.");
						flag = false;
					}
				} else {
					flag = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
//			re-direct
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", this.loginId);
			return "page/courseinfo?faces-redirect=true";
		} else {
			return null;
		}
	}
	
	public String doLogout() {
		System.out.println("=== doLogout() ===");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "loginPage";
	}
	
	@SuppressWarnings("unused")
	private boolean callAuthenService() throws IOException {
		String targetUrl = cfg.getValue("cfg.link.service.authen");
		String pathAuthen = cfg.getValue("cfg.link.service.authen.path.authen");
		
		String encrypted = EncryptionUtil.getInstance().encrypt(password);
//		System.out.println("encrypted: " + encrypted);
		UserSession us = process(targetUrl, pathAuthen, new UserSession(loginId, encrypted));
		
		if(us != null && us.getLoginSuccess().booleanValue() == true) {
			return true;
		}
		
		return false;
	}
	
	private UserSession process(String target, String path, UserSession userSession) {
		Gson gson = new GsonBuilder().create();
		Response response = ClientBuilder.newClient()
				.target(target)
				.path(path)
				.request()
				.header("strJson", gson.toJson(userSession))
				.get();
		String resStr = response.readEntity(String.class);
//		System.out.println("status: " + response.getStatus());
		
		gson = new GsonBuilder().create();
		
		UserSession us = gson.fromJson(resStr, UserSession.class);
		return us;
	}

	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
}
