package com.adms.elearning.web.bean.base;

import java.io.IOException;
import java.io.Serializable;
import java.util.TimeZone;

import com.adms.elearning.web.util.PropertyConfig;

public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private TimeZone timeZone;
	
	private Long nullLong;
	
	public BaseBean() {
		try {
			timeZone = TimeZone.getTimeZone(PropertyConfig.getInstance().getValue("cfg.timezone.asia.bangkok"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public Long getNullLong() {
		return nullLong;
	}

	public void setNullLong(Long nullLong) {
		this.nullLong = nullLong;
	}

}
