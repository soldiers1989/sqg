<#include '../h5indexHead.ftl' />
<div class="weui-tab">
			
		<div class="weui-tab__bd">
				<!--全部订单-->
				<div id="ordertab1" class="weui-tab__bd-item  weui-pull-to-refresh" dataState="">
					<div class="weui-pull-to-refresh__layer" style="display:none">
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
				currentPage =1;
				load('pull',$("#"+tabid));
			}
			
			//上拉刷新页面
			$('.weui-tab__bd-item').pullToRefresh().on('pull-to-refresh', function(done) {
				currentPage=1;
				load('pull',$(this));
				$(this).pullToRefreshDone();
			})
			
			function load(flag,dom){
			var data = {"pageNum":currentPage,"rows":rows};
				var config = {
					url : "${sysContextPath}/web/coupon/list.json",
					postData : data,
					dataType:"json",
					onSuccessFunction : function(data) {
						var html="";
						var nowTime = new Date().getTime();
						if(data!=null){
							$.each(data,function(i,order){
								var haveCoupon = order.couponExist;
								var deleteAmount  = "";
								var couponAmount  = "<i class='iconfont icon-youhuiquan'> </i>无优惠券";
								var tklSX = true;
								if (nowTime -(15 * 60 * 60 * 24 * 1000) > order.createTime) {
									var tklHave = "<a id='tklcopy"+order.id+"' class='weui-btn weui-btn_mini weui-btn_plain-default'>淘口令失效<a/>";
									tklSX = false;
								}else {
									var tklHave = "<a id='tklcopy"+order.id+"' class='weui-btn weui-btn_mini weui-btn_plain-primary'>一键复制淘口令<a/>";
								}
								
								if (haveCoupon && haveCoupon=='1') {
									var amountStr = '券后价';
									var amount = (Number(order.itemPrice).toFixed(2) - Number(order.couponAmout).toFixed(2)).toFixed(2);
									deleteAmount = "<span style = 'text-decoration:line-through;font-size:10px;color:#CDCDC1';>" + Number(order.itemPrice).toFixed(2) + "</span>";
									couponAmount = "<span style = 'font-size:10px;color:#FF3030';><i class='iconfont icon-youhuiquan'> </i>" + Number(order.couponAmout).toFixed(2) + "元</span>";
								}else {
									var amountStr = '原价';
									var amount = Number(order.itemPrice).toFixed(2);
									
								}
								 
								
								 html= html+"<div class='content-padded'>"+
								"<div class='weui-panel weui-panel_access'>";
										
								html= html+"<a href='' class='weui-panel__bd'>"+
											"<div class='weui-media-box weui-media-box_appmsg'>"+
												"<div class='weui-media-box__hd'>"+
													"<img class='weui-media-box__thumb' src='"+ order.itemImage +"' />"+
												"</div>"+
												"<div class='weui-media-box__bd'>"+
													"<h4 class='weui-media-box__title'>"+ order.itemTitle + "</h4>"+
													"<div class='shopcart_info'><div class='shopcart_price'>"+couponAmount+"</div></div>"+
													"<div class='shopcart_info'>"+
														"<div class='shopcart_price'>"+
															amountStr+
															"<b>￥"+  amount + "&nbsp;" + deleteAmount  +"&nbsp;&nbsp;</b>"+
														"</div>"+
														"<div class='shopcart_number' >"+
															"<div class='shopcart_price'>"+
																"预估奖励金"+
																"<b>￥"+ Number(order.commission).toFixed(2)+"</b>"+
															"</div>"+
														"</div>"+
													"</div>"+
												"</div>"+
											"</div>"+
										"</a>";
									html=html+"<div class='order_btn' style='font-size:13px'><div style='float:left;margin-top: 10px;'>转券时间"+
												dateFtt('yyyy-MM-dd hh:mm:ss',new Date(order.createTime)) + 
												"</div><div style='float:right'>"+tklHave+"</div></div></div>"+
											"</div>"+
										"</div>"+
									"</div>";
									if (tklSX) {
									 new ClipboardJS('#tklcopy'+order.id, {
								        text: function() {
								            return order.tkl;
								        }
								    }).on('success', function(e) {
								        $.toast("复制成功", 1000);
								    });
									
									}
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
			 currentPage=currentPage+1;
			 load("",self);
			});
			 
		</script>