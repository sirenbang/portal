$(document).ready(function () {
	// 1.鼠标移动行变色
	// 方法一：jQuery中的hover(fun(),fun())方法，参数一：第一个方法是添加样式功能，参数二：第二个方法是取消样式功能
	$("#table1 tr").hover(function(){ 
	    $(this).children("td").addClass("hover");
	},function(){ 
	    $(this).children("td").removeClass("hover"); 
	});

	/*方法二：
	$("#table1 tr:gt(0)").hover(function() { 
	    $(this).children("td").addClass("hover"); 
	}, function() { 
	    $(this).children("td").removeClass("hover"); 
	});*/ 

	// 2.奇偶行不同颜色
	$("#table1 tbody tr:odd").css("background-color", "#bbf"); 
	$("#table1 tbody tr:even").css("background-color","#ffc");  
	$("#table1 tbody tr:odd").addClass("odd"); 
	$("#table1 tbody tr:even").addClass("even"); 

	// 3.隐藏一行
	$("#table1 tbody tr:eq(3)").hide(); 
	// 4.隐藏一列
	// 方法一：
	$("#table1 tr td::nth-child(3)").hide();
	 
	/*方法二：
	$("#table1 tr").each(function(){$("td:eq(3)",this).hide()});  
*/
	//5.删除一行
	//删除除第一行外的所有行 
	   //   $("#table1 tr:not(:first)").remove();
	//删除指定行
	 //   $("#table1 tr:eq(3)").remove();
	 
	//6.删除一列
	//删除除第一列外的所有列 
	     // $("#table1 tr th:not(:nth-child(1))").remove();
	    //  $("#table1 tr td:not(:nth-child(1))").remove();
	//删除第一列
	  //    $("#table1 tr td::nth-child(1)").remove();
	 
	// 7.得到（设置）某个单元格的值
	//设置table1,第2个tr的第一个td的值。   
	$("#table1 tr:eq(1) td:nth-child(1)").html("value");   
	//获取table1,第2个tr的第一个td的值。   
	// $("#table1 tr:eq(1) td:nth-child(1)").html();
	 
	// 8.插入一行：
	//在第二个tr后插入一行 
	$("<tr><td>插入3</td><td>插入</td><td>插入</td><td>插入</td></tr>").insertAfter($("#table7 tr:eq(1)"));

	// 9：获取每一行指定的单元格的值
	var arr = [];
	$("#table1 tr td:nth-child(1)").each(function (key, value) {
	   arr.push($(this).html());
	});
	var result = arr.join(',');
});

//全选或全不选
//方法一：
//全选或全不选 此传入的参数为event 如：checkAll(event)
   function checkAll(evt)
   {
     evt=evt?evt:window.event;
     var chall=evt.target?evt.target:evt.srcElement;
     var tbl=$("#table1");
     var trlist=tbl.find("tr");
     for(var i=1;i<trlist.length;i++)
     {
         var tr=$(trlist[i]);
         var input=tr.find("INPUT[type='checkbox']");
           input.attr("checked",chall.checked);
     }
   }

//方法二：
//全选或全不选 此传入的参数为this 如：checkAll(this)
  function checkAll1(evt)
  {
      var tbl=$("#table1");
      var trlist=tbl.find("tr");
      for(var i=1;i<trlist.length;i++)
    {
        var tr=$(trlist[i]);
        var input=tr.find("INPUT[type='checkbox']");
          input.attr("checked",evt.checked);
    }
  }

//方法三：
//全选或全不选 此传入的参数为this 如：checkAll(this)
function checkAll2(evt)
{
    $("#table1 tr").find("input[type='checkbox']").each(function(i){
    $(this).attr("checked",evt.checked);
    });
}

//方法四：
//全选或全不选 此传入的参数为this 如：checkAll(this)
function checkAll3(evt)
{
    $("#table1 tr").find("input[type='checkbox']").attr("checked",evt.checked);
}

//客户端动态添加行
function btnAddRow()
{
    //行号是从0开始，最后一行是新增、删除、保存按钮行 故减去2
    var rownum=$("#table1 tr").length-2;
    var chk="<input type='checkbox' id='chk_"+rownum+"' name='chk_"+rownum+"'/>";
    var text="<input type='text' id='txt_"+rownum+"' name='txt_"+rownum+"' width='75px'/>";
    var sel="<select id='sel_"+rownum+"'><option value='1'>男</option><option value='0'>女</option></select>";
    var row="<tr><td>"+chk+"</td><td>"+text+"</td><td>"+sel+"</td><td>"+text+"</td><td>"+text+"</td></tr>";
    $(row).insertAfter($("#table1 tr:eq("+rownum+")"));   
}

//客户端删除一行
//每次只能删除一行，删除多行时出错
function btnDeleteRow()
{
   $("#table1 tr").find("input[type='checkbox']").each(function(i){
   if($(this).attr("checked"))
   { 
       if(i!=0)//不能删除行标题
       {
            $("#table1 tr:eq("+i+")").remove();
       }
   }
   });
}

//这个比上面的要好，可以一下删除多个记录
function btnDeleteRow()
{
   $("#table1 tr").each(function(i){
       var chk=$(this).find("input[type='checkbox']");
       if(chk.attr("id")!="checkall")//不能删除标题行
       {
       if(chk.attr("checked"))
       {
          $(this).remove();
       }
       }
    });
}

//客户端保存
function btnSaveClick()
{
   //find()方法中我暂时不知道如何设定多个筛选条件，所以下面得不到select列表的值
   //$("#table1 tr td").find("input[type='text']" || "select").each(function(i){
   //alert($(this).val());
   //});
             
   $("#table1 tr").find("td").each(function(i){
      if($(this).find("input[type='text']").length>0)
      {
          alert($(this).find("input[type='text']").val());
      }
      else if($(this).find("select").length>0)
      {
          alert($(this).find("select").val());
      }
    });
}
	
