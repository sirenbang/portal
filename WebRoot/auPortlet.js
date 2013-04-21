$(document).ready(function () {
	$('body').layout({ applyDefaultStyles: true });
	$('#menu').accordion();
	
	$('#changePassword').bind('click', showChangePasswordDialog);
	$('#exit').bind('click', logout);
	
	// $('#addPortletBtn').bind('click', showAllPortletBtnDialog);
	$('#portletType').change(showExPortlet);

	var msg = $('#msg').val();
	if (msg != null && msg != "") {
		alert(msg);
	}
});
	
/**
 * 弹出修改密码对话框函数
 */
function showChangePasswordDialog() {
	$('#changePasswordDialog').dialog({
		modal:true,
		position:'center',	// 对话框的位置设置
		buttons:{			// 按钮信息
			修改密码:changePassword,		// 调用下面的 changePassword()
			取消:function() {
				 $(this).dialog("close");
			}
		}
	});
}

/**
 * 退出提交函数
 */
function logout() {
	$('#logout-form').submit();
}

/**
 * 修改密码函数： 修改密码前验证以及修改密码提交
 */
function changePassword() {
	var username = $('#changePassword_username').val();
	var password = $('#changePassword_password').val();
	var repassword = $('#changePassword_repassword').val();

	if (username == null || username == "") {
		alert("用户名不能为空！");
		return;
	}
	
	if (password == null || password == "") {
		alert("密码不能为空！");
		return;
	}
	
	if (repassword == null || repassword == "") {
		alert("请确认密码！");
		return;
	}
	
	if (repassword != password) {
		alert("两次密码不一致！");
		return;
	}
	
	$('#changePassword-form').submit();
}

/**
 * 打开模块对话框
 */
function showAllPortletBtnDialog(type) {
	if (type == 1) {		// 添加模块
		$('actionType').val(1);
		$('#portletDialog').dialog({
			title:'添加模块',
			modal:true,
			position:'center',	// 对话框的位置设置
			buttons:{			// 按钮信息
				修改密码:submitPortlet,		// 调用下面的 submitPortlet()
				取消:function() {
					 $(this).dialog("close");
				}
			}
		});
	} else {				// 修改模块
		$('actionType').val(2);
		$('#portletDialog').dialog({
			title:'修改模块',
			modal:true,
			position:'center',	// 对话框的位置设置
			buttons:{			// 按钮信息
				修改密码:submitPortlet,		// 调用下面的 submitPortlet()
				取消:function() {
					 $(this).dialog("close");
				}
			}
		});
	}
}

/**
 * 提交模块信息
 */
function submitPortlet() {
	$('#portlet-form').submit();
}

/**
 * 通过选择不同的类型选择不同的添加模式
 */
function showExPortlet() {
	if ($('#portletType').val() == 1) {
		$('#iframeEx-form').show();
		$('#linkEx-form').css("display", "none");
		$('#gridEx-form').css("display", "none");
	} else if ($('#portletType').val() ==2) {
		$('#linkEx-form').show();
		$('#iframeEx-form').css("display", "none");
		$('#gridEx-form').css("display", "none");
	} else if ($('#portletType').val() == 3) {
		$('#gridEx-form').show();
		$('#iframeEx-form').css("display", "none");
		$('#linkEx-form').css("display", "none");
	} else {
		$('#gridEx-form').css("display", "none");
		$('#iframeEx-form').css("display", "none");
		$('#linkEx-form').css("display", "none");
	}
}

function showLinkManageDialog() {
	$('#linkManageDialog').dialog({
		title:'链接管理',
		modal:true,
		position:'center',	// 对话框的位置设置
		buttons:{			// 按钮信息
			关闭:function() {
				 $(this).dialog("close");
			}
		}
	});
}