<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>用户授权</title>
    <script type="text/javascript" src="./userPermission.js"></script>
    <style type="text/css">
    	li {
    		font-size: small;
    	}
    	
    	table,tr,td,th {
    		border: 1px solid #000000;
    	}
    	
    </style>
  </head>
  
  <body>
  	<input type="hidden" id="msg" value="${session.msg }"/>
  	<div style="display: none;">
  		<!-- 退出form start -->
  		<form action="logoutAction" id="logout-form"></form>
  		<!-- 退出form end -->
  		<!-- 翻页form start -->
  		<form action="authorizeUser" id="showUser-form">
  			<input type="text" id="currentPage" name="currentPage" value="${currentPage }"/>
  			<input type="text" id="totalPage" name="totalPage" value="${totalPage }"/>
  		</form>
  		<!-- 翻页form end -->
  	</div>
    <!-- top start -->
		<div class="ui-layout-north">
			<div style="text-align:right;font-size: small">
            	欢迎您  | ${session.user.username } &nbsp;&nbsp;<a id="changePassword">修改密码</a>&nbsp;&nbsp;<a id="exit">退出</a>
            </div>
		</div>
		<!-- top end -->
		<!-- left start -->
		<s:if test="#session.user.type > 0">
			<div class="ui-layout-west" id="menu">
				<h3>门户管理</h3>
					<div>
						<ul>
							<li><a href="showPortalGroupAction">门户组管理</a></li>
							<li><a href="showPortletAction">模块管理</a></li>
							<li><a href="showPermissionAction">模块授权</a></li>
							<li><a href="showDefaultPortletAction">默认排版</a></li>
						</ul>
					</div>
				<s:if test="#session.user.type > 1">
					<h3>用户管理</h3>
					<div>
						<ul>
							<li><a href="showUserAction">用户管理</a></li>
							<li><a href="showUserPermissionAction">用户授权</a></li>
						</ul>
					</div>
					<h3>角色管理</h3>
					<div>
						<ul>
							<li><a href="showRoleAction">角色管理</a></li>
							<li><a href="showRolePermissionAction">角色授权</a></li>
						</ul>
					</div>
				</s:if>
			</div>
		</s:if>
		<!-- left end -->
		<!-- center start -->
		<div class="ui-layout-center" style="text-align:center;">
			对 [${session.authorizeUser.username}] 授权
			<table width="100%">
				<caption>用户信息列表</caption>
				<thead>
					<tr>
						<th>序号</th>
						<th>角色名</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="userRole" value="#session.userRoles" status="st">
						<tr <s:if test="#st.odd">style="background-color:#BBBBBB"</s:if>>
							<td align="center">${st.count }</td>
							<td><span id="rolename_${userRole.id }">${userRole.role.name  }</span></td>
							<td> 
								<s:if test="#userRole.status == 1">
									已授权
								</s:if> <s:else>
									未授权
								</s:else>
							</td>
							<td>
								<s:if test="#userRole.status == 1">
									<a href="authorizeUserAction?id=${userRole.id }">取消授权</a>
								</s:if> <s:else>
									<a href="authorizeUserAction?id=${userRole.id }">授权</a>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<!-- center end -->
        
        <!-- Dialogs -->
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
