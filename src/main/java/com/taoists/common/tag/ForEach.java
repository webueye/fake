package com.taoists.common.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;

import com.google.common.collect.Lists;
import com.taoists.sys.entity.NodeModel;

/**
 * @author rubys@vip.qq.com
 * @since 2012-5-29
 */
@SuppressWarnings("serial")
public class ForEach extends BodyTagSupport {

	private String var;
	private String indexed;
	private String items;
	private List<NodeModel> itemList;
	private Iterator<?> iterator;
	private int rows = 0;

	@Override
	public int doAfterBody() throws JspException {
		if (iterator.hasNext()) {
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		itemList = getItemList();
		if (CollectionUtils.isNotEmpty(itemList)) {
			List<NodeModel> list = Lists.newArrayList();
			iterator = loop(list, itemList, rows).iterator();
			pageContext.setAttribute(var, iterator.next());
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

	@SuppressWarnings("unchecked")
	public List<NodeModel> loop(List<NodeModel> list, List<NodeModel> nodes, int rows) {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).setLevel(rows);
			nodes.get(i).setRows(new String[rows]);
			list.add(nodes.get(i));
			if (i == 0) {
				nodes.get(i).setFirst(true);
			}
			if (i == nodes.size() - 1) {
				nodes.get(i).setLast(true);
			}
			if (CollectionUtils.isNotEmpty(nodes.get(i).getChild()) && BooleanUtils.isTrue(nodes.get(i).getExpanded())) {
				loop(list, (List<NodeModel>) nodes.get(i).getChild(), rows + 1);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getItemList() {
		if (pageContext.getAttribute(items, PageContext.PAGE_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.PAGE_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.REQUEST_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.REQUEST_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.SESSION_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.SESSION_SCOPE);
		}
		if (pageContext.getAttribute(items, PageContext.APPLICATION_SCOPE) != null) {
			return (List) pageContext.getAttribute(items, PageContext.APPLICATION_SCOPE);
		}
		return null;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getIndexed() {
		return indexed;
	}

	public void setIndexed(String indexed) {
		this.indexed = indexed;
	}

}
