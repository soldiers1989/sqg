var Enums = (function() {
	return{
		// 证件类型
		CERT_TYPE:[['01','身份证'],['02','户口本'],['03','护照'],['04','军官证'],['05','士兵证'],
	                      ['06','港澳居民来往内地通行证'],['07','台湾同胞来往内地通行证'],['08','临时身份证'],['09','外国人居住证'],['10','警官证'],
	                      ['11','驾驶证'],['12','社会保障号'],['13','其他证件']],                  
		//性别
		SEX:[['0','男'],['1','女']],
		//系统类型
		//SYS_TYPE:[['00','客户端'],['01','后台配置系统']],
		SYS_TYPE:[['01','后台配置系统']],
		// 有效标志位 
		VALID_FLAG : [['0','无效'],['1','有效']],
	    //并发状态
	    CON_CURRENT:[['0','非并发'],['1','并发']],
	    //是否
	    YES_OR_NO:[['0','否'],['1','是']],
	    //有无标识
	    EXIST_FLAG:[['1','有'],['0','无']],
	    //最高学历
	    HIGHEST_DEGREE:[['01','硕士及以上'],['02','本科'],[ '03','专科'],['04','高中'],['05','初中及以下']],
	    //产品阶段
	    SERVICE_PHASE:  [['01','查询'],['02','征信'],['03','欺诈']],
	    //用户状态
	    USER_STATE:  [['0','注销'],['1','正常']],
	   //月份
	   MONTH:[['01','1月'],['02','2月'],['03','3月'],['04','4月'],['05','5月'],['06','6月'],['07','7月'],['08','8月'],['09','9月'],['10','10月'],['11','11月'],['12','12月']],
	   //资源类型
	   //RES_TYPE:[['01','菜单'],['02','功能']],
	   RES_TYPE:[['01','菜单']],
	   //数据类型
	   DATA_TYPE : [['String','String'],['Double','Double'],['Integer','Integer'],['Date','Date']],
	   //数据字典类型
	   DIC_TYPE : [['text','text'],['combobox','combobox']],
	   //消息类型
	   MESSAGE_TYPE:[['0','短信'],['1','邮件']],
	   //请求类型
	   REQUEST_TYPE:[['0','请求'],['1','数据源回调'],['2','业务回调']],
	   //发布状态
	   PUBLISH_STATUS:[['0','不公开'],['1','公开']],
	   //服务类型
	   SERVICE_TYPE:[['0','单一'],['1','组合']],
	   //响应方式
	   RESP_TYPE:[['0','同步'],['1','异步查询'],['2','异步回调']],
	   //执行方式
	   EXECUTE_WAY:[['0','串行'],['1','并行']],
	   //更新频率
	   FREQ_UNIT:[['0','天'],['1','月'],['2','月固定日期']],
	   //稳定性
	   STEAD:[['0','稳定'],['1','偶尔故障'],['2','时常故障'],['3','不可用']],
	   //接口评级
	   GRADE:[['A','A级'],['B','B级'],['C','C级'],['D','D级']],
	   //数据属性
	   HANDS:[['1','一手'],['2','二手'],['3','三手'],['0','多手']],
	   //授权类型
	   AUTH_TYPE:[['0','短信'],['1','页面'],['2','协议']],
	   //所属报文类型
	   IO_TYPE:[['1','输入'],['0','输出']],
	   //授权触发方式
	   AUTH_TRIGGER_TYPE:[['0','平台回调触发'],['1','服务触发']],
	   //调用方式
	   INVOKE_TYPE:[['http-get','HTTP-GET'],['http-post','HTTP-POST'],['https-get','HTTPS-GET'],['https-post','HTTPS-POST'],['webservice11','WEBSERVICE11'],['webservice12','WEBSERVICE12'],['client','CLIENT']],
	   //报文格式
	   FORMAT_TYPE:[ ["P", "PARAM"],['X','XML'],['J','JSON']],
	   //压缩方式
	   ZIP_TYPE:[['G','GZip']],
	   //接口执行规则标志
	   API_EXEC_RULE_FLAG:[['0','顺序'],['1','权重']],
	   //接口处理状态
	   TRANS_STATE:[['0','失败'],['1','成功'],['2','处理中']],
	   //预警类型
	   WARN_TYPE:[['01','未配置']],
	   //数据来源
	   TRANS_SOURCE:[['0','查库'],['1','查接口']],
	   //数据来源
	   STAT_TYPE:[['全量统计',''],['按日统计','statDate','统计日期'],['按周统计','statWeek','统计周'],['按月统计','statMonth','统计月']],
	   transferEnumToOptions : function(enumObj, addNullOption, defaultValue) {
			var options = new Array;
			var seq = 0;
			if(addNullOption) {
				options[0] = {};
				options[0].showText = "";
				options[0].value = "";
				seq = 1;
			}
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i + seq] = {};
					options[i + seq].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i + seq].value = enumObj[i][0];
					if (defaultValue && defaultValue == options[i + seq].value) {
						options[i + seq].checked = 'true';
					}
				}
			}
			return options;
		},
		transferEnumToJson : function(enumObj) {
			var options = new Array;
			if (enumObj && enumObj.length) {
				for ( var i = 0; i < enumObj.length; i++) {
					options[i] = {};
					options[i].showText = enumObj[i][0] + "-" + enumObj[i][1];
					options[i].value = enumObj[i][0];
				}
			}
			return options;
		}

	};
})();