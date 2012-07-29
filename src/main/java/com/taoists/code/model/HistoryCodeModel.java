package com.taoists.code.model;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * @author rubys@vip.qq.com
 * @since 2012-7-26
 */
public class HistoryCodeModel {

	public static final String EXISTED = "existed";
	public static final String NOT_EXISTED = "notExisted";
	public static final String ILLEGAL = "illegal";

	private String batchFileName;
	private String wsFileName;
	private String completeText;
	private String productName;
	private List<Box> boxs;
	private ImpResult result;

	private List<ImpResult> illegals;

	public static Multimap<String, Object> group(List<HistoryCodeModel> list) {
		Multimap<String, Object> group = HashMultimap.create();
		for (HistoryCodeModel model : list) {
			if (CollectionUtils.isNotEmpty(model.getBoxs())) {
				for (Box box : model.getBoxs()) {
					if (CollectionUtils.isNotEmpty(box.getFakes())) {
						for (Fake fake : box.getFakes()) {
							Box b = new Box();
							b.setBoxCode(box.getBoxCode());
							fake.setBox(b);
							if (fake.getFakeResult() != null) {
								group.put(EXISTED, fake);
							} else {
								group.put(NOT_EXISTED, fake);
							}
						}
					}
				}
			}
			if (model.getResult() != null) {
				group.put(ILLEGAL, model.getResult());
			}
			if (CollectionUtils.isNotEmpty(model.getBoxs())) {
				for (ImpResult illegal : model.getIllegals()) {
					group.put(ILLEGAL, illegal);
				}
			}
		}
		return group;
	}

	public static List<Box> groupByBox(Collection<Object> collection) {
		Multimap<Box, Object> group = HashMultimap.create();
		for (Object obj : collection) {
			Fake fake = (Fake) obj;
			group.put(fake.getBox(), fake);
		}
		List<Box> boxs = Lists.newArrayList();
		for(Entry<Box, Collection<Object>> entry: group.asMap().entrySet()){
			Box box = entry.getKey();
			List<Fake> fakes = Lists.newArrayList();
			for(Object obj: entry.getValue()){
				fakes.add((Fake) obj);
			}
			box.setFakes(fakes);
			boxs.add(box);
		}
		return boxs;
	}

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((boxCode == null) ? 0 : boxCode.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Box other = (Box) obj;
			if (boxCode == null) {
				if (other.boxCode != null)
					return false;
			} else if (!boxCode.equals(other.boxCode))
				return false;
			return true;
		}

	}

	public static class Fake {
		private String completeText;
		private String plainCode;
		private ImpResult fakeResult;
		private Box box;

		public void setBox(Box box) {
			this.box = box;
		}

		public Box getBox() {
			return box;
		}

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
