$(document).ready(function () {
	$('body').layout({ applyDefaultStyles: true });
	
	$('#menu').accordion();
	$('#changePassword').bind('click', showChangePasswordDialog);
	$('#exit').bind('click', logout);
	$('#firstPage').bind('click', goToFirst);
	$('#prePage').bind('click', goToPre);
	$('#nextPage').bind('click', goToNext);
	$('#lastPage').bind('click', goToLast);
	$('#addUserBtn').bind('click', showAddUserDialog);
//	$('#mdfUserBtn').bind('click', showMdfUserDialog);
//	$('#delUserBtn').bind('click', delUser);
	
	var msg = $('#msg').val();
	if (msg != null && msg != "") {
		alert(msg);
	}
	
	var updateUserId = $('#updateUserId').val();
	if (updateUserId != null && updateUserId != "") {
		showUpdateUserDialog();
	}
	
});
	
// 弹出修改密码对话框函数
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

// 退出提交函数
function logout() {
	$('#logout-form').submit();
}

// 修改密码函数： 修改密码前验证以及修改密码提交
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

// 跳转到第一页
function goToFirst() {
	if ($('#currentPage').val() == 1) {
		alert("已经是第一页！");
		return false;
	}
	$('#currentPage').val(1);
	
	$('#showUser-form').submit();
}

// 跳转到上一页
function goToPre() {
	if ($('#currentPage').val() == 1) {
		alert("无上一页！");
		return false;
	}
	$('#currentPage').val(parseInt($('#currentPage').val()) - 1);
	$('#showUser-form').submit();
}

// 跳转到下一页
function goToNext() {
	if ($('#currentPage').val() == $('#totalPage').val()) {
		alert("无下一页！");
		return false;
	}
	$('#currentPage').val(parseInt($('#currentPage').val()) + 1);
	$('#showUser-form').submit();
}

// 跳转到最后一页
function goToLast() {
	if ($('#currentPage').val() == $('#totalPage').val()) {
		alert("已经处于最后一页！");
		return false;
	}
	$('#currentPage').val(parseInt($('#totalPage').val()));
	$('#showUser-form').submit();
}

// 打开添加用户对话框
function showAddUserDialog() {
	$('#addUserDialog').dialog({
		modal:true,
		position:'center',	// 对话框的位置设置
		buttons:{			// 按钮信息
			添加用户:addUser,		// 调用下面的 addUser()
			取消:function() {
				 $(this).dialog("close");
			}
		}
	});
}

// 打开修改用户对话框
function showUpdateUserDialog() {
	var description = $('#desc_hidden').val();
	$('#updateUser_description').text(description);
	$('#updateUserDialog').dialog({
		modal:true,
		position:'center',	// 对话框的位置设置
		buttons:{			// 按钮信息
			修改用户:updateUser,		// 调用下面的 updateUser()
			取消:function() {
				$('#updateUserId').val("");
				$(this).dialog("close");
			}
		}
	});
}

// 添加用户
function addUser() {
	var username = $('#addUser_username').val();
	var password = $('#addUser_username').val();
	var repassword = $('#addUser_username').val();
	
	if (username == null || username == "") {
		alert("用户名不能为空！");
	}
	
	if (password == null || password == "") {
		alert("密码不能为空！");
	}
	
	if (repassword == null || password == "") {
		alert("请确认密码！");
	}
	
	if (password != repassword) {
		alert("两次输入的密码不一致");
	}
	
	$('#addUser-form').submit();
}

// 修改用户
function updateUser() {
	var username = $('#updateUser_username').val();
	var password = $('#updateUser_password').val();
	var repassword = $('#updateUser_repassword').val();
	
	if (username == null || username == "") {
		alert("用户名不能为空！");
	}
	
	if (password == null || password == "") {
		alert("密码不能为空！");
	}
	
	if (repassword == null || password == "") {
		alert("请确认密码！");
	}
	
	if (password != repassword) {
		alert("两次输入的密码不一致");
	}
	
	$('#updateUser-form').submit();
}

function getSelectIds() {
	var ids = "";
	$('input[type="checkbox"][id^="userId_"]').each(function(){
		if ($(this).attr("checked")) {
			ids = ids + $(this).val() + ",";
		}   
	});
	
	return ids.substring(0, ids.lastIndexOf(","));
}