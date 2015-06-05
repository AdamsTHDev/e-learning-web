package com.adms.elearning.web.validation;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.adms.elearning.web.util.MessageUtils;

@FacesValidator("com.adms.elearning.web.validation.CitizenValidator")
public class CitizenValidator implements Validator {

	private static final String PATTERN = "^[0-9]{13}$";
	
	private Pattern pattern;
	private Matcher matcher;
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com.adms.msg.globalMsg");
	
	public CitizenValidator() {
		pattern = Pattern.compile(PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value == null || value == "" || StringUtils.isBlank(String.valueOf(value))) {
			throw new ValidatorException(MessageUtils.getInstance().getErrorFacesMsg(resourceBundle.getString("validate.err.citizen.id.empty"), ""));
		}
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()) {
//			MessageUtils.getInstance().addErrorMessage(null, resourceBundle.getString("validate.err.citizen.id.pattern"));
			throw new ValidatorException(MessageUtils.getInstance().getErrorFacesMsg(resourceBundle.getString("validate.err.citizen.id.pattern"), ""));
		}
	}
	
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

}
