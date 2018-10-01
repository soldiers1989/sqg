<div class="row" id="viewShow">
	
</div>

<script>

var viewConfig = [
                  {"name":"id","text":"","hidden":true},
                  {"name":"itemTitle","text":"商品名称"},
                  {"name":"itemPrice","text":"商品价格"}
                  ];

$(document).ready(function() {
	var id = ${id};
	var options = {
			url : "${sysContextPath}/admin/order/get.json?id="+id,
			postData : {id:id},
			onSuccessFunction : function(data) {
				showViewHtml(viewConfig,data.data,"订单详情");
			}
		};
	myAjaxRequest(options);
});

</script>
