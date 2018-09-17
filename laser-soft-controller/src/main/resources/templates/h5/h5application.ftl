<#include '/h5indexHead.ftl' />
<body ontouchstart>
<!--content-->
<div class="content">
			<div class="account_bg">
				<div class="user_info">
					<div class="user_pic">
						<#if user?exists>
							<img src="${user.userImgurl?default(jsRoot+'/images/user_pic.png')}" />
						<#else>
							<img src="${jsRoot}/images/user_pic.png" />
						</#if>
						
					</div>
					<h2 class="user_text">
						<#if user?exists >
							${user.userNickname?default(userName)}
						<#else>
							${userName?default('游客')}	
						</#if>
					</h2>
				</div>
			</div>
			<div class="weui-cells">
			  <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/order/mng/myOrderList">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon01"><i class="iconfont icon-wodedingdan"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>我的订单</p>
	            </div>
	            <div class="weui-cell__ft">全部订单 </div>
	          </a>
			  <div class="weui-cell">
			    <a href="${sysAppContextPath}/web/order/mng/myOrderList?tabNum=2" class="account_ordermenu">
			    		<i class="iconfont icon-daifukuan"></i>
			    		<p>待付款</p>
			    </a>
			    <a href="${sysAppContextPath}/web/order/mng/myOrderList?tabNum=3" class="account_ordermenu">
			    		<i class="iconfont icon-daishouhuo"></i>
			    		<p>待收货</p>
			    </a>
			    <a href="${sysAppContextPath}/web/order/mng/myOrderList?tabNum=4" class="account_ordermenu">
			    		<i class="iconfont icon-icontuihuan"></i>
			    		<p>已完成</p>
			    </a>
			  </div>
			</div>
			<div class="weui-cells">
			  <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/trade/account/wallet">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon02"><i class="iconfont icon-qianbao-copy"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>我的钱包</p>
	            </div>
	            <div class="weui-cell__ft">账户余额${(user.faccountAmount)?default('0.00')}</div>
	           </a>
	           
	           <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/cop/coupon/myCouponList">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon06"><i class="iconfont icon-youhuiquan"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>我的优惠券</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	           </a>
	           
	           <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/trade/account/safe">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon03"><i class="iconfont icon-anquan"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>账户与安全</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	           </a>
	           <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/trade/account/myinfo">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon04"><i class="iconfont icon-gerenxinxi"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>个人资料</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	           </a>
	            <a class="weui-cell weui-cell_access" href="${sysAppContextPath}/web/trade/withdrawcard/bankeditlist">
	            <div class="weui-cell__hd">
	            		<span class="round_icon round_icon05"><i class="iconfont icon-bankcard"></i></span>
	            </div>
	            <div class="weui-cell__bd">
	              <p>银行卡设置</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	           </a>
	         </div>
		</div>
</body>
</html>