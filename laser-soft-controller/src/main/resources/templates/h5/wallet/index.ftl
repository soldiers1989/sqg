<#include '../h5indexHead.ftl' />
<body ontouchstart>
		<!--底部固定按钮-->
		<div class="function_block_btn">
			<a href="${sysContextPath}/web/wallet/cash" onclick="" class="weui-btn  weui-btn_plain-primary">提 现</a>
		</div>
		<!--底部固定按钮  end-->
		<!--content-->
		<div class="content">
			<div class="wallet_bg">
				<h2 class="wallet_money">
					账户余额
					<p>￥<b>${(amount)?string('0.00')}</b></p>
				</h2>
				<p class="wallet_text"><i class="iconfont icon-anquan"></i>每月25号结算上月预估奖励金到余额，本月奖励金预估在下月25号进行结算到</p>
			</div>
			<div class="weui-panel weui-panel_access">
				<div class="weui-panel__bd" id="walletlist">
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>本月预估奖励金&nbsp;<span class="vip_span" style="background-color:#00B2EE;border-radius:10px;">&nbsp;待结算&nbsp;</span></p>
			            </div>
			            <div class="weui-cell__ft red">￥<span>${(nowSum['sumAmount'])?string('0.00')}</span></div>
			        </div>
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>上月预估奖励金&nbsp;<span class="vip_span" style="background-color:#228B22;border-radius:10px;">&nbsp;本月21号结算&nbsp;</span></p>
			            </div>
			            <div class="weui-cell__ft red">￥<span>${(nowSum_1['sumAmount'])?string('0.00')}</span></div>
			        </div>
				</div>
			</div>
			<div class="weui-panel weui-panel_access">
				<div class="weui-panel__bd" id="walletlist">
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>今日预估奖励金</p>
			            </div>
			            <div class="weui-cell__ft black">￥<span>${(now['sumAmount'])?string('0.00')}</span></div>
			        </div>
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>今日成交订单</p>
			            </div>
			            <div class="weui-cell__ft black"><span>${now['count']!''}</span></div>
			        </div>
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>昨日预估奖励金</p>
			            </div>
			            <div class="weui-cell__ft black">￥<span>${(now_1['sumAmount'])?string('0.00')}</span></div>
			        </div>
					<div class="weui-cell wallet_list">
			            <div class="weui-cell__bd">
			              <p>昨日成交订单</p>
			            </div>
			            <div class="weui-cell__ft black"><span>${now_1['count']!''}</span></div>
			        </div>
				</div>
			</div>
		</div>
		
		<!--引用 js-->
		<script>
			function withdraw() {
					$.alert("<image src='${sysContextPath}/static/images/wx_logo.png'/><p>客服微信号：taotao12345</p><p>长按识别二维码</p>", "提现申请请联系客服");
			}
		</script>
	</body>
</html>