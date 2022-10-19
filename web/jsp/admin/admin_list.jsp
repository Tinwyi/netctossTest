<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <%--引入jquery文件--%>
    <script src="http://www.mobiletrain.org/js/jquery.min.js"></script>
    <%--引入分页静态资源--%>
    <link rel="stylesheet" href="resource/layui/css/layui.css" tppabs="http://layui/dist/css/layui.css"  media="all">
    <script src="resource/layui/layui.js" charset="utf-8"></script>
    <title>合肥千锋－JavaDemoSystem</title>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //显示角色详细信息
        function showDetail(flag, a) {
            var detailDiv = a.parentNode.getElementsByTagName("div")[0];
            if (flag) {
                detailDiv.style.display = "block";
            }
            else
                detailDiv.style.display = "none";
        }
        //重置密码
        function resetPwd() {
            location.href="resetAdminPassword?adminId=${currentAdmin.adminId}";
        }
        //删除
        function deleteAdmin() {
            var r = window.confirm("确定要删除此管理员吗？");
            if(r){
                location.href="deleteAdmin?adminId=${currentAdmin.adminId}";
                document.getElementById("operate_result_info").style.display = "block";
            }
        }
        //全选
        function selectAdmins(inputObj) {
            var inputArray = document.getElementById("datalist").getElementsByTagName("input");
            for (var i = 1; i < inputArray.length; i++) {
                if (inputArray[i].type == "checkbox") {
                    inputArray[i].checked = inputObj.checked;
                }
            }
        }

        $(function (){
            var ids = $("td.adminId").eq(0).text();
            for (let i = 1; i < $("td.adminId").text().length; i++) {
                ids += ","+$("td.adminId").eq(i).text();
            }
            $.ajax({
                url:"findCurrentRole",
                data:{ids:ids},
                dataType:"json", //string ==> json数组
                async:false,
                success: function(roles) {
                    var html = "<option value=''>全部</option> ";
                    for(var i=0; i < roles.length; i++) {
                        var roleId = roles[i].roleId;
                        var roleName = roles[i].roleName;
                        html += "<option value='"+roleId+"'>"+roleName+"</option> ";
                    }
                    $("#selModules").html(html);
                }
            });
            $("#selModules option[value='"+'${roleId}'+"']").attr("selected",true);
        })
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
    <form action="pageFindAdmin" method="post">
        <input id="currentPage" type="hidden" name="currentPage">
        <input id="pageSize" type="hidden" name="pageSize">
        <div class="search_add">
            <div> 角色：
                <select id="selModules" name='roleId' class="select_search">

                </select>
            </div>
            <div>姓名：
                <input type="text" name="name" value="${name}" class="text_search width200"/>
            </div>
            <div>
                <input type="submit" value="搜索" class="btn_search"/>
            </div>
            <input type="button" value="密码重置" class="btn_add" onClick="resetPwd();"/>
            <input type="button" value="增加" class="btn_add" onClick="location.href='toAddAdmin'"/>
        </div>
        <div id="operate_result_info" class="operate_fail"> <img src="../images/close.png"  onClick="this.parentNode.style.display='none';"/> <span>删除失败！数据并发错误。</span> </div>
        <div id="data">
            <table id="datalist">
                <tr>
                    <th class="th_select_all"> <input type="checkbox" onClick="selectAdmins(this);"/>
                        <span>全选</span>
                    </th>
                    <th>管理员ID</th>
                    <th>姓名</th>
                    <th>登录名</th>
                    <th>电话</th>
                    <th>电子邮件</th>
                    <th>授权日期</th>
                    <th class="width100">拥有角色</th>
                    <th></th>
                </tr>
                <c:forEach items="${page.list}" var="admin">
                    <tr>
                        <td><input type="checkbox"/></td>
                        <td class="adminId">${admin.adminId}</td>
                        <td>${admin.name}</td>
                        <td>${admin.adminCode}</td>
                        <td>${admin.telephone}</td>
                        <td>${admin.email}</td>
                        <td>${admin.enrollDate}</td>
                        <td>${admin.roleName}</td>
                        <td class="td_modi"><input type="button" value="修改" onclick="location.href='toModifyAdmin?adminId=${admin.adminId}'" class="btn_modify"/>
                            <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin()"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </form>
    <%--分页--%>
    <div id="demo7"> </div>
</div>
<div id="footer">
    <p>[源自合肥千锋的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
    <p>版权所有(C)合肥千锋信息科技有限公司 </p>
</div>
</body>
<script>
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
</html>

