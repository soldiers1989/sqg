
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>开发管理平台登录</title>
		<#include 'login_inc.ftl' />	

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo">
            <a href="index.html">
                <img src="${domainName}/static/assets/pages/img/logo-big.png" alt="" /> </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" id="login-form" method="post" action="" target="form_iframe">
                <h3 class="form-title font-green">登录</h3>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">用户名</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" id="code" autocomplete="off" placeholder="用户名" name="code" /> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" id="pwd" autocomplete="off" placeholder="密码" name="pwd" /> </div>
                <div class="form-actions" style="text-align: center;">
                    <button type="button" class="btn green uppercase" onclick="login()">登录</button>
                </div>
            </form>
        </div>
        <div class="copyright"> 开发管理平台 </div>
        <iframe name="form_iframe" style="display:none;"></iframe>
    </body>
</html>

<script>
function login() {
	var code = $("#code").val();
	var pwd = $("#pwd").val();
	if (code == null || code == "") {
		common_alert_error("请输入用户名");
		return;
	}
 	$.ajax({
		type:"GET",
		url: "${sysContextPath}/admin/login.json?code=" + code + "&pwd=" + pwd,
		contentType:"text/html",
		success : function(data, textStatus, jqXHR) {
			if (data.success) {
				window.location.href = "${sysContextPath}/admin/index";
			}else {
				common_alert_error("账号或密码错误");
			}
		}});
}

</script>
