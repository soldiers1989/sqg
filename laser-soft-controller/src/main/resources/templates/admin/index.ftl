<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>开发管理平台</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
		<#include 'inc.ftl' />	
    </head>
    <!-- END HEAD -->
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
		 <#include 'layout/head.ftl' />
        <div class="clearfix"> </div>
        <div class="page-container">
            <div class="page-sidebar-wrapper">
                <div class="page-sidebar navbar-collapse collapse">
                    <#include 'layout/menu.ftl' />
                </div>
            </div>
            <div class="page-content-wrapper" id="rootMenu">
                <#include 'layout/center.ftl' />
            </div>
        </div>
        <jsp:include page="layout/footer.ftl"></jsp:include>
    </body>
</html>
<script>
</script>