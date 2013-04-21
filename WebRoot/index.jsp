<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>首页</title>
    <script type="text/javascript" src="./index.js"></script>
    <style type="text/css">
    	li {
    		font-size: small;
    	}
    </style>
  </head>
  
  <body>
  	<input type="hidden" id="msg" value="${session.msg }"/>
  	<div style="display: none;">
  		<form action="logoutAction" id="logout-form"></form>
  	</div>
    <!-- top start -->
		<div class="ui-layout-north">
			<div style="text-align:right;font-size: small">
            	<s:if test="#session.user == null">
	            	<a id="login">登陆</a> &nbsp;&nbsp;  <a id="regist">注册</a>
            	</s:if>
            	<s:else>
            		欢迎您  | ${session.user.username } &nbsp;&nbsp;<a id="changePassword">修改密码</a>&nbsp;&nbsp;<a id="exit">退出</a>
            	</s:else>
            </div>
		</div>
		<!-- top end -->
		<!-- center start -->
		<div class="ui-layout-center">
			CENTER_CONTENT
		</div>
		<!-- center end -->
        
        <!-- Dialogs -->
        <!-- 登录对话框 DIV -->
        <div id="loginDialog" class="ui-widget ui-widget-content ui-corner-all" title="登录">
        	<form id="login-form" action="loginAction" method="post">
		       		用户名:<input type="text" name="user.username" id="login_username" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		                        密&nbsp;&nbsp;&nbsp;码:<input type="password" name="user.password" id="login_password" value="" class="text ui-widget-content ui-corner-all" />
        	</form>
	    </div>
	    <!-- 注册对话框 -->
        <div id="registDialog" class="ui-widget ui-widget-content ui-corner-all" title="注册">
        	<form id="regist-form" action="registAction" method="post">
        		用户名:&nbsp;&nbsp;&nbsp;<input type="text" name="user.username" id="regist_username" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="user.password" id="regist_password" value="" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		       	确认密码：<input type="password" name="repassword" id="regist_repassword" value="" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		       	描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:	<textarea name="user.description" id="regist_description" class="text ui-widget-content ui-corner-all" ></textarea>	
        	</form>
        </div>
        <!-- 修改密码对话框 DIV -->
        <div id="changePasswordDialog" class="ui-widget ui-widget-content ui-corner-all" title="修改密码">
        	<form id="changePassword-form" action="changePasswordAction" method="post">
        		用户名:&nbsp;&nbsp;&nbsp;<input type="text" name="user.username" value="${session.user.username }" id="changePassword_username" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		                密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="user.password" value="${session.user.password }" id="changePassword_password" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		       	确认密码：<input type="password" name="repassword" id="changePassword_repassword" value="" class="text ui-widget-content ui-corner-all" />
        	</form>
        </div>
  </body>
</html>
