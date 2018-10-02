<div class="row" id="viewShow">
	
</div>

<script>

	var viewConfig = [
	            {"name": "parentId", "text": "父用户Id"},
	            {"name": "parentIdPath", "text": "父用户关系"},
	            {"name": "userNickname", "text": "用户昵称"},
	            {"name": "userPhone", "text": "手机号", "sortable": "no"},
	            {"name": "userLevel", "text": "用户等级"},
	            {"name": "createTime", "text": "创建时间"},
	            {"name": "userRelname", "text": "真实姓名"},
	            {"name": "userAlipayAccount", "text": "支付宝账号"},
	            {"name": "amount", "text": "账户余额"},
	            {"name": "amountA", "text": "账户可用余额"},
	            {"name": "amountF", "text": "提现冻结余额"},
	            {"name": "amountE", "text": "已赚余额"}
	        ];

$(document).ready(function() {
	var id = ${id};
	var options = {
			url : "${sysContextPath}/admin/user/get.json?id="+id,
			postData : {id:id},
			onSuccessFunction : function(data) {
				showViewHtml(viewConfig,data.data,"用户详情");
			}
		};
	myAjaxRequest(options);
});

</script>
