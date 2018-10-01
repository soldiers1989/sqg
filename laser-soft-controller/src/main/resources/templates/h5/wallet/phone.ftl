<#include '../h5loginHead.ftl' />
<body ontouchstart>
	<div class="register_main">
		<form>
			<label class="reginput">
            	<input type="number" id="userPhone" name="userPhone" placeholder="手机号" />
            	<i class="login_icon phone_icon"></i>
	        </label>
			<label class="reginput testinput">
	            	<input type="text" id="verCode" name="verCode" placeholder="验证码" />
	            	<i class="login_icon code_icon"></i>
	            	<a href="javascript:void(0)" id="btn-time" onclick="sendMsg(this);" class="btn_message">获取验证码</a>
	       </label>
			<div class="btn_div">
				<a href="javascript:void(0);" onclick="next(this)" class="btn_block">下一步</a>
			</div>
		</form>
	</div>
</body>
<script>
var phone_check=false;
 var wait=60; 
function time(o) {
    if (wait == 0) {
    	phone_check=true;
    	$(o).html("获取验证码");
         wait = 60;
         addClick();
       } else {
       		phone_check=false;
         	$(o).html(wait+"秒重新获取");
          wait--;
          setTimeout(function() {
              time(o)
           },
           1000)
      }
 }
 function addClick(){
	 $("#btn-time").attr("onclick","sendMsg(this)");
 }
 
 function sendMsg(obj){
		$(obj).attr("onclick","");
	 	if(!validate()){
	 		addClick()
	 		return false;	
	 	}
		var data=new Object();
		data.phoneNo=$("#userPhone").val();
		$.ajax({
			type:"POST",
			url: "${sysContextPath}/laserDirect/sms/getCode",
			async:false,
			data:data,
			dataType:"json",
			success : function(data, textStatus, jqXHR) {
					if(data.success){
						 time(obj);
					}else if(!data.success){
						alert(data.msg);
					}
				}
			});
	 
 }
$("#btn-time").bind('click',function(){
	
})
 
 function validate(){
 	var userPhone=$("#userPhone").val();
  	if(!(/^1[0-9][0-9]\d{8}/.test(userPhone))){
  		 alert("请输入正确的手机号码")
		 return false;
	}
	return true;
 }
 
 function next(dom){
 	if(!validate()){
 		return false;	
 	}
 	$(dom).attr("onclick","");
 	var data=new Object();
	data.phoneNo=$("#userPhone").val();
	data.code=$("#verCode").val();
	$.ajax({
	type:"POST",
	url: "${sysContextPath}/web/wallet/updatePhone",
	async:false,
	data:data,
	dataType:"json",
	success : function(data, textStatus, jqXHR) {
				if(data.success){
					window.location.href="${sysContextPath}/web/wallet/cash";
				}else if(!data.success){
				 	alert(data.msg);
				 	$(dom).attr("onclick","next(this)");
				}
			}
 	});
 }
 
</script>