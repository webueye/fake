package com.taoists.code.model;

import java.util.Collection;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-22
 */
public class HistoryFileModel {

	public static final String WS = "ws";
	public static final String BATCH = "batch";
	public static final String OUT = "out";
	public static final String STOCK_OUT = "stockout";

	private String suffix;
	private String wsFileName;
	private String batchFileName;
	private Collection<String> outFileNames;
	private String stockOutFileName;
	private Collection<String> stockOutLines;

	public HistoryFileModel() {

	}

	public HistoryFileModel(String suffix, Collection<String> names) {
		this.suffix = suffix;
		for (String name : names) {
			String lower = name.toLowerCase();
			if (lower.contains(WS)) {
				this.wsFileName = name;
			} else if (lower.contains(BATCH)) {
				this.batchFileName = name;
			}
		}
	}

	public int getOutFileSize() {
		if (outFileNames == null) {
			return 1;
		}
		return outFileNames.size();
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	public Collection<String> getOutFileNames() {
		return outFileNames;
	}

	public void setOutFileNames(Collection<String> outFileNames) {
		this.outFileNames = outFileNames;
	}

	public String getStockOutFileName() {
		return stockOutFileName;
	}

	public void setStockOutFileName(String stockOutFileName) {
		this.stockOutFileName = stockOutFileName;
	}

	public Collection<String> getStockOutLines() {
		return stockOutLines;
	}

	public void setStockOutLines(Collection<String> stockOutLines) {
		this.stockOutLines = stockOutLines;
	}

}
