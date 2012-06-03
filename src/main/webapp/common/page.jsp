<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function paginationHandle(pageNum, pageSize){
		if(!isNaN(pageSize)){
			var currentUrl = window.location.href;
			if(currentUrl.indexOf("?") == -1){
				window.location.href = currentUrl + "?pageNum="+pageNum+"&pageSize="+pageSize;
			}else if(currentUrl.indexOf("pageSize=") == -1){
				window.location.href = currentUrl + "&pageSize="+pageSize;
			}else{
				var url = currentUrl.substring(0, currentUrl.indexOf("&pageSize"));
				window.location.href = (url + "&pageSize="+pageSize);
			}
			return;
		}
		return true;
	}
</script>

<c:if test="${page.totalPages > 0}">
	<div style="margin: 1px;">
		
		<span>
			Showing ${page.beginIndex+1} to ${page.endIndex} of ${page.totalCount} entries
		</span>
		
		<span>
			Show
			<select name="pageSize" style="height:25px; line-height: 20px;width: 55px;margin:3px;" onchange='paginationHandle("${page.pageNum}", this.value)'>
				<option ${page.pageSize == '10'? 'selected': ''}>10</option>
				<option ${page.pageSize == '25'? 'selected': ''}>25</option>
				<option ${page.pageSize == '50'? 'selected': ''}>50</option>
				<option ${page.pageSize == '100'? 'selected': ''}>100</option>
			</select>
			entries
		</span>
		
		<span style="float:right;margin:5px;">
			<c:if test="${page.totalPages > 1}">
				<c:if test="${page.pageNum == 1}">
					[<span>First</span> |
					<span>Previous</span> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum+1}${param.param}" onclick='return paginationHandle("${page.pageNum+1}");'>Next</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.totalPages}${param.param}" onclick='return paginationHandle("${page.totalPages}");'>Last</a>]
				</c:if>
				<c:if test="${page.pageNum > 1 and page.pageNum < page.totalPages}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=1${param.param}" onclick='return paginationHandle("${a}"1);'>First</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum-1}${param.param}" onclick='return paginationHandle("${page.pageNum-1}");'>Previous</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum+1}${param.param}" onclick='return paginationHandle("${page.pageNum+1}");'>Next</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.totalPages}${param.param}" onclick='return paginationHandle("${page.totalPages}");'>Last</a>]
				</c:if>
				<c:if test="${page.pageNum == page.totalPages}">
					[<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=1${param.param}" onclick='return paginationHandle("${a}"1);'>First</a> |
					<a href="${pageContext.request.contextPath}/${param.actionURL}?pageNum=${page.pageNum-1}${param.param}" onclick='return paginationHandle("${page.pageNum-1}");'>Previous</a> |
					<span>Next</span> |
					<span>Last</span>]
				</c:if>	
			</c:if>
		</span>
			
	</div>
</c:if>
