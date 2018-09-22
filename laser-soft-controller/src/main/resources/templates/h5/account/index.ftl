<#include '../h5indexHead.ftl' />
<body ontouchstart>
<div class="content">
	<div class="account_bg">
		<div class="user_info">
			<div class="user_pic">
				<#if user?exists>
					<img src="${user.userImgurl}" />
				<#else>
					<img src="/images/user_pic.png" />
				</#if>
			</div>
			<div  class="user_text">
				<h2>
					<#if user?exists >
						${user.userNickname?default(userName)}
					<#else>
						${userName?default('游客')}	
					</#if>
					<span class="vip_span">vip${user.userLevel}</span>
				</h2>
				<div>
					账户余额￥${(amount)?string('0.00')}
				</div>
			</div>
		</div>
	</div>
	<div class="weui-cells">
	  <a class="weui-cell weui-cell_access" href="${sysContextPath}/web/order/index">
           <div class="weui-cell__hd">
           		<span class="round_icon round_icon01"><i class="iconfont icon-wodedingdan"></i></span>
           </div>
           <div class="weui-cell__bd">
             <p>我的订单</p>
           </div>
           <div class="weui-cell__ft">全部订单 </div>
         </a>
	  <a class="weui-cell weui-cell_access" href="${sysContextPath}/web/trade/account/wallet">
           <div class="weui-cell__hd">
           		<span class="round_icon round_icon02"><i class="iconfont icon-qianbao-copy"></i></span>
           </div>
           <div class="weui-cell__bd">
             <p>我的钱包</p>
           </div>
           <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${sysContextPath}/web/coupon/index">
           <div class="weui-cell__hd">
           		<span class="round_icon round_icon03"><i class='iconfont icon-youhuiquan'></i></span>
           </div>
           <div class="weui-cell__bd">
             <p>转券记录</p>
           </div>
           <div class="weui-cell__ft"></div>
          </a>
          
        </div>
        <div class="weui-cells">
        	<a class="weui-cell weui-cell_access" href="">
           <div class="weui-cell__hd">
           		<span class="round_icon round_icon04"><i class="iconfont icon-gerenxinxi"></i></span>
           </div>
           <div class="weui-cell__bd">
             <p>关于我们</p>
           </div>
           <div class="weui-cell__ft"></div>
          </a>
        </div>
</div>
</body>
</html>