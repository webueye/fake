<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/twitter/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/twitter/bootstrap-tooltip.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/validate/messages_cn.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/date/WdatePicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#validateForm").validate();
		
		$("button").filter(".historyBackClass").each(function(i){
			$(this).bind("click", function(){
				window.history.go(-1);
			});
		});
		
		$("a").filter(".deletePromptClass").each(function(i){
			$(this).bind("click", function(){
				//$("#modalDiv").show();
				//$("#modalDiv").modal({
				//	backdrop:true
				//});
				//return false;
			});
		});
		
	});
</script>

<div class="modal" id="modalDiv" style="display: none;">
  <div class="modal-header">
    <button class="close" data-dismiss="modal">X</button>
    <h3>提示</h3>
  </div>
  <div class="modal-body">
    <p>是否要删除</p>
  </div>
  <div class="modal-footer">
    <a href="#" class="btn">否</a>
    <a href="#" class="btn btn-primary">是</a>
  </div>
</div>