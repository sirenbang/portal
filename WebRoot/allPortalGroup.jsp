<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
  	<jsp:include page="./common/meta.jsp"></jsp:include>
    <title>门户组管理</title>
    <script type="text/javascript" src="./allPortalGroup.js"></script>
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
  	<input type="hidden" id="updatePortalGroupId" value="${session.updatePortalGroup.id }"/>
  	<div style="display: none;">
  		<!-- 退出form start -->
  		<form action="logoutAction" id="logout-form"></form>
  		<!-- 退出form end -->
  		<!-- 翻页form start -->
  		<form action="showPortalGroupAction" id="showRole-form">
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
			<form id="searchRole-form" action="./showPortalGroupAction" method="post">
				门户组名：<input type="text" name="searchPortalGroupName" value="${searchPortalGroupName }" id="searchPortalGroupName" class="text ui-widget-content ui-corner-all"  /> &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" class="ui-button" value="查询"/>
			</form>
		<hr/>
			<table width="100%">
				<caption>门户组信息列表</caption>
				<thead>
					<tr>
						<th>序号</th>
						<th>门户组名</th>
						<th>是否可见</th>
						<th>描述</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="showPortalGroup" value="#session.showPortalGroups" status="st">
						<tr <s:if test="#st.odd">style="background-color:#BBBBBB"</s:if>>
							<td align="center">${st.count }</td>
							<td><span id="portalGroupName_${showPortalGroup.id }">${showPortalGroup.name  }</span></td>
							<td><input type="hidden" id="hidden_${showPortalGroup.id }" value="${showPortalGroup.hidden }"/>
							<s:if test="#showPortalGroup.hidden == 0">
								<span>可见</span>
							</s:if><s:else>
								<span>不可见</span>
							</s:else></td>
							<td><span id="portalGroupdesc_${showPortalGroup.id }">${showPortalGroup.description}</span> </td>
							<td>
								<a href="updatePortalGroup?id=${showPortalGroup.id }">修改</a>&nbsp;&nbsp;<a href="deletePortalGroupAction?id=${showPortalGroup.id }">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
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
							<input type="button" id="addPortalGroupBtn" value="增加"/>	
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
        <!-- 添加门户组对话框 -->
        <div id="addPortalGroupDialog"  class="ui-widget ui-widget-content ui-corner-all" title="添加门户组">
        	<form action="addPortalGroupAction" id="addPortalGroup-form" method="post">
        		
        		门户组名：<input type="text" name="portalGroup.name" id="addPortalGroup_name" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		       	是否可见：<input type="radio" name="portalGroup.hidden" value="0" id="addPortalGroup_hidden"/>是
		      		 	 <input type="radio" name="portalGroup.hidden" value="1" id="addPortalGroup_hidden"/>否
		       	<br/>
		      	描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<textarea name="portalGroup.description" id="addportalGroup_description" class="text ui-widget-content ui-corner-all" ></textarea>	
        	</form>
        </div>
        <!--修改门户组对话框 -->
        <div id="updatePortalGroupDialog"  class="ui-widget ui-widget-content ui-corner-all" title="修改门户组">
			<form action="updatePortalGroupAction" id="updatePortalGroup-form" method="post">
				<input type="hidden" name="portalGroup.id" value="${session.portalGroup.id }" />
				门户组名：<input type="text" name="portalGroup.name" value="${session.updatePortalGroup.name }" id="updatePortalGroup_name" class="text ui-widget-content ui-corner-all" />
		       		<br/>
		       	是否可见：
	       		<s:if test="#session.updatePortalGroup.hidden == 0">
	       			<input type="radio" name="hidden" value="0" id="updateRole_enabled" checked="checked"/>是
     		 	 	<input type="radio" name="hidden" value="1" id="updateRole_enabled"/>否
	       		</s:if> <s:else>
	       			<input type="radio" name="hidden" value="0" id="updateRole_enabled"/>是
     		 	 	<input type="radio" name="hidden" value="1" id="updateRole_enabled" checked="checked" />否
	       		</s:else>
		       	<br/>
		       	<input type="hidden" id="desc_hidden" value="${session.updatePortalGroup.description }"/>
		      	描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：<textarea name="description" id="updatePortalGroup_description" class="text ui-widget-content ui-corner-all" >${session.updatePortalGroup.description }</textarea>	
			</form>        	
        </div>
  </body>
</html>
