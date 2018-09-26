<#include '../h5indexHead.ftl' />
<body ontouchstart>
		<div class="content">
			<div class="weui-cells weui-cells_form">
	      		<div class="weui-cell">
			        <div class="weui-cell__hd">
			          <label class="weui-label">支付宝账号</label>
			        </div>
			        <div class="weui-cell__bd">
			          <input class="weui-input" type="text" id="userAlipayAccount" placeholder="请输入支付宝账号">
			        </div>
			    </div>
			</div>
		    <div class="weui-btn-area">
		      <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="saveAlipay()">保存</a>
		    </div>
		</div>
		<!--引用 js-->
		<script>
		function saveAlipay(){
			var userAlipayAccount = $("#userAlipayAccount").val();
			var config = {
				url : "${sysContextPath}/web/wallet/updateAlipay",
				postData : {"userAlipayAccount": userAlipayAccount},
				dataType:"json",
				onSuccessFunction : function(data) {
					if (data.code == "success") {
						location.href="${sysContextPath}/web/wallet/cash";
					} else {
						alert(data.data);
					}
				},
				mask : false
			};
			myAjaxRequest(config);
		}
		</script>
	</body>
</html>