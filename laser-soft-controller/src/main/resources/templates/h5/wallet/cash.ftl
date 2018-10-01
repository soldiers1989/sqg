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
							<img src="${domainName}/static/images/alipay_icon.png" class="bank_logo" />
						</div>
						<div class="weui-cell__bd">
							${user.userAlipayAccount!''}
							<input type="hidden" id="userAlipayAccount" value="${user.userAlipayAccount!''}"/>
						</div>
					</a>
				<#else>
					<a class="weui-cell weui-cell_access" href="${sysContextPath}/web/wallet/addAlipay">
						<div class="weui-cell__bd gary">
							<i class="iconfont icon-jiahao"></i>&nbsp;添加支付宝账号
						</div>
					</a>
				</#if>
				<div class="weui-cell">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:70%" class="weui-input" id="verCode" name="verCode" placeholder="验证码" />
	            	<a href="javascript:void(0)" id="btn-time" onclick="sendMsg(this);" class="btn_message">获取验证码</a>
			    </div>
				<div class="weui-cell">
			        <div class="cash_biginput">
				          <span>￥</span>
				          <input class="weui-input" type="number" id="withdrawAmout" placeholder="提现金额">
			        </div>
			    </div>
			    <div class="weui-cell f14">
			    		可用余额：￥${(amount)?string('0.00')}元 &nbsp;&nbsp;&nbsp;&nbsp;最小提现金额：￥${(minWithdraw)?string('0.00')}元
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
			var phoneNo = "${phoneNo!''}";
			var minWithdraw = "${(minWithdraw)?string('0.00')}";
			minWithdraw = parseFloat(minWithdraw);
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
				if (withdrawAmout < minWithdraw) {
					alert("提现金额不能小于最小提现金额");
					$("#withdrawAmout").val("");
					return;
				}
				var code = $("#verCode").val();
				if (code == "") {
					alert("验证码不能为空!");
					return;
				}
				var userAlipayAccount = $("#userAlipayAccount").val();
				if (userAlipayAccount == "" || userAlipayAccount == null){
					alert("请先绑定支付宝账号!");
					return;
				}
				var config = {
					url : "${sysContextPath}/web/wallet/withdraw",
					postData : {"amount": withdrawAmout, "userAlipayAccount": userAlipayAccount, "code": code},
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
								var status = order.status;
								var statusStr = "提现中";
								if (status == 1) {
									statusStr = "已完成";
								}
								html += '<div class="weui-cell wallet_list">' + 
						            '<div class="weui-cell__bd">' +
				            			'<p>' + showStr + '</p>' +
						              	'<p class="gary">' + dateFtt('yyyy-MM-dd hh:mm:ss',new Date(order.createTime)) + '</p>' +
						            '</div>' +
						            '<div class="weui-cell__ft"><span>' + statusStr + '</span></div>' +
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
			
			
			var phone_check=false;
			 var wait=60; 
			function time(o) {
			    if (wait == 0) {
			    	phone_check=true;
			    	$(o).html("获取验证码");
			         wait = 60;
			         addClick();
			       } else {
			       		phone_check=false;
			         	$(o).html(wait+"秒重新获取");
			          wait--;
			          setTimeout(function() {
			              time(o)
			           },
			           1000)
			      }
			 }
			 function addClick(){
				 $("#btn-time").attr("onclick","sendMsg(this)");
			 }
			function sendMsg(obj){
				$(obj).attr("onclick","");
				var data=new Object();
				data.phoneNo=phoneNo;
				$.ajax({
					type:"POST",
					url: "${sysContextPath}/laserDirect/sms/getCode",
					async:false,
					data:data,
					dataType:"json",
					success : function(data, textStatus, jqXHR) {
							if(data.success){
								 time(obj);
							}else if(!data.success){
								alert(data.msg);
							}
						}
					});
			 
		 }
		</script>
	</body>
</html>