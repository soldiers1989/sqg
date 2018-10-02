<div class="page-content-wrapper">
    <div class="page-content">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <a href="${sysContextPath}/admin/index">首页</a>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <span>用户管理</span>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <span>用户列表</span>
                </li>
            </ul>
        </div>
        <h3 class="page-title"></h3>
        <div class="row">
            <div class="col-md-12">
                <div class="portlet light busered">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-settings font-green"></i>用户列表
						</div>
					</div>
					<div id="bs_searchForm"></div>
					<div class="portlet-body">
                        <div class="table-toolbar">
                            <div class="row">
                                <div class="col-md-12">
                                        <button id="save_btn" class="btn sbold green"> 添加
                                            <i class="fa fa-plus"></i>
                                        </button>
                                        <button id="edit_btn" class="btn sbold green"> 修改
                                            <i class="fa fa-edit"></i>
                                        </button>
                                       <button id="delete_btn" class="btn sbold green"> 删除
                                            <i class="fa fa-remove"></i>
                                        </button>
                                        <button id="show_btn" class="btn sbold green"> 查看
                                            <i class="fa fa-search"></i>
                                        </button>
                                        <button id="tuiguang_btn" class="btn sbold green"> 生成永久推广二维码
                                            <i class="fa fa-search"></i>
                                        </button>
                                        <button id="fangfa_btn" class="btn sbold green"> 发奖励金
                                            <i class="fa"></i>
                                        </button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-edit"></i>用户列表列表
								</div>
							</div>
							<div class="portlet-body">
						        <div class="table-scrollable" id="datagrid">
						        </div>
					        </div>
				        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

function initGrid() {
	$("#datagrid").bs_grid({
        ajaxFetchDataURL: "${sysContextPath}/admin/user/list.json",
        row_primary_key: "id",
        columns: [
            {field: "id", header: "用户Id"},
            {field: "parentId", header: "父用户Id"},
            {field: "userNickname", header: "用户昵称"},
            {field: "userPhone", header: "手机号", "sortable": "no"},
            {field: "userLevel", header: "用户等级"},
            {field: "createTime", header: "创建时间"},
        ],
         searching:[
             {searchingName: "用户id", field: "userId"},
             {searchingName: "用户昵称", field: "userNickname"}
         ],
         callbackDataFunction: function (dataobj) {
         	var data = dataobj;
         	if (dataobj.success) {
         		data["total"] = dataobj.data.length;
         		data["list"] =dataobj.data;
         	}
         	return data;
         }
    });
}


$(function() {
    
	initGrid();
    
    $("#save_btn").click(function(){
    	var attr = {url:"${sysContextPath}/admin/user/add",title:"添加用户列表",addInitFun:function(){
    	}}; 
    	addViewHtmlDialog(attr);
    });
    $("#delete_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
    		common_confirm("确定要删除吗？",function() {
        		var options = {
        				url : "${sysContextPath}/admin/user/delete.json",
        				postData : {id:id},
        				onSuccessFunction : function(data) {
        					if (data.success) {
        						callBackSuccess();initGrid();
        					}
        				}
        			};
            	myAjaxRequest(options);
        	});
    	}
    	
    });
    $("#edit_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
    		var attr = {url:"${sysContextPath}/admin/user/add?id="+id,title:"编辑用户",addInitFun:function(){
		    	var options = {
						url : "${sysContextPath}/admin/user/get.json?id="+id,
						onSuccessFunction : function(data) {
							bindUpdateValue(data.data);
						}
					};
		    	myAjaxRequest(options);
        	}}; 
        	addViewHtmlDialog(attr);
    	}
    	
    });
    $("#show_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
	    	var attr = {url:"${sysContextPath}/admin/user/view?id="+id,title:"查看用户"}; 
	    	showViewHtmlDialog(attr);
    	}
    });
    
    $("#tuiguang_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
		    	var options = {
						url : "${sysContextPath}/laserDirect/wx/generateWxLimitQrCode/"+id,
						onSuccessFunction : function(data) {
							if (data.success) {
								openDialog({
									msg : "<image height='270' width='270' src='"+data.data+"'>",
									size : 'small',
									title : "永久推广二维码"
								});
							}
						}
					};
		    	myAjaxRequest(options);
    	}
    });
    
    $("#fangfa_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
	    	openDialog({
				msg : '<input type="text" class="form-control" placeholder="请输入发放金额" name="sendAmount" id="sendAmount">',
				size : 'small',
				title : "发放奖励金",
				type : 'confirm',
				callFun : function (status) {
					if (status) {
						var options = {
							url : "${sysContextPath}/admin/user/sendAmount.json?id=" + id + "&amount=" + $("#sendAmount").val(),
							onSuccessFunction : function(data) {
								if (data.success) {
									common_alert_success("发放成功");
								}
							}
						};
				    	myAjaxRequest(options);
					}
				}
			});
    	}
    });
    
});
</script>