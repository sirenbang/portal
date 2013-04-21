<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>首页</title>
    <script type="text/javascript" src="./allPortlet.js"></script>
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
  		<form action="logoutAction" id="logout-form"></form>
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
		<div class="ui-layout-center">
			<form id="searchPortlet-form" method="post" action="./searchPortletAction">
				模块名：<input type="text" name="searchPortletName" id="searchPortletName" value="${attr.searchPortletName}" class="text ui-widget-content ui-corner-all"/>&nbsp;&nbsp;
				模块类型：<select name="searchPortletType" id="searchPortletType" class="text ui-widget-content ui-corner-all">
					<s:if test="#attr.searchPortletType == 0">
						<option value="0" selected="selected">请选择</option>
					</s:if><s:else>
						<option value="0">请选择</option>
					</s:else>
					<s:iterator var="portletType" value="#application.allPortletType ">
						<s:if test="#attr.searchPortletType == portletType.code">
							<option value="${portletType.code }" selected="selected">${portletType.value }</option>
						</s:if><s:else>
							<option value="${portletType.code }">${portletType.value }</option>
						</s:else>
					</s:iterator>
				</select>
				&nbsp;&nbsp;&nbsp;
				<input type="submit" value="查询" class="ui-button"/>&nbsp;&nbsp;&nbsp;
				<input type="button" value="添加" class="ui-button" id="addPortletBtn" onclick="showAllPortletBtnDialog('./auPortlet.jsp')"/>
			</form>
			<hr/>
			<table style="width: 100%">
				<s:iterator var="portletDescription" value="#attr.allPortletDescriptions" status="st">
					<s:if test="#st % 3 == 1">
						<tr>
					</s:if>
						<td style="width: 33%;vertical-align: middle;text-align: center;"> 
							<img alt="${portletDescription.name }" src="./${portletDescription.imagePath }"/>
							<br/>
							${portletDescription.name }
							<br/>
							<input type="button" id="updatePortlet_${portletDescription.id}" value="修改" onclick="showAllPortletBtnDialog('./updatePortlet?id=${portletDescription.id}')"/>&nbsp;&nbsp;
							<input type="button" id="deletePortlet_${portletDescription.id}" value="删除"/>
						</td>
					<s:if test="#st % 3 == 0">
						</tr>
					</s:if>
				</s:iterator>
			</table>
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
  </body>
</html>
