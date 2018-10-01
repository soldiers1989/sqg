<div class="row" id="addShow">
	<div class="col-md-12">
		<div class="portlet light portlet-fit portlet-form bordered">
			<div class="portlet-title">
				<div class="caption">
					<i class=" icon-layers font-green"></i> <span
						class="caption-subject font-green sbold uppercase">数据字典信息</span>
				</div>
			</div>
			<div class="portlet-body">
				<!-- BEGIN FORM-->
				<form action="#" class="form-horizontal" id="form_sample_1">
					<input type="hidden" name="id" id="id" />
					<div class="form-body">
						<div class="form-group ">
							<label class="col-md-2 control-label" for="code">商品名称
								<span class="required"></span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="" name="itemTitle" id="itemTitle">
								<div class="form-control-focus"></div>
								<span class="help-block"></span>
							</div>
							<label class="col-md-2 control-label" for="name">用户ID
								<span class="required">*</span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="" name="userId" id="userId">
								<div class="form-control-focus"></div>
								<span class="help-block"></span>
							</div>
						</div>						
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-5">
								<button type="submit" class="btn green" id="saveBtn">保存</button>
								<button type="button" class="btn" onclick="hideBox()">关闭</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>

$(document).ready(function() {
	
	$("#form_sample_1").submit(function() {
		return false;
	});
	
	//请求
	$("#saveBtn").click(function () {
	    if (submitVad()) {
	    	var id = "${id!''}";
	    	var url = "${sysContextPath}/admin/order/add.json";
	    	if (id!=null&&id!='null'&&id!=undefined&&id!='') {
	    		url = "${sysContextPath}/admin/order/update.json";
	    	}
	    	var data = $('.form-horizontal').serialize();
	    	var options = {
					url : url,
					postData : data,
					onSuccessFunction : function(data) {
						if (data.success) {
							bootbox.hideAll();
							common_alert_success("保存成功");
						}else {
							common_alert_error(data.msg);
						}
						initGrid();
					}
				};
	    	myAjaxRequest(options);
	    }
	});
	/*验证*/
	budingVad({
		ddCode : {
			validators : {
				notEmpty : {
					message : '值不能为空'
				},
				stringLength: {
			        max: 20,
			        message: '代码小于20位'
			    }
			}
		},ddValue : {
			validators : {
				notEmpty : {
					message : '值不能为空'
				}
			}
		}
		});
	
	});
	
</script>