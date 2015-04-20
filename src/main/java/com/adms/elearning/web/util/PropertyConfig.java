package com.adms.elearning.web.util;

import java.io.IOException;

import com.adms.utils.PropertyResource;

public class PropertyConfig {

	private static PropertyConfig instance;
	private final String CONFIG_PROP_PATH = this.getClass().getClassLoader().getResource("config/config.properties").getPath();
	private PropertyResource prop;
	
	public static PropertyConfig getInstance() {
		if(instance == null) {
			instance = new PropertyConfig();
		}
		return instance;
	}
	
	public PropertyConfig() {
		prop = new PropertyResource(CONFIG_PROP_PATH);
	}
	
	public String getValue(String name) throws IOException {
		return prop.getValue(name);
	}
	
}
