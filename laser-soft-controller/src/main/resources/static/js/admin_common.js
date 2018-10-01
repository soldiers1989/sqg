var selectDataObject;

var illegal_check = {regexp: /^[a-zA-Z0-9_]+$/,message: '只能是数字、字母、下划线'};
var range_check = {regexp: /^[\u4e00-\u9fa5a-zA-Z0-9_]+$/, message: '只能是中文、数字、字母、下划线'};
var mobile_check = {regexp: /^1(3|4|5|7|8)\d{9}$/, message: '手机号码格式不正确'};
var email_check = {regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '邮箱格式不正确'};

function common_formtojson(formid){
	var arr=$('#'+formid).serializeArray();
	var jsonStr = "";
	jsonStr += '{';
	for (var i = 0; i < arr.length; i++) {
		jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",';
	}
	jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
	jsonStr += '}';
	var json = JSON.parse(jsonStr);
	return json;
}

/* 返回key-value集合*/
function getFormData( jqueryCollection ) {
	return $.param(jqueryCollection.formToArray()); //适用于jquery格式
}

/***
 * AJAX请求，返回JSON数据
 * @param par :url,postData,onSuccessFunction
 * 
 * @return
 */
function myAjaxRequest(par){
	if(par.imgPath == undefined){
		par.imgPath = "../images";
	}
	if(par.appPath == undefined){
		//par.appPath = "/exchange";
		par.appPath = "";
	}
	if(par.dataType == undefined){
		par.dataType = "json";
	}
	if(par.contentType == undefined){
		par.contentType = "application/x-www-form-urlencoded";
	}
	var url = par.url;
	
	var addRemote = par.addRemote;
	
	if (addRemote) {
		url = "http://139.196.4.84:9981" + url;
	}
	
	var posturl = url;
	
	var postData = par.postData;
	var proxyFlag = par.proxyFlag;
	if (proxyFlag==null || undefined == proxyFlag) {
		proxyFlag = false;
	}
	if (proxyFlag) {
		var posturl =  _contextPath+"/AjaxProxy";
		if (postData == undefined) {
			postData = new Object();
		}
		postData["ajaxUrl"] = url;
		if (typeof postData == "string") {
			postData = postData + "&ajaxUrl=" + url;
		}
	}
	var contentType = par.contentType;
	var onSuccessFunction = par.onSuccessFunction;
	var async = par.async ;
	var mask = par.mask;
	var imgPath = par.imgPath;
	var appPath = par.appPath;
	var dataType = par.dataType;
	if (async==null || async == undefined){
		async = true;//默认不同步
	}
	if(mask){
		$(document.body).mask("请求处理中...");
	}
	if (par.ajaxSubmit) {
		var ajax_option={
				url:posturl,// 默认是form action
				success:onSuccessFunction
		}
		par.submitForm.ajaxSubmit(ajax_option);
	}else {
		$.ajax({
			timeout:300000, //5分钟
			type:"POST",
			async:async,
			url: posturl,
			data:postData,
			dataType:dataType,
			contentType:contentType,
			success : function(data, textStatus, jqXHR) {
				if (dataType == "html") {
					if (data.indexOf("result") == 2) {
						data = eval("(" + data + ")");
					}
				}
				if (mask) {
					$(document.body).mask("操作完成!"); 
				}
				if (onSuccessFunction) {
					onSuccessFunction(data);
				}
				if (mask) {
					setTimeout(function() {
						$(document.body).unmask();
					}, 300);
				}
			},
			error:function(jqXHR, textStatus, errorThrown){
				if (textStatus=='timeout'){
					//alert("连接超时，请检查网络");
				}else if (textStatus=='error'){
					//alert("请求错误");
				}else if (textStatus=='abort'){
					//alert("请求中止");
				}else{
					common_alert_error(textStatus+",HTTP error occurs"+errorThrown,"提示");
				}
			},
			statusCode:{404:function(){common_alert_error("404,页面不存在"+this.url,"提示");},
				500:function(){common_alert_error("500,服务器内部错误"+this.url,"提示");}}
		});
		
	}
	
	
}

function common_alert_error(msg, title) {
	common_alert(msg, "error", title)
} 
function common_alert_success(msg, title) {
	common_alert(msg, "success", title)
} 
function common_alert_warning(msg, title) {
	common_alert(msg, "warning", title)
} 

function common_alert(msg, type, title) {
	if (title == undefined || title == "") {
		title = "提示信息";
	}
	var attr = {
			type : "alert",
			title : title,
			message : msg
	};
	if (type == undefined || type == "") {
		type = "info";
	}
	//"warning", "error", "success" and "info"
	swal({
		title : title,
		text : msg,
		type : type,
		confirmButtonText : "确定"
	});
}

