package com.taoists.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-6-10
 */
@SuppressWarnings("serial")
public class DateFormat extends TagSupport {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String value;
	private String pattern = "yyyy-MM-dd";

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			logger.debug("Date value[{}], pattern[{}]", value, pattern);
			if (StringUtils.isNotBlank(value)) {
				out.print(DateUtils.toString(value, pattern));
				return EVAL_PAGE;
			}
			out.print(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
