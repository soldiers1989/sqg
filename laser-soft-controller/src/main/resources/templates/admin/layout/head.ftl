<div class="page-header navbar navbar-fixed-top">
    <!-- BEGIN HEADER INNER -->
    <div class="page-header-inner ">
        <div class="page-logo">
            <a href="${sysContextPath}/admin/index">
                <img src="${domainName}/static/assets/layouts/layout/img/logo.png" alt="logo" class="logo-default"> </a>
            <div class="menu-toggler sidebar-toggler"> </div>
        </div>
        <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
        <div class="top-menu">
            <ul class="nav navbar-nav pull-right">
                <li class="dropdown dropdown-user">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        <img alt="" class="img-circle" src="${domainName}/static/assets/layouts/layout/img/avatar3_small.jpg">
                        <span class="username username-hide-on-mobile">管理员</span>
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-default">
                    </ul>
                </li>
                <li class="dropdown dropdown-quick-sidebar-toggler">
                    <a href="javascript:void(0)" class="dropdown-toggle" onclick="logout()">
                        <i class="icon-logout"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
function logout() {
	$.ajax({
		type:"GET",
		url: "${sysContextPath}/sys/baseUserAction!notSession_logout.action",
		dataType:"script",
		contentType:"html",
		success : function(data, textStatus, jqXHR) {
			window.location.href = "${sysContextPath}/admin/login";
		},
		error : function(data, textStatus, jqXHR) {
			window.location.href = "${sysContextPath}/admin/login";
		}});
}
function changeEnv(env) {
	setCookie("env",env);
	window.location.href = "${sysContextPath}/admin/index";
}
function setCookie(name,value){
   var Days = 30*12;  //cookie 将被保存一年
   var exp = new Date(); //获得当前时间
   exp.setTime(exp.getTime() + Days*24*60*60*1000); //换成毫秒
   document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
 } 
</script>

