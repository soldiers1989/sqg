<#include '../h5indexHead.ftl' />
<body ontouchstart>
		<!--content-->
		<div class="weui-pull-to-refresh__layer">
	      <div class='weui-pull-to-refresh__arrow'></div>
	      <div class='weui-pull-to-refresh__preloader'></div>
	      <div class="down">下拉刷新</div>
	      <div class="up">释放刷新</div>
	      <div class="refresh">正在刷新</div>
	    </div>
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
		    
		    <div class="weui-panel weui-panel_access">
				<div class="weui-panel__hd">最近提现明细</div>
					<div class="weui-panel__bd" id="walletlist">
					
				</div>
			</div>
		</div>
		<!--向下滚动加载样式-->
		<div class="weui-loadmore" id="weui-loadmore"  style="display: none;">
	      <i class="weui-loading"></i>
	      <span class="weui-loadmore__tips">正在加载</span>
	    </div>
	    <div class="weui-loadmore weui-loadmore_line" id="weui-loadmore_line" style="display: none;">
		  <span class="weui-loadmore__tips">没有更多数据</span>
		</div>
		<!--引用 js-->
		<script>
		
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
		<script>
			var currentPage = 1;
			var rows = 8;
			var loading=false;
				$(document).ready(function(){
					load("pull");
				})		
			$(function() {
				FastClick.attach(document.body);
				
			});
			
			//上拉刷新页面
			$(document.body).pullToRefresh().on('pull-to-refresh', function(done) {
				currentPage=1;
				load('pull');
				$(this).pullToRefreshDone();
			})
			
			function load(flag){
			var data = {"pageNum":currentPage,"rows":rows};
				var config = {
					url : "${sysContextPath}/web/wallet/list.json",
					postData : data,
					dataType:"json",
					onSuccessFunction : function(data) {
						var html="";
						if(data!=null){
							$.each(data,function(i,order){
								var showStr = order.code;
								if (showStr == null){
									showStr = order.account;
								}
								html += '<div class="weui-cell wallet_list">' + 
						            '<div class="weui-cell__bd">' +
				            			'<p>' + showStr + '</p>' +
						              	'<p class="gary">' + dateFtt('yyyy-MM-dd hh:mm:ss',new Date(order.createTime)) + '</p>' +
						            '</div>' +
						            '<div class="weui-cell__ft green">-<span>' + Number(order.amount).toFixed(2) + '</span></div>' +
						        '</div>';
							})
						}
						if(flag=='pull'){//下拉刷新
							$("#walletlist").html(html);
						}else{//上拉加载
							$("#walletlist").append(html);
						}
						if(data==''||data.length<rows) {
							$(".weui-loadmore").hide();
							$(".weui-loadmore_line").show();
						}else{
							loading = false;
						}
					},
					mask : false
				};
				myAjaxRequest(config);
			}
			
			//下拉加载页面
			$(document.body).infinite().on("infinite", function() {
			  if(loading) return;
				  loading = true;
				 currentPage=currentPage+1;
				 load("");
			});
			 
		</script>
	</body>
</html>