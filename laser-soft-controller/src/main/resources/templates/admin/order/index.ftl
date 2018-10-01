<div class="page-content-wrapper">
    <div class="page-content">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <a href="${sysContextPath}/admin/index">首页</a>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <span>订单管理</span>
                    <i class="fa fa-circle"></i>
                </li>
                <li>
                    <span>订单列表</span>
                </li>
            </ul>
        </div>
        <h3 class="page-title"></h3>
        <div class="row">
            <div class="col-md-12">
                <div class="portlet light bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-settings font-green"></i>订单列表
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
                                            <i class="fa fa-edit"></i>
                                        </button>
                                        <button id="show_btn" class="btn sbold green"> 查看
                                            <i class="fa fa-search"></i>
                                        </button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="portlet box green">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-edit"></i>订单列表列表
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
        ajaxFetchDataURL: "${sysContextPath}/admin/order/list.json",
        row_primary_key: "id",
        columns: [
            {field: "itemTitle", header: "商品名称"},
            {field: "userId", header: "用户Id"},
            {field: "tradeTime", header: "订单时间"},
            {field: "tradeStatus", header: "订单状态", "sortable": "no"}
        ],
         searching:[
             {searchingName: "用户id", field: "userId"},
             {searchingName: "商品名称", field: "itemTitle"}
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
    	var attr = {url:"${sysContextPath}/admin/order/add",title:"添加订单列表",addInitFun:function(){
    	}}; 
    	addViewHtmlDialog(attr);
    });
    $("#delete_btn").click(function(){
    	if (checkSelect()) {
    		var id = getSelectId();
    		common_confirm("确定要删除吗？",function() {
        		var options = {
        				url : "${sysContextPath}/admin/order/delete.json",
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
    		var attr = {url:"${sysContextPath}/admin/order/add?id="+id,title:"编辑订单列表",addInitFun:function(){
		    	var options = {
						url : "${sysContextPath}/admin/order/get.json?id="+id,
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
	    	var attr = {url:"${sysContextPath}/admin/order/view?id="+id,title:"查看订单列表"}; 
	    	showViewHtmlDialog(attr);
    	}
    });
});
</script>