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
		    <div class="weui-panel weui-panel_access"  style="overflow-y:auto; overflow-x:hidden;height:900px;">
				<div class="weui-panel__hd">邀请好友人数:<font color="red" id="total"></font>人</div>
				<div class="weui-panel__bd" id="walletlist">
				</div>
				<!--向下滚动加载样式-->
				<div class="weui-loadmore" id="weui-loadmore"  style="display: none;">
			      <i class="weui-loading"></i>
			      <span class="weui-loadmore__tips">正在加载</span>
			    </div>
			    <div class="weui-loadmore weui-loadmore_line" id="weui-loadmore_line" style="display: none;">
				  <span class="weui-loadmore__tips">没有更多数据</span>
				</div>
			</div>
		</div>
		<!--引用 js-->
		<script>
			var currentPage = 1;
			var rows = 10;
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
					url : "${sysContextPath}/web/friend/list.json",
					postData : data,
					dataType:"json",
					onSuccessFunction : function(data) {
						var html="";
						if(data!=null){
							var list = data.list;
							var total = data.total;
							$("#total").html(total);
							$.each(list,function(i,user){
								var userImgurl = user.userImgurl;
								if (userImgurl == undefined){
									userImgurl = "${domainName}/static/images/user_pic.png";
								}
								var userNickname = user.userNickname;
								if (userNickname == undefined) {
									userNickname == "未知";
								}
								var friendAccount = user.friendAccount;
								html += '<div class="weui-cell wallet_list">' + 
						            '<div class="weui-cell__hd">' +
						            '<span class="user_pic"><img src="' + userImgurl + '"></span>'+ 
						            '</div>' +
						            '<div class="weui-cell__bd"><span>' + userNickname + '</span></div>' +
						            '<div class="weui-cell__bd"><span>成功邀请:' + friendAccount + '人</span></div>' +
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
			$(".weui-panel_access").infinite().on("infinite", function() {
			  if(loading) return;
				  loading = true;
				 currentPage=currentPage+1;
				 load("");
			});
		</script>
	</body>
</html>