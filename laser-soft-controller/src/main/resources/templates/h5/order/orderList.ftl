<#include '../h5indexHead.ftl' />
<div class="weui-tab">
		<div class="weui-navbar order_navbar">
			<div class="weui-navbar__item" href="#ordertab1" id="ordertab1_on" onclick="showtab('ordertab1')">
				全部
			</div>
			<div class="weui-navbar__item" href="#ordertab2" id="ordertab2_on" onclick="showtab('ordertab2')">
				已付款
			</div>
			<div class="weui-navbar__item" href="#ordertab3" id="ordertab3_on" onclick="showtab('ordertab3')">
				已结算
			</div>
			<div class="weui-navbar__item" href="#ordertab4" id="ordertab4_on" onclick="showtab('ordertab4')">
				已失效
			</div>
		</div>
			
		<div class="weui-tab__bd">
				<!--全部订单-->
				<div id="ordertab1" class="weui-tab__bd-item  weui-pull-to-refresh" dataState="">
					<div class="weui-pull-to-refresh__layer">
						<div class='weui-pull-to-refresh__arrow'></div>
						<div class='weui-pull-to-refresh__preloader'></div>
						<div class="down">下拉刷新</div>
						<div class="up">释放刷新</div>
						<div class="refresh">正在刷新</div>
					</div>
					<div id="list">
					 
					</div>
					<div class="weui-loadmore">
					  <i class="weui-loading"></i>
					  <span class="weui-loadmore__tips">正在加载</span>
					</div>
				    <div class="weui-loadmore weui-loadmore_line"  style="display: none;">
					  <span class="weui-loadmore__tips">没有更多数据</span>
					</div>
				</div>
				<!--全部订单   end-->
				
			 
				<!--待付款订单-->
				<div id="ordertab2" class="weui-tab__bd-item  weui-pull-to-refresh" dataState="12">
					<div class="weui-pull-to-refresh__layer">
						<div class='weui-pull-to-refresh__arrow'></div>
						<div class='weui-pull-to-refresh__preloader'></div>
						<div class="down">下拉刷新</div>
						<div class="up">释放刷新</div>
						<div class="refresh">正在刷新</div>
					</div>
					<div id="list">
					 
					</div>
					<div class="weui-loadmore" >
					  <i class="weui-loading"></i>
					  <span class="weui-loadmore__tips">正在加载</span>
					</div>
				    <div class="weui-loadmore weui-loadmore_line"  style="display: none;">
					  <span class="weui-loadmore__tips">没有更多数据</span>
					</div>
				</div>
				<!--待付款订单  end-->
				<!--待收货订单-->
				<div id="ordertab3" class="weui-tab__bd-item weui-pull-to-refresh" dataState="3">
					<div class="weui-pull-to-refresh__layer">
						<div class='weui-pull-to-refresh__arrow'></div>
						<div class='weui-pull-to-refresh__preloader'></div>
						<div class="down">下拉刷新</div>
						<div class="up">释放刷新</div>
						<div class="refresh">正在刷新</div>
					</div>
					<div id="list">
					 
					</div>
					<div class="weui-loadmore" >
					  <i class="weui-loading"></i>
					  <span class="weui-loadmore__tips">正在加载</span>
					</div>
				    <div class="weui-loadmore weui-loadmore_line"  style="display: none;">
					  <span class="weui-loadmore__tips">没有更多数据</span>
					</div>
				</div>
				<!--待收货订单  end-->
				<!--已完成订单-->
				<div id="ordertab4" class="weui-tab__bd-item weui-pull-to-refresh" dataState="13">
					<div class="weui-pull-to-refresh__layer">
						<div class='weui-pull-to-refresh__arrow'></div>
						<div class='weui-pull-to-refresh__preloader'></div>
						<div class="down">下拉刷新</div>
						<div class="up">释放刷新</div>
						<div class="refresh">正在刷新</div>
					</div>
					<div id="list">
					 
					</div>
					<div class="weui-loadmore" >
					  <i class="weui-loading"></i>
					  <span class="weui-loadmore__tips">正在加载</span>
					</div>
				    <div class="weui-loadmore weui-loadmore_line"  style="display: none;">
					  <span class="weui-loadmore__tips">没有更多数据</span>
					</div>
				</div>
				<!--已完成订单  end-->
				
				
			</div>
		</div>
		<script>
		

