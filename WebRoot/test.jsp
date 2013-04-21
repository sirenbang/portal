<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="./common/meta.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./test.js"></script>
<title>Insert title here</title>
<style type="text/css">
       .hover
       {
           background-color:red;
       }
    </style>
</head>
<body>

<table id="table1" border="1" cellpadding="0" cellspacing="0">
    <tr>
    <th>
    <input type="checkbox" id="checkall" onclick="checkAll1(this)"/>
    </th>
    <th>姓名</th>
    <th>性别</th>
    <th>密码</th>
    <th>地址</th>
    </tr>
    <tr>
    <td>
    <input type="checkbox" id="Checkbox1" />
    </td>
    <td>张三</td>
    <td>男</td>
    <td>zhangsan</td>
    <td>上海</td>
    </tr>
    <tr>
    <td>
    <input type="checkbox" id="Checkbox2" />
    </td>
    <td>李四</td>
    <td>男</td>
    <td>lisi</td>
    <td>安庆</td>
    </tr>
    <tr>
    <td>
    <input type="checkbox" id="Checkbox3" />
    </td>
    <td>王五</td>
    <td>男</td>
    <td>beijing</td>
    <td>北京</td>
    </tr>
    <tr>
    <td>
    <input type="checkbox" id="Checkbox4" />
    </td>
    <td>无名氏</td>
    <td>女</td>
    <td>wumingshi</td>
    <td>上海</td>
    </tr>
    <tr>
    <td>
    <input type="checkbox" id="Checkbox5" />
    </td>
    <td>赵老师</td>
    <td>男</td>
    <td>zhaolaoshi</td>
    <td>浙江</td>
    </tr>
    <tr>
    <td colspan="5" align="center">
    <input type="button" id="btnAdd" runat="server" value="新增" onclick="btnAddRow()" />&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" id="btnDelete" runat="server" value="删除" onclick="btnDeleteRow()" />&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" id="btnSave" runat="server" value="保存" onclick="btnSaveClick()" />
    </td>
    </tr>
    </table>
</body>
</html>