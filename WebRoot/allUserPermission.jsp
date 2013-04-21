<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>用户授权</title>
    <script type="text/javascript" src="./allUserPermission.js"></script>
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
  		<form action="showUserAction" id="showUser-form">
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
			<form id="searchUser-form" action="./showUserPermissionAction" method="post">
				用户名：<input type="text" name="searchUsername" value="${searchUsername }" id="searchUsername" class="text ui-widget-content ui-corner-all"  /> &nbsp;&nbsp;&nbsp;&nbsp;
				激活状态：<select name="searchEnabled" id="searchEnabled" class="text ui-widget-content ui-corner-all" >
					<s:if test="#attr.searchEnabled == 0">
						<option value="-1">请选择</option>
						<option value="0" selected="selected">已激活</option>
						<option value="1">未激活</option>
					</s:if> <s:elseif test="#attr.searchEnabled == 1">
						<option value="-1">请选择</option>
						<option value="0">已激活</option>
						<option value="1" selected="selected">未激活</option>
					</s:elseif><s:else>
						<option value="-1" selected="selected">请选择</option>
						<option value="0">已激活</option>
						<option value="1">未激活</option>
					</s:else>
				</select> &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" class="ui-button" value="查询"/>
			</form>
		<hr/>
			<table width="100%">
				<caption>用户信息列表</caption>
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>是否激活</th>
						<th>描述</th>
						<th>角色列表</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="showUser" value="#session.showUsers" status="st">
						<tr <s:if test="#st.odd">style="background-color:#BBBBBB"</s:if>>
							<td align="center">${st.count }</td>
							<td><span id="username_${showUser.id }">${showUser.username  }</span></td>
							<td><input type="hidden" id="enabled_${showUser.id }" value="${showUser.enabled }"/>
							<s:if test="#showUser.enabled == 0">
								<span>已激活</span>
							</s:if><s:else>
								<span>未激活</span>
							</s:else></td>
							<td><span id="userdesc_${showUser.id }">${showUser.description}</span> </td>
							<td>
								<span>
									<s:iterator var="userRole" value="#showUser.userRoles" status="st">
										<s:if test="#userRole.status == 0">
											<s:if test="#st.count == showUser.userRoles.size()">
												${userRole.role.name }
											</s:if><s:else>
												${userRole.role.name }, 
											</s:else>
										</s:if>
									</s:iterator>
								</span>
							</td>
							<td>
								<a href="authorizeUser?id=${showUser.id }">授权</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<s:if test="#currentPage == 1">
								第一页
							</s:if><s:else>
								<a id="firstPage">第一页</a>&nbsp;&nbsp;
							</s:else>
							<a id="prePage">上一页</a>&nbsp;&nbsp;
							<a id="nextPage">下一页</a>&nbsp;&nbsp;
							<s:if test="#currentPage == totalPage">
								最后一页
							</s:if><s:else>
								<a id="lastPage">最后一页</a>
							</s:else>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							当前第 ${ currentPage} 页 | 共 ${totalPage  }页 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;
						</td>
					</tr>
				</tfoot>
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
