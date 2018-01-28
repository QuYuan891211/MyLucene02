<%--
  Created by IntelliJ IDEA.
  User: qy
  Date: 2018/1/27
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" href="themes/default/easyui.css" type="text/css"></link>
    <link rel="stylesheet" href="themes/icon.css" type="text/css"></link>
    <%--注意顺序--%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="locale/easyui-lang-zh_CN.js"></script>
    <title>Lucene+ easyUI+ pagination</title>
</head>
<body>
    <input type="text" id="keyword" size="4px"/>
    <input type="button" id="search" value="搜索">
    <script type="text/javascript">
        $("#search").click(function () {
            var keyword = $("#keyword").val();
            keyword = $.trim(keyword);
            if(keyword.length <=0 ){
                alert("请输入");
                $("#keyword").val("");
                $("#keyword").focus();
            }else {
                //request.getParameter("keyWords");
//                异步提交请求到服务端
                $("#dg").datagrid("load",{
                    "keyWords": keyword
                });
            }
        })
    </script>

    <table id="dg"></table>
    <script type="text/javascript">
        $("#dg").datagrid({
            url: '${pageContext.request.contextPath}/ArticleServlet?id=' + new Date().getTime(),
            fitColumns: true,
            singleSelect:true,
            columns:[[
                {field:'id',title:'编号',width:100,align:'center'},
                {field:'title',title:'标题',width:100,align:'left'},
                {field:'content',title:'内容',width:100,align:'center'},
                {field:'count',title:'字数',width:100,align:'center'}
            ]],
            pagination : true,
            pageSize : 2,
            pageList : [2]
        });
    </script>
</body>
</html>
