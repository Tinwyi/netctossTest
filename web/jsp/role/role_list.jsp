<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>合肥千锋－JavaDemoSystem</title>

    <%--引入分页静态资源--%>
    <link rel="stylesheet" href="resource/layui/css/layui.css" tppabs="http://layui/dist/css/layui.css"  media="all">
    <script src="resource/layui/layui.js" charset="utf-8"></script>
    <script src="http://www.mobiletrain.org/js/jquery.min.js"></script>

    <link type="text/css" rel="stylesheet" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" href="resource/styles/global_color.css"/>
    <script language="javascript" type="text/javascript">
        function deleteRole(roleId) {
            var r = window.confirm("确定要删除此角色吗？");
            if(r){
   //             document.getElementById("operate_result_info").style.display = "block";
                location.href="deleteRoleById?roleId="+roleId;
            }
        }
        layui.use(['laypage', 'layer'], function(){
            var laypage = layui.laypage,layer = layui.layer;

            //完整功能
            laypage.render({
                elem: 'demo7'
                ,count: ${page.count}        //总条数
                ,limit: ${page.pageSize}     //一页显示的条数
                ,curr : ${page.currentPage}  //当前页
                ,limits:[5,10,15,20]    //一页显示多少条的选项
                ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                ,jump: function(obj, first){  //点击页码的回调方法（会执行该函数）
                    var limit = obj.limit; //一页显示多少条
                    var curr = obj.curr;   //你点击的页数


                    if(!first) {
                        $("#currentPage").val(curr);
                        $("#pageSize").val(limit);

                        //把显示哪一页，一页显示的条数传到后台
                        $("#main form:first").submit();   //jquer提交表单
                    }
                }
            });
        });
    </script>
</head>
<body>
<div id="header"> <img src="resource/images/logo.png"  alt="logo" class="left"/> <a href="logout">[退出]</a> </div>
<div id="navi">
    <ul id="menu">
        <li><a href="indexPage"  class="index_off"></a></li>
        <c:if test="${fn:contains(moduleNames, '角色管理')}">
            <li><a href="pageFindRole" class="role_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '管理员管理')}">
            <li><a href="pageFindAdmin"  class="admin_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '资费管理')}">
            <li><a href="fee/fee_list.html"  class="fee_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '账务账号')}">
            <li><a href="account/account_list.html"  class="account_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '业务账号')}">
            <li><a href="service/service_list.html"  class="service_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '账单')}">
            <li><a href="bill/bill_list.html"  class="bill_off"></a></li>
        </c:if>

        <c:if test="${fn:contains(moduleNames, '报表')}">
            <li><a href="report/report_list.html" class="report_off"></a></li>
        </c:if>
    </ul>
</div>
<div id="main">
    <form action="pageFindRole" method="post">
        <input id="currentPage" type="hidden" name="currentPage">
        <input id="pageSize" type="hidden" name="pageSize">

        <div class="search_add">
            <div>角色名称：
                <input type="text" value="${roleName}" name="roleName" class="text_search width200"/>
            </div>
            <div>
                <input type="submit" value="搜索" class="btn_search"/>
            </div>

            <input type="button" value="增加" class="btn_add" onClick="location.href='toAddRolePage'"/>
        </div>
    </form>
    <div id="operate_result_info" class="operate_success"> <img src="resource/images/close.png" onClick="this.parentNode.style.display='none';"/> 删除成功！ </div>
    <div id="data">
        <table id="datalist">
            <tr>
                <th>角色 ID</th>
                <th>角色名称</th>
                <th class="width600">拥有的权限</th>
                <th class="td_modi"></th>
            </tr>
            <c:forEach items="${page.list}" var="role">
                <tr>
                    <td>${role.roleId}</td>
                    <td>${role.roleName}</td>
                    <td>${role.moduleNames}</td>
                    <td><input type="button" value="修改" class="btn_modify" onClick="location.href='toUpdatePolePage?roleId=${role.roleId}'"/>
                        <input type="button" value="删除" class="btn_delete" onClick="deleteRole(${role.roleId});"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%--分页--%>
    <div id="demo7" style="text-align: center"> </div>
</div>
<div id="footer">
    <p>[源自顶端的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
    <p>版权所有(C)合肥千锋IT培训集团公司 </p>
</div>
</body>

</html>