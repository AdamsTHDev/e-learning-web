package com.adms.elearning.web.authorization;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class AuthorizationListener implements PhaseListener {

	private static final long serialVersionUID = -1177538587408767802L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		
		boolean isLoginPage = (currentPage.lastIndexOf("login.")) > -1;
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		if(session == null) {
			NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
			navHandler.handleNavigation(facesContext, null, "loginPage");
		} else {
			Object currentUser = session.getAttribute("username");
//			System.out.println("Object currentUser: " + currentUser);
			if(!isLoginPage && (currentUser == null || currentUser == "")) {
				NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
				navHandler.handleNavigation(facesContext, null, "loginPage");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
