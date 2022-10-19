<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>合肥千锋－JavaDemoSystem</title>
    <%--引入validform表单验证静态资源--%>
    <link rel="stylesheet" href="resource/validform/css/style.css"/>
    <%--引入jquery文件--%>
    <script src="http://www.mobiletrain.org/js/jquery.min.js"></script>
    <%--引入validform表单验证静态资源--%>
    <script type="text/javascript" src="resource/validform/js/Validform_v5.3.2_min.js"></script>

    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" tppabs="http://demo.mycodes.net/houtai/isoakJavaDemoSystem/styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" tppabs="http://demo.mycodes.net/houtai/isoakJavaDemoSystem/styles/global_color.css"/>
    <script language="javascript" type="text/javascript">
        $(function (){
            $.ajax({
                url:"findAllRole",
                dataType:"json", //string ==> json数组
                async:false,
                success: function(roles) {
                    var html = "";
                    for(var i=0; i < roles.length; i++) {
                        var roleId = roles[i].roleId;
                        var roleName = roles[i].roleName;
                        html += "<li><input type='checkbox' name='roleId' value='"+roleId+"'/>"+roleName+"</li> ";
                    }
                    $("#roleUl").html(html);
                }
            });

            //main_form为form表单的class属性
            $(".main_form").Validform({
                tiptype:3
            })

            var roleIds =  '${admin.roleIds}'.split(",");
            for (let i = 0; i < roleIds.length; i++) {
                $(":checkbox[name='roleId'][value='"+roleIds[i]+"']").attr("checked",true);
            }
        })
    </script>
</head>
<body>

<div id="header">
    <img src="resource/images/logo.png" tppabs="http://demo.mycodes.net/houtai/isoakJavaDemoSystem/images/logo.png" alt="logo" class="left"/>
    <a href="logout">[退出]</a>
</div>


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
    <div id="save_result_info" class="save_success">保存成功！</div>
    <form action="modifyAdmin" method="post" class="main_form">
        <div class="text_info clearfix"><span>姓名：</span></div>
        <div class="input_info">
            <input type="hidden" name="adminId" value="${admin.adminId}"/>
            <input type="text" name="name" datatype="s1-20"  value="${admin.name}"  errormsg="20长度以内的汉字、字母、数字的组合"/>
        </div>
        <div class="text_info clearfix"><span>管理员账号：</span></div>
        <div class="input_info">
            <input type="text" name="adminCode" datatype="s1-30" value="${admin.adminCode}" ajaxurl="checkAdminCode" errormsg="30长度以内的字母、数字和下划线的组合"/>
        </div>
        <div class="text_info clearfix"><span>密码：</span></div>
        <div class="input_info">
            <input type="password" name="password" datatype="s1-30" value="${admin.password}" errormsg="密码范围在1~30位之间！"/>
        </div>
        <div class="text_info clearfix"><span>重复密码：</span></div>
        <div class="input_info">
            <input type="password"  name="password2" datatype="*" value="${admin.password}" recheck="password" errormsg="您两次输入的账号密码不一致！"/>
        </div>
        <div class="text_info clearfix"><span>电话：</span></div>
        <div class="input_info">
            <input type="text" name="telephone" datatype="m" value="${admin.telephone}" errormsg="请输入正确的手机号！" class="width200"/>
        </div>
        <div class="text_info clearfix"><span>Email：</span></div>
        <div class="input_info">
            <input type="text" name="email" datatype="e" value="${admin.email}" errormsg="邮箱格式不正确！" class="width200"/>
        </div>
        <div class="text_info clearfix"><span>角色：</span></div>
        <div class="input_info_high">
            <div class="input_info_scroll">
                <ul id="roleUl">
                </ul>
            </div>
            <input type='checkbox' name='roleId' datatype="*" style="display: none"/>
        </div>
        <div class="button_info clearfix">
            <input type="submit" value="保存" class="btn_save" onClick="showResult();"/>
            <input type="button" value="取消" class="btn_save" onclick="location.href='pageFindAdmin'"/>
        </div>
    </form>
</div>

<div id="footer">
    <span>[源自合肥千锋的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
    <br/>
    <span>版权所有(C)合肥千锋信息科技有限公司 </span>
</div>
</body>
</html>