var currentPage = 1;
var rows = 10;
var loading=false;
var dataStste="";
var tabNum = "${tabNum!'1'}";
	$(document).ready(function(){
		$("#ordertab"+tabNum + "_on").addClass("weui-bar__item--on");
		$("#ordertab"+tabNum).addClass("weui-tab__bd-item--active");
		showtab('ordertab'+tabNum);
	})		
			$(function() {
				FastClick.attach(document.body);
				
			});
			
			function showtab(tabid){
				dataStste = $("#"+tabid).attr("dataState");
				currentPage =1;
				load('pull',$("#"+tabid));
			}
			
			//上拉刷新页面
			$('.weui-tab__bd-item').pullToRefresh().on('pull-to-refresh', function(done) {
				dataStste = $(".weui-tab__bd-item--active").attr("dataState");
				currentPage=1;
				load('pull',$(this));
				$(this).pullToRefreshDone();
			})
			
			function load(flag,dom){
			var data = {"pageNum":currentPage,"rows":rows,"tradeStatus":dataStste};
				var config = {
					url : "${sysContextPath}/web/order/list.json",
					postData : data,
					dataType:"json",
					onSuccessFunction : function(data) {
						var html="";
						if(data!=null){
							$.each(data,function(i,order){
								var state=""; 	 
								var settelHtml = "";
								var settelDateHtml = "";
								if (order.tradeStatus == 12) {
									state = "已付款";
								}else if (order.tradeStatus == 3) {
									state = "已结算";
									settelHtml="<div class='shopcart_price'>"+
															"结算金额 "+
															"<b>￥"+  Number(order.alipayTotalPrice).toFixed(2) +"</b>"+
														"</div>"+
														"<div class='shopcart_number' >"+
															"<div class='shopcart_price'>"+
																"&nbsp;&nbsp;结算奖励金"+
																"<b>￥"+ Number(order.commissionSamount).toFixed(2) +"</b>"+
															"</div>"+
														"</div>";
														
									settelDateHtml = "<span style='float:right'>"+dateFtt('yyyy-MM-dd hh:mm:ss',new Date(order.earningTime)) + " 结算</span>";
								}else if (order.tradeStatus == 13) {
									state = "已失效";
								}
								
								 html= html+"<div class='content-padded'>"+
								"<div class='weui-panel weui-panel_access'>"+
								"<a href='' class='weui-panel__hd'>"+
								"<div class='shop_name_text text_overflow'>"+
								"<i class='iconfont icon-dianpu'></i>" +order.sellerName +
								"</div>"+
								"<div class='order_status text_overflow orange'>"+ state +"</div></a>";
										
								html= html+"<a href='' class='weui-panel__bd'>"+
											"<div class='weui-media-box weui-media-box_appmsg'>"+
												"<div class='weui-media-box__hd'>"+
													"<img class='weui-media-box__thumb' src='"+ order.itemImage +"' />"+
												"</div>"+
												"<div class='weui-media-box__bd'>"+
													"<h4 class='weui-media-box__title' style='white-space:normal'>"+ order.itemTitle + "</h4>"+
													"<div class='shopcart_info'>"+
														"<div class='shopcart_price'>"+
															"付款金额 "+
															"<b>￥"+  Number(order.alipayTotalPrice).toFixed(2) +"</b>"+
														"</div>"+
														"<div class='shopcart_number' >"+
															"<div class='shopcart_price'>"+
																"&nbsp;&nbsp;预估奖励金"+
																"<b>￥"+ Number(order.commissionAmount).toFixed(2) +"</b>"+
															"</div>"+
														"</div>"+
														settelHtml +
													"</div>"+
												"</div>"+
											"</div>"+
										"</a>";
									html=html+"<div class='order_btn' style='font-size:12px'><span>"+dateFtt('yyyy-MM-dd hh:mm:ss',new Date(order.tradeTime)) + " 创建</span>"+settelDateHtml+"</div></div>"+
											"</div>"+
										"</div>"+
									"</div>";
							})
						}
						if(flag=='pull'){//下拉刷新
							$(dom).find("#list").html(html);
						}else{//上拉加载
							$(dom).find("#list").append(html);
						}
						if(data==''||data.length<rows) {
							$(dom).find(".weui-loadmore").hide();
							$(dom).find(".weui-loadmore_line").show();
						}else{
							loading = false;
						}
					},
					mask : false
				};
				myAjaxRequest(config);
			}
			
			//下拉加载页面
			$(".weui-tab__bd-item").infinite().on("infinite", function() {
			  if(loading) return;
			  loading = true;
			  
			  var self= $(".weui-tab__bd-item--active");
			  dataStste = $(".weui-tab__bd-item--active").attr("dataState");
			  
			  
			 currentPage=currentPage+1;
			 load("",self);
			});
			 
		</script>