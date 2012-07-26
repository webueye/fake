package com.taoists.code.model;

import java.util.List;

import org.joda.time.LocalDate;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-26
 */
public class HistoryCodeModel {

	private String batchFileName;
	private String wsFileName;
	private String completeText;
	private String productName;
	private List<Box> boxs;
	private ImpResult result;

	private List<ImpResult> illegals;

	public void setIllegals(List<ImpResult> illegals) {
		this.illegals = illegals;
	}

	public List<ImpResult> getIllegals() {
		return illegals;
	}

	public ImpResult getResult() {
		return result;
	}

	public void setResult(ImpResult result) {
		this.result = result;
	}

	public String getBatchFileName() {
		return batchFileName;
	}

	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}

	public String getWsFileName() {
		return wsFileName;
	}

	public void setWsFileName(String wsFileName) {
		this.wsFileName = wsFileName;
	}

	public String getCompleteText() {
		return completeText;
	}

	public void setCompleteText(String completeText) {
		this.completeText = completeText;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Box> getBoxs() {
		return boxs;
	}

	public void setBoxs(List<Box> boxs) {
		this.boxs = boxs;
	}

	public static class Box {
		private String boxCode;
		private ImpResult boxCodeResult;
		private List<Fake> fakes;
		private LocalDate produceDate;

		public int getSize() {
			if (fakes == null) {
				return 1;
			} else {
				return fakes.size();
			}
		}

		public void setProduceDate(LocalDate produceDate) {
			this.produceDate = produceDate;
		}

		public LocalDate getProduceDate() {
			return produceDate;
		}

		public String getBoxCode() {
			return boxCode;
		}

		public void setBoxCode(String boxCode) {
			this.boxCode = boxCode;
		}

		public ImpResult getBoxCodeResult() {
			return boxCodeResult;
		}

		public void setBoxCodeResult(ImpResult boxCodeResult) {
			this.boxCodeResult = boxCodeResult;
		}

		public List<Fake> getFakes() {
			return fakes;
		}

		public void setFakes(List<Fake> fakes) {
			this.fakes = fakes;
		}

	}

	public static class Fake {
		private String completeText;
		private String plainCode;
		private ImpResult fakeResult;

		public String getCompleteText() {
			return completeText;
		}

		public void setCompleteText(String completeText) {
			this.completeText = completeText;
		}

		public String getPlainCode() {
			return plainCode;
		}

		public void setPlainCode(String plainCode) {
			this.plainCode = plainCode;
		}

		public ImpResult getFakeResult() {
			return fakeResult;
		}

		public void setFakeResult(ImpResult fakeResult) {
			this.fakeResult = fakeResult;
		}

	}

}
