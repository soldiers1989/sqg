var OpenValidator = function(){
	var handleSubmit = function(options) {
		var myrules = options.rules;
		var messages = options.messages;
		var formId = options.formId;
		var ajaxSub = options.ajaxSub;
		var errorClassName = options.errorClass;
		if (!errorClassName) {
			errorClassName = 'help-inline';
		}
		if (ajaxSub==undefined) {
			ajaxSub = true;
		}
		$(formId).validate({
			errorElement : 'span',
			errorClass : errorClassName,
			focusInvalid : false,
			rules : myrules,
			messages : messages,
			highlight : function(element) {
				$(element).closest('.control-group').removeClass('success');
				$(element).closest('.control-group').addClass('error');
			},
			success : function(label) {
				label.closest('.control-group').removeClass('error');
				label.closest('.control-group').addClass('success');
			},
			errorPlacement : function(error, element) {
				element.parent('div').append(error);
			},
			submitHandler : function(form) {
				if (ajaxSub) {
					return;
				}else {
					form.submit();
				}
			}
		});

		$(formId+' input').keypress(function(e) {
			if (e.which == 13) {
				if ($(formId+' input').validate().form()) {
					$(formId+' input').submit();
				}
				return false;
			}
		});
	}
	return {
		init : function(options) {
			handleSubmit(options);
		}
	};

}();
