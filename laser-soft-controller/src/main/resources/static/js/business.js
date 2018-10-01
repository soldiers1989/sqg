function showorgid(selectId, selectName) {
	selectDataObject = null;
	var attr = {url:_contextPath+"/common/orgid.jsp",size:"small",title:"选择",callFun:function(){
		if (selectDataObject) {
			$("input[name='" + selectId + "']").val(selectDataObject.id);
			$("input[name='" + selectName + "']").val(selectDataObject.text) ; 
		}
	}}; 
	openSelectDialogHtml(attr);
}

function showouserrole(selectId) {
	var options = {
			url : _contextPath + '/sys/baseRoleAction!filteredDataGrid.action',
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					$("#"+selectId).append("<option value='"+rows[v].id+"'>"+rows[v].name+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showprovider(selectId) {
	var options = {
			url : _contextPath + '/serapi/serDataProviderAction!dataGrid.action',
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					$("#"+selectId).append("<option value='"+rows[v].id+"'>"+rows[v].name+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showStatusGroup(selectId,templateId,groupId) {
	if(groupId == undefined || groupId == 'undefined'){
		groupId = "";
	}
	var options = {
			url : _contextPath + '/template/serTemplateStatusGroupAction!dataGrid.action',
			postData: {"templateId":templateId},
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					var selected = "";
					if(rows[v].id == groupId){
						selected = "selected='selected'";
					}
					$("#"+selectId).append("<option value='"+rows[v].id+"' "+ selected +">"+rows[v].pathGroup+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showDictionary(selectId, templateId, fieldId, value) {
	var options = {
			url : _contextPath + '/template/serTemplateDictionaryAction!dataGrid.action',
			postData: {"templateId":templateId,"fieldId":fieldId,"sort":"id","order":"asc"},
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					var selected = "";
					if(rows[v].dictionaryKey == value){
						selected = "selected='selected'";
					}
					$("#"+selectId).append("<option value='"+rows[v].dictionaryKey+"' " + selected + ">"+rows[v].dictionaryValueName+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showApifield(selectId, fieldId, value) {
	var options = {
			url : _contextPath + '/serapi/serDataApiDictionaryAction!dataGrid.action',
			postData: {"fieldId":fieldId,"sort":"id","order":"asc"},
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					var selected = "";
					if(rows[v].valueKey == value){
						selected = "selected='selected'";
					}
					$("#"+selectId).append("<option value='"+rows[v].valueKey+"' " + selected + ">"+rows[v].valueName+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showclassstruc(apiId, ioType) {
	if(ioType == undefined || ioType == 'undefined'){
		ioType = "";
	}
	selectDataObject = null;
	var attr = {url: _contextPath+"/common/classstruc.jsp?apiId="+apiId+"&ioType="+ioType,size:"small",title:"选择",callFun:function(){
		if (selectDataObject) {
			
			if(selectDataObject.attributes.type.toString()!='L'){
				common_alert_error('不能选择非叶子节点！');
				return;
			}
			
			$("input[id=field]").val(selectDataObject.attributes.field.toString());
			$("input[id=fieldId]").val(selectDataObject.attributes.id.toString());
			$("input[id=fieldPath]").val(selectDataObject.attributes.fieldPath.toString());
			$("input[id=ioType]").val(selectDataObject.attributes.ioType.toString());
		}
	}}; 
	openSelectDialogHtml(attr);
}


function showregular() {
	
	var attr = {url:_contextPath+"/common/regular.jsp",size:"large",title:"请选择正则规则",callback:function(){
		var id = $("#select_datagrid").bs_grid('selectedRows', 'get_ids')[0];
		if (id) {
			var row = $("#select_datagrid").bs_grid('selectedRows', 'get_obj', id);
			$("#regularId").val(id);
			$("#regularCode").val(row.code);
			$("#regularExpression").val(row.expression);
		}
		
	}};
	showSelectHtmlDialog(attr);
}

function showTempapi(selectId, temInfoId, value){
	if(value == undefined || value == null){
		value = "";
	}
	var options = {
			url : _contextPath + '/rela/templateApiAction!dataGrid.action',
			postData: {"temInfoId":temInfoId},
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					var selected = "";
					if(rows[v].valueKey == value){
						selected = "selected='selected'";
					}
					$("#"+selectId).append("<option value='"+rows[v].apiId+"' " + selected + ">"+rows[v].apiName+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function showExecRule(selectId, templateId, value){
	if(value == undefined || value == null){
		value = "";
	}
	var options = {
			url : _contextPath + '/serExecRule/serSerExecRuleAction!dataGrid.action',
			postData: {"templateId":templateId},
			onSuccessFunction : function(data) {
				var rows = data.rows;
				$("#"+selectId).append("<option value=''></option>");
				for (var v in rows) {
					var selected = "";
					if(rows[v].valueKey == value){
						selected = "selected='selected'";
					}
					$("#"+selectId).append("<option value='"+rows[v].childTemplateId+"' " + selected + ">"+rows[v].childTemplateName+"</option>");
				}
			}
		};
	myAjaxRequest(options);
}

function view(id){
	alert("view:" + id);
}
function edit(id){
	alert("edit:" + id);
}
function del(id){
	alert("del:"+id)
}