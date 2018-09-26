<#include '../h5indexHead.ftl' />
<body ontouchstart>
<iframe id="shareCode" src=""  frameborder="0" style="width:100%;height: 100%"></iframe>
</body>
<script>
	var config = {
		url : "${sysContextPath}/laserDirect/wx/generateWxQrCode/1",
		dataType:"json",
		onSuccessFunction : function(data) {
			if (data.code == "success") {
				$("#shareCode").attr("src", data.data);
			}
		},
		mask : false
	};
	myAjaxRequest(config);

</script>
</html>

