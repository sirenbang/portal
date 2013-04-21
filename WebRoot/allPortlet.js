$(document).ready(function () {
	$('body').layout({ applyDefaultStyles: true });
	$('#menu').accordion();
	
	$('#changePassword').bind('click', showChangePasswordDialog);
	$('#exit').bind('click', logout);
	
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
function showAllPortletBtnDialog(url) {
	window.location.href = url;
}

/**
 * 提交模块信息
 */
function submitPortlet() {
	$('#portlet-form').submit();
}