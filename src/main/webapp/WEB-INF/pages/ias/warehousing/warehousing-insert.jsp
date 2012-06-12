<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>箱码贴箱入库</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class="row">
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/warehousing">	
					
					<table class="table table-bordered table-striped">
						<thead>
							<tr class="th">
								<th class="rth" colspan="5">箱码贴箱入库：</th>
							</tr>
							
							<tr>
								<td>输入箱码</td>
								<td colspan="3">
									<textarea id="boxCodeValues" name="boxCodeValues" rows="5" cols="80"></textarea>
									<label id="boxCodeValuesVerify" for="boxCodeValues" class="error" style="display: none;">请输入输入箱码</label>
								</td>
								<td>
									<button type="button" class="btn btn-primary" onclick="boxGroup();">添加</button>
								</td>
							</tr>
							
							<tr>
								<th>货号</th>
								<th>货品名称</th>
								<th>箱数</th>
								<th>总数</th>
								<th>箱码/包装类型/容量</th>
							</tr>
							
						</thead>
						
						<tbody id="detailContent">
							
						</tbody>
							
					</table>
					
					<div class="search" align="center">
						<div class="row">
							<div class="span12">
								<div class="control-group">
						           	<button type="submit" class="btn btn-primary">提&nbsp;&nbsp;&nbsp;交</button>
									<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
						        </div>
							</div>
						  </div>
					</div>
					
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			function removeElement(boxCode){
				var bcValues = $("#boxCodeValues").val().replace(boxCode, "");
				bcValues = bcValues.replace("\n", "");
				$("#boxCodeValues").val(bcValues);
				if(bcValues == ''){
					$("#detailContent").html("");
				}else{
					boxGroup();
				}
			}
			
			function boxGroup(){
				var codeValues = $("#boxCodeValues").val();
				if(codeValues == ''){
					$("#boxCodeValues").addClass("error");
					$("#boxCodeValuesVerify").show();
					return;
				}
				var data = {boxCodeValues: codeValues};
				$.ajax({
					type : 'get',
					url : '${pageContext.request.contextPath}/box-code/box-group',
					data: data,
					dataType : 'json',
					success : function(data) {
						if (data) {
							var con = new Array();
							for (var i = 0; i < data.length; i++) {
								con.push("<tr>");
								con.push("	<td>");
								con.push(		data[i].product.productNo);
								con.push("	</td>");
								con.push("	<td>");
								con.push(		data[i].product.name);
								con.push("	</td>");
								con.push("	<td>");
								con.push(		data[i].boxCount);
								con.push("	</td>");
								con.push("	<td>");
								con.push(		data[i].totalCount);
								con.push("	</td>");
								con.push("	<td>");
								for(var j = 0; j < data[i].boxCodes.length; j++){
									con.push("<div>");
									con.push("<input type='hidden' class='boxCodes' name='boxCodes' value='"+data[i].boxCodes[j].boxCode+"'/>");	
									con.push("["+data[i].boxCodes[j].boxCode+"] ");	
									con.push("["+data[i].boxCodes[j].boxSpec.specName+"] ");	
									con.push("["+data[i].boxCodes[j].boxSpec.capacity+"] ");	
									con.push("<span style='cursor: pointer;' onclick='removeElement("+data[i].boxCodes[j].boxCode+");'>[删除]</span>");	
									con.push("</div>");
								}
								con.push("	</td>");
								con.push("</tr>");
							}
							$("#detailContent").html(con.join(""));
						}
					}
				});				
			}
		</script>
		
	</body>

</html>