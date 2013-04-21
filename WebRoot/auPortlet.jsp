 <%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>
    	<s:if test="#actionType == null || actionType == 1">
    		添加模块
    	</s:if><s:else>
    		修改模块
    	</s:else>
    </title>
    <script type="text/javascript" src="./auPortlet.js"></script>
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
			<div style="margin-left: 100px;margin-top: 40px;">
				<h3><s:if test="#actionType == null || actionType == 1">
			    		添加模块
			    	</s:if><s:else>
			    		修改模块
			    	</s:else>
	    		</h3>
				<form id="portlet-form" method="post">
		    		模块名称：<input type="text" name="abstractPortletDescription.name" id="name" value="${updatePortlet.abstractPortletDescription.name }" class="text ui-widget-content ui-corner-all"/>
		    		<br/>
		    		模块类型：<select name="abstractPortletDescription.type" id="portletType" class="text ui-widget-content ui-corner-all">
							<s:if test="#attr.updatePortlet == null">
								<option value="0" selected="selected">请选择</option>
								<s:iterator var="portletType" value="#application.allPortletType ">
									<option value="${portletType.code }">${portletType.value }</option>
								</s:iterator>
							</s:if><s:else>
								<option value="0">请选择</option>
								<s:iterator var="portletType" value="#application.allPortletType ">
									<s:if test="#attr.updatePortlet.abstractPortletDescription.type == portletType.code">
										<option value="${portletType.code }" selected="selected" disabled="disabled">${portletType.value }</option>
									</s:if>
								</s:iterator>
							</s:else>
					</select>
					<br/>
					高&nbsp;&nbsp;&nbsp;&nbsp;度：<input type="text" name="abstractPortletDescription.width" id="width" value="${updatePortlet.abstractPortletDescription.width }" class="text ui-widget-content ui-corner-all"/>
		    		<br/>
		    		图片路径：<input type="file" name="abstractPortletDescription.imagePath" id="imagePath" value="${updatePortlet.abstractPortletDescription.imagePath }" class="text ui-widget-content ui-corner-all"/>
		    		<br/>
		    		模块描述：<textarea name="abstractPortletDescription.description" id="description" class="text ui-widget-content ui-corner-all" ></textarea>	
					<br/>
					<div style="display: none" id="iframeEx-form">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;URL:
						<input type="text" name="iframe.url" id="url" value="${updatePortlet.url }" class="text ui-widget-content ui-corner-all"/>
						<br/>
					</div>
					<div style="display: none" id="linkEx-form">
						<input type="hidden" id="linkPorpertyStr" name="linkPorpertyStr"/>
						<s:if test="#attr.updatePortlet == null">
							<a href="#" onclick="showLinkManageDialog()">链接管理</a><!-- linkManageAction -->
						</s:if><s:else>
							<a href="#" onclick="showLinkManageDialog()">链接管理</a><!-- linkManageAction?id=${updatePorlet.id } -->
						</s:else>
						<br/>
					</div>
					<div style="display: none" id="gridEx-form">
						grid
					</div>
					<s:if test="#actionType == null || actionType == 1">
			    		<input type="button" class="ui-button" id="addPortletBtn" value="添加模块"/>
			    	</s:if><s:else>
			    		<input type="button" class="ui-button" id="addPortletBtn" value="修改模块"/>
			    	</s:else>
		    	</form>
		    </div>	
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
	    
	    <!-- 链接管理对话框 DIV -->
	    <div id="linkManageDialog" class="ui-widget ui-widget-content ui-corner-all" title="链接管理">
	    	模块名称：<span id="linkManage_portletName"></span>
			<table id="linkManageTable">
				<thead>
					<tr>
						<th>名称</th>
						<th>URL</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:if test="#attr.updatePortlet != null">
						<s:iterator value="linkProperty" var="#attr.updatePortlet.linkProperties" status="st">
							<tr <s:if test="#st.odd">style="background-color:#BBBBBB"</s:if>>
								<td align="center">${linkProperty.description }</td>
								<td align="center">${linkProperty.url }</td>
								<td align="center">
									<a href="updateLinkProperty?id=${linkProperty.id }">修改</a>&nbsp;&nbsp;
									<a href="deleteLinkPropertyAction?id=${linkProperty.id }">删除</a>
								</td>
							</tr>
						</s:iterator>
					</s:if>
				</tbody>
			</table>
			<hr/>
			名称：<input type="text" id="description" class="text ui-widget-content ui-corner-all"/>
			<br/>
			&nbsp;URL：<input type="text" id="url" class="text ui-widget-content ui-corner-all"/>
			<br/>
			<input type="button" id="addLinkPropertyBtn" value="添加链接"/> &nbsp;&nbsp;
			<input type="button" id="resetLinkPropertyBtn" value="重置">
	    </div>
  </body>
</html>
