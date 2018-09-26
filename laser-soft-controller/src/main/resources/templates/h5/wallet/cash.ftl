<#include '../h5indexHead.ftl' />
<body ontouchstart>
		<!--content-->
		<div class="content">
			<div class="weui-cells">
				<#if (user.userAlipayAccount)??>
					<a class="weui-cell weui-cell_access" href="${sysContextPath}/web/wallet/editAlipay">
						<div class="weui-cell__hd">
							<img src="../../static/images/alipay_icon.png" class="bank_logo" />
						</div>
						<div class="weui-cell__bd">
							${user.userAlipayAccount!''}
							<input type="hidden" id="userAlipayAccount" value="${user.userAlipayAccount!''}"/>
						</div>
						<div class="weui-cell__ft"></div>
					</a>
				<#else>
					<a class="weui-cell weui-cell_access" href="${sysContextPath}/web/wallet/addAlipay">
						<div class="weui-cell__bd gary">
							<i class="iconfont icon-jiahao"></i>&nbsp;添加支付宝账号
						</div>
					</a>
				</#if>
				<div class="weui-cell">
			        <div class="cash_biginput">
				          <span>￥</span>
				          <input class="weui-input" type="number" id="withdrawAmout" placeholder="提现金额">
			        </div>
			    </div>
			    <div class="weui-cell f14">
			    		可用余额：￥${(amount)?string('0.00')}元
			    </div>
			</div>
			<div class="weui-btn-area">
		      <a class="weui-btn weui-btn_primary" href="javascript:void(0)" onclick="saveWithdraw()">确认提现</a>
		    </div>
		</div>
		<!--引用 js-->
		<script>
			$(function() {
				FastClick.attach(document.body);
			});
			var amount = "${(amount)?string('0.00')}";
			amount = parseFloat(amount);
			function saveWithdraw(){
				var withdrawAmout = $("#withdrawAmout").val();
				withdrawAmout = parseFloat(withdrawAmout);
				if (withdrawAmout > amount) {
					alert("提现金额不能大于可用余额");
					$("#withdrawAmout").val("");
					return;
				}
				var userAlipayAccount = $("#userAlipayAccount").val();
				if (userAlipayAccount == "" || userAlipayAccount == null){
					alert("请先绑定支付宝账号!");
					return;
				}
				var config = {
					url : "${sysContextPath}/web/wallet/withdraw",
					postData : {"amount": withdrawAmout, "userAlipayAccount": userAlipayAccount},
					dataType:"json",
					onSuccessFunction : function(data) {
						if (data.code == "success") {
							alert("操作成功!");
							location.href="${sysContextPath}/web/wallet/index";
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