function common_confirm_tt(msg, type, title, callbackFun) {
	if (title == undefined || title == "") {
		title = "提示信息";
	}
	if (type == undefined || type == "") {
		type = "info";
	}
	swal({
        title: title,
        text: msg,
        type: type, 
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "确定",
        closeOnConfirm: true
    }, callbackFun);
}
function common_confirm(msg, callbackFun) {
	swal({
        title: "操作提示",
        text: msg,
        type: "info", 
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: true
    }, callbackFun);
}

function testOpenDialogHtml() {
	var attr = {url:"/cdp-manage/new/layout/center.jsp"}; 
	openDialogHtml(attr);
}

function openDialogHtml(attr) {
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}

function openSelectDialogHtml(attr) {
	attr.type="confirm";
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}

function openDialogHtmlLarge(attr) {
	attr.size = "large";
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}

function openDialogHtmlSmall(attr) {
	attr.size = "small";
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}



function openDialog(attr) {
	var type = attr.type;
	if (type == undefined || type == null) {
		type = "dialog";
	}
	var szie = attr.size;
	var msg = attr.msg;
	var title = attr.title;
	var callFun = attr.callFun;
	var cancelCallFun = attr.cancelCallFun;
	if (title == undefined || title == "") {
		title = "提示信息";
	}
	var buttons = attr.buttons;
	if (type == "alert") {
		bootbox.alert({
			buttons : {
				ok : {
					label : '确定',
					className : 'btn-primary'
				}
			},
			size : szie,
			message : msg,
			callback : callFun,
			title : title,
		}); 
	}else if (type == "confirm") {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : '确认',
					className : 'btn-primary'
				},
				cancel : {
					label : '取消',
					className : 'btn-default'
				}
			},
			size : szie,
			message : msg,
			callback : callFun,
			title : title
		}); 
	} else if (type == "dialog") {
		bootbox.dialog({
			message : msg,
			size : szie,
			title : title,
			buttons : buttons
		});
	}

 
}


function showDefaultTree(url) {
	var options = {
			url : url,
			onSuccessFunction : function(data) {
				$('#tree_1').jstree({
				    "core" : {
				        "themes" : {
				            "responsive": false
				        },
				        "data": data
				    },
				    "types" : {
				        "default" : {
				            "icon" : "fa fa-folder icon-state-warning icon-lg"
				        },
				        "file" : {
				            "icon" : "fa fa-file icon-state-warning icon-lg"
				        }
				    },
				    "plugins": ["types"]
				});
			}
		};
	myAjaxRequest(options);
}
function showCheckTree(url) {
	var options = {
			url : url,
			onSuccessFunction : function(data) {
				$('#tree_1').jstree({
				    "core" : {
				        "themes" : {
				            "responsive": false
				        },
				        "data": data
				    },
				    "types" : {
				        "default" : {
				            "icon" : "fa fa-folder icon-state-warning icon-lg"
				        },
				        "file" : {
				            "icon" : "fa fa-file icon-state-warning icon-lg"
				        }
				    },
				    "plugins": ["wholerow", "checkbox"],
				});
			}
		};
	myAjaxRequest(options);
}

function showDefaultTreeEV(url) {
	var options = {
			url : url,
			onSuccessFunction : function(data) {
				$('#tree_1').jstree({
				    "core" : {
				        "themes" : {
				            "responsive": false
				        },
				        "data": data
				    },
				    "types" : {
				        "default" : {
				            "icon" : "fa fa-folder icon-state-warning icon-lg"
				        },
				        "file" : {
				            "icon" : "fa fa-file icon-state-warning icon-lg"
				        }
				    },
				    "plugins": ["types"]
				});
				$('#tree_1').bind('click.jstree', function(event) {
					var eventNodeName = event.target.nodeName;
					if (eventNodeName == 'INS') {
						return;
					} else if (eventNodeName == 'A') {
						
						var $subject = $(event.target).parent();
						if ($subject.find('ul').length > 0) {
						} else {
							// 选择的id值
							alert($(event.target).parents('li').attr('id'));
						}
					}
					
				});
				$('#tree_1').bind("select_node.jstree", function (e, data) {  
					  
				    if(data.node.id !=1 ){                           //排除第一个节点(2011民事案由)  
				        data.instance.toggle_node(data.node);        //单击展开下面的节点  
				    }  
				    alert(data.node.id !=1);
				});
			}
		};
	myAjaxRequest(options);
}



