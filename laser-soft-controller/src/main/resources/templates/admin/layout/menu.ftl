<ul id="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
</ul>

<script>
	
var data = [{'menuName':'订单管理','menuCode':'1','menuList':[{'menuName':'订单列表','menuCode':'1-1','menuUrl':'/admin/order/index'}]},
			{'menuName':'用户管理','menuCode':'2','menuList':['']}];
loadMenu();
function loadMenu(){
var htmlStr = ""; 
for(var t in data){ 
	var icon = "icon-wallet";
	htmlStr += "<li class='nav-item' >" +
			        "<a href='javascript:;' class='nav-link nav-toggle' onclick='getChildMenu(\"" + data[t].menuCode + "\")'>" +
			        	"<i class='"+icon+"'></i>" +
			            "<span class='title'>" + data[t].menuName + "</span>" +
			            "<span class='arrow'></span>" +
			        "</a>" +
			        "<ul class='sub-menu' id='childMenu" + data[t].menuCode + "'>" +
			        "</ul>" +
			    "</li>";
}
$("#menu").html(htmlStr);
}

function getChildMenu(id){

	var subdata;
	var htmlStr="";
	for (var r in data) {
		if (data[r].menuCode == id) {
			subdata = data[r].menuList;
		}
	}

	for(var t in subdata){
		var icon = '';
		htmlStr += "<li class='nav-item'>" +
						"<a href='javascript:;' onclick='openBodyHtml(\"${sysContextPath}" + subdata[t].menuUrl + "\");' class='nav-link '>" +
							"<i class='"+icon+"'></i>" +
							"<span class='title'>" + subdata[t].menuName + "</span>" +
						"</a>" +
					"</li>";
	}
	$("#childMenu"+id).html(htmlStr);
}
</script>