package com.taoists.code.model;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import com.taoists.common.util.DateUtils;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-24
 */
public class SummaryModel {

	public SummaryModel(String summary) {
		String[] values = summary.split(",");
		if (values.length >= 5) {
			productNo = values[1];
			date = LocalDate.parse(values[4], DateTimeFormat.forPattern(DateUtils.PATTERN));
			complete = true;
		}
	}

	private String productNo;
	private LocalDate date;
	private boolean complete;

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