function openBodyHtml(url) {
	var config = {
			url : url,
			onSuccessFunction : function(data) {
				$("#rootMenu").html(data);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}


function common_confirm_simple(msg, url, rows) {
	common_confirm(msg,function() {
		var options = {
				url : url,
				onSuccessFunction : function(data) {
					if (data.success) {
						//刷页面
					}
				}
			};
    	myAjaxRequest(options);
	});
}

function getQueryString(name) {
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}



function showViewHtmlDialog(attr) {
	attr.size = "large";
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				attr.buttons = {close : {
					  label: '关闭',
					  className: "some-class",
					  callback: function() {
						  bootbox.hideAll();
					  }
					}};
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}
function showChooseHtmlDialog(attr) {
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}
function showSelectHtmlDialog(attr) {
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				attr.buttons = {
					ok : {
					  label: '确认',
					  className: "btn-primary",
					  callback: attr.callback
					},
					close : {
					  label: '关闭',
					  className: "some-class",
					}
				};
				openDialog(attr);
				if(attr.addInitFun) {
					attr.addInitFun();
				}
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}
function addViewHtmlDialog(attr) {
	attr.size = "large";
	var config = {
			url : attr.url,
			onSuccessFunction : function(data) {
				attr.msg = data;
				openDialog(attr);
				if(attr.addInitFun) {
					attr.addInitFun();
				}
			},
			proxyFlag : false,
			dataType : "html"
	};
	myAjaxRequest(config);
}

function bindUpdateValue(data) {
	for (var key in data) {
		if($("#"+key)) {
			if ($("#"+key).is('textarea')) {
				$("#"+key).html(data[key]);
			}else {
				$("#"+key).val(data[key]);
			}
		}
	}
}

function showViewHtml(viewConfig,data,title) {
	var htmlStr = '<div class="col-md-12"><div class="portlet light portlet-fit portlet-form bordered"><div class="portlet-title"><div class="caption"><i class=" icon-layers font-green"></i> <spanclass="caption-subject font-green sbold uppercase">'+title+'</span></div></div>';
	htmlStr += '<div class="portlet-body"><form action="#" class="form-horizontal" id="form_sample_1"><div class="form-body">';
	var line = true;
	for (var v in viewConfig) {
		var value = data[viewConfig[v].name];
		if (value == undefined) {
			value = "";
		}
		var name = viewConfig[v].text;
		var show = viewConfig[v].hidden;
		var hidden = "";
		var width = viewConfig[v].width;
		if (width == undefined || width == "") {
			width = "col-md-4";
		}
		var type = viewConfig[v].type;
		if (type == undefined || type == "") {
			type = "text";
		}
		var oneline = viewConfig[v].oneline;
		
		if (undefined == oneline || oneline == "") {
			oneline = false;
		}
		
		if (show) {
			type = "hidden";
			htmlStr += '<input type="'+type+'" class="form-control" placeholder="" readonly="readonly" value="'+value+'" >';
		}else {
			var valueHtml = "";
			if (type == "text") {
				valueHtml = '<input type="'+type+'" class="form-control" placeholder="" readonly="readonly" value="'+value+'" >'; 
			}else if (type == "textarea") {
				if (!line) {
					htmlStr += "</div>"
				}
				valueHtml += '<textarea class="form-control" name="" rows="3" readonly="readonly">'+value+'</textarea>';
				width = "col-md-10";
				htmlStr += '<div class="form-group"><label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div></div>';
				line = true;
				continue;
			}
			if (line) {
				htmlStr += '<div class="form-group"><label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div>';
				line = false;
			}else {
				htmlStr += '<label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div></div>';
				line = true;
			}
		}
	}
	htmlStr += '</div></form></div></div></div>';
	$("#viewShow").html(htmlStr);
}

function showViewFromHtml(viewConfig,data,title) {
	var htmlStr = '<div class="col-md-12"><div class="portlet light portlet-fit portlet-form bordered"><div class="portlet-title"><div class="caption"><i class=" icon-layers font-green"></i> <spanclass="caption-subject font-green sbold uppercase">'+title+'</span></div></div>';
	htmlStr += '<div class="portlet-body"><form action="#" class="form-horizontal" id="form_sample_1"><div class="form-body">';
	var line = true;
	for (var v in viewConfig) {
		var value = data[viewConfig[v].name];
		if (value == undefined) {
			value = "";
		}
		var name = viewConfig[v].text;
		var show = viewConfig[v].hidden;
		var hidden = "";
		var width = viewConfig[v].width;
		if (width == undefined || width == "") {
			width = "col-md-4";
		}
		var type = viewConfig[v].type;
		if (type == undefined || type == "") {
			type = "text";
		}
		var oneline = viewConfig[v].oneline;
		
		if (undefined == oneline || oneline == "") {
			oneline = false;
		}
		
		if (show) {
			type = "hidden";
			htmlStr += '<input type="'+type+'" class="form-control" placeholder="" readonly="readonly" value="'+value+'" >';
		}else {
			var valueHtml = "";
			if (type == "text") {
				valueHtml = '<input type="'+type+'" class="form-control" placeholder="" readonly="readonly" value="'+value+'" >'; 
			}else if (type == "textarea") {
				if (!line) {
					htmlStr += "</div>"
				}
				valueHtml += '<textarea class="form-control" name="" rows="3" readonly="readonly">'+value+'</textarea>';
				width = "col-md-10";
				htmlStr += '<div class="form-group"><label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div></div>';
				line = true;
				continue;
			}
			if (line) {
				htmlStr += '<div class="form-group"><label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div>';
				line = false;
			}else {
				htmlStr += '<label class="col-md-2 control-label" for="form_control_1">'+name+'</label><div class="'+width+'">'+valueHtml+'</div></div>';
				line = true;
			}
		}
	}
	htmlStr += '</div></form></div></div></div>';
	$("#viewShow").html(htmlStr);
}

function checkSelectById(datagrid) {
	var id = getSelectById(datagrid);
	if (id == undefined || id == null || id == "") {
		common_alert_error("请选择记录");
		return false;
	}
	return true;
}

function checkSelect() {
	var id = getSelectId();
	if (id == undefined || id == null || id == "") {
		common_alert_error("请选择记录");
		return false;
	}
	return true;
}
function getSelectId() {
	return $("#datagrid").bs_grid('selectedRows', 'get_ids')[0];
}
function getSelectById(datagrid) {
	return $("#"+datagrid).bs_grid('selectedRows', 'get_ids')[0];
}
function checkTreeSelect() {
	var id = getTreeSelectId();
	if (id == undefined || id == null || id == "") {
		common_alert_error("请选择记录");
		return false;
	}
	return true;
}
function getTreeSelectId() {
	if($('#gtreetable').gtreetable("getSelectedNodes")[0]) {
		return $('#gtreetable').gtreetable("getSelectedNodes")[0].id;
	}else {
		return null;
	}
}
function callBackSuccess() {
	openDialog({"msg":"操作成功","size":"small"});
	setTimeout(function(){bootbox.hideAll();},1000);
}

function submitVad() {
	var data = $("#form_sample_1").data('bootstrapValidator');
    if (data) {
    	//修复记忆的组件不验证
        data.validate();
        if (!data.isValid()) {
            return false;
        }
    }
    return true;
}
function submitVadById(formid) {
	var data = $("#"+ formid).data('bootstrapValidator');
    if (data) {
    	//修复记忆的组件不验证
        data.validate();
        if (!data.isValid()) {
            return false;
        }
    }
    return true;
}

function budingVad(fields) {
    /**
     * 下面是进行插件初始化
     * 你只需传入相应的键值对
     * */
    $('#form_sample_1').bootstrapValidator({
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields
     });
} 
function budingVadById(fields,formid) {
    /**
     * 下面是进行插件初始化
     * 你只需传入相应的键值对
     * */
    $('#'+formid).bootstrapValidator({
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields
     });
} 

function initCombox(data,selectId,config) {
	$("#"+selectId).append("<option value=''></option>");
	for (var v in data) {
		$("#"+selectId).append("<option value='"+data[v][0]+"'>"+data[v][1]+"</option>");
	}
}

function initEnum(data, key){
	for (var v in data) {
		if(data[v][0] == key){
			return data[v][1];
		}
	}
}

function initCheckTable() {
	 var table = $('#check_sample_1');

     table.find('.group-checkable').change(function () {
         var set = jQuery(this).attr("data-set");
         var checked = jQuery(this).is(":checked");
         jQuery(set).each(function () {
             if (checked) {
                 $(this).prop("checked", true);
                 $(this).parents('tr').addClass("active");
             } else {
                 $(this).prop("checked", false);
                 $(this).parents('tr').removeClass("active");
             }
         });
         jQuery.uniform.update(set);
     });

     table.on('change', 'tbody tr .checkboxes', function () {
         $(this).parents('tr').toggleClass("active");
     });
}

function text_blank(str) {
	if (str == ""||str == undefined||str==null||str=="null") {
		return "";
	}
	return str;
}
function resetsearch(resetPro) {
	var pros = resetPro.split(",");
	for (var v in pros) {
		if (pros[v].indexOf("@") > 0) {
			var pros_detail = pros[v].split("@");
			for (var p in pros_detail) {
				$("#search_" + pros_detail[p]).val("");
			}
		}else {
			$("#search_" + pros[v]).val("");
		}
	}
}
function refreshVlidate(formid) {
	if (formid == ""||formid == undefined||formid==null||formid=="null") {
		formid = "form_sample_1";
	}
	if ($("#"+formid)) {
		$("#"+formid).data('bootstrapValidator').resetForm()
	}
}
function init_select() {
	var ComponentsBootstrapSelect = function () {
	    var handleBootstrapSelect = function() {
	        $('.bs-select').selectpicker({
	            iconBase: 'fa',
	            tickIcon: 'fa-check'
	        });
	    }
	    return {
	        //main function to initiate the module
	        init: function () {      
	            handleBootstrapSelect();
	        }
	    };
	}();
	if (App.isAngularJsApp() === false) {
	    jQuery(document).ready(function() {    
	        ComponentsBootstrapSelect.init(); 
	    });
	}
}

function checkUnique(url,name,value) {
	var falg = true;
	var url = url+'!checkPropertyUnique.action?uniqPropertyName=' + name;
	var formParams={};
    formParams[name] = value;
    formParams['id']=$("[name=id]").val();
    var options = {
			url : url,
			postData : formParams,
			async:false,
			onSuccessFunction : function(data) {
				falg = data;
			}
	};
	myAjaxRequest(options);
	return falg;
}

function hideBox() {
	bootbox.hideAll();
}



var reg=new RegExp("Txt$");     
var reg_dyn=new RegExp("_dyn$");     
//导出
var exportExcelReport = function(action,groupFields,$dataGrid,searchFormid){
	var operadatagrid = $dataGrid;
	if(operadatagrid == undefined || operadatagrid == null){
		operadatagrid = $("#datagrid");
	}
	if(groupFields == undefined || groupFields == null){
		groupFields = 'id';
	}
	if(searchFormid == undefined || searchFormid == null){
		searchFormid = 'searchForm';
	}
	var rows = operadatagrid.bs_grid('selectedRows', 'get_all_obj');
	
	if(rows != undefined && !isEmptyObject(rows)){
		common_confirm('您确定要导出请求列表吗？',function(){
			excelRptDownload($('#'+searchFormid), action+'!exportExcelForReport.action',operadatagrid,groupFields);			
		});
	}else{
		common_alert_error("没有要导出的列表");
	}
}
function isEmptyObject(e) {
	var t;
	for (t in e)
		return !1;
	return !0
}
var excelRptDownload = function($serchForm, submitUrl,$dataGrid,groupFields) {
    var headerColumns=getDataGridFields($dataGrid);
    var newSubmitUrl=encodeURI(submitUrl+"?headerColumns="+headerColumns+"&groupFields="+groupFields);
    //newSubmitUrl = "http://139.196.4.84:8766" + newSubmitUrl;
    var formBind = function() {
   	    $serchForm.unbind();
   	   	$serchForm.submit(function(e) {
			$.fileDownload(newSubmitUrl, {           
           	   httpMethod: "POST",
               data: $serchForm.serialize(),
               successCallback: function (url) {
            	   $serchForm.unbind();
               },
               failCallback: function (responseHtml, url) {
            	   $serchForm.unbind();
            	   common_alert_error("请求下载失败，请检查下载文件流正常返回");
              	}
           });
		});
	};
   formBind();
   $serchForm.submit();   
};
function getDataGridFields(dataGrid){
		if(dataGrid==undefined||dataGrid==null){
			return '';
		}
		var  fieldNameArray= dataGrid.bs_grid("getAllOptions").columns;
		
		if(fieldNameArray==undefined||fieldNameArray==null){
  			return '';       
		}else{
			var fieldTitleArray=new Array();
			var retStr="";
	    	for ( var i = 0; i < fieldNameArray.length; i++) {
	   			var fieldName = fieldNameArray[i].field;
	   			if(fieldName == 'action')
	   				continue;
	   			var field=fieldNameArray[i];
	   			if(reg.test(fieldName))
	   				fieldName = fieldName.substring(0,fieldName.length-3)
	   			if(reg_dyn.test(fieldName)){
	   				fieldName = $('[name='+fieldName+']').val();
	   				if(fieldName == '')
		   				continue;
	   			}
	   			var fwidth = field.width;
	   			if (!fwidth) {
	   				fwidth = 100;
	   			}
   				retStr +='{field:"'+fieldName+'",title:"'+field.header+'",width:'+fwidth+',seqNum:'+i+'}';
	   			if(i!=fieldNameArray.length-1){
	   				retStr += ",";
	   			}
   			}
          	return  "["+retStr+"]";
       }
 }