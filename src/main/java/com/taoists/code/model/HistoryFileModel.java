package com.taoists.code.model;

import java.util.Collection;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public class HistoryFileModel {

	public static final String WS = "ws";
	public static final String BATCH = "batch";

	private String suffix;
	private String wsFileName;
	private String batchFileName;

	public HistoryFileModel(String suffix, Collection<String> names) {
		this.suffix = suffix;
		for (String name : names) {
			String lower = name.toLowerCase();
			if (lower.contains(WS)) {
				this.wsFileName = name;
			}
		}
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return suffix;
	}

	public String getWsFileName() {
		return wsFileName;
	}

	public void setWsFileName(String wsFileName) {
		this.wsFileName = wsFileName;
	}

	public String getBatchFileName() {
		return batchFileName;
	}

	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}

}
