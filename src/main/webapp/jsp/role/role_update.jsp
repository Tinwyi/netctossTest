<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/18
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
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

    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />
    <script language="javascript" type="text/javascript">

        //页面加载完成后执行
        $(function(){

            //组装权限的多选框
            $.ajax({
                url:"findAllModule",
                dataType:"json", //string ==> json数组
                async:false,      //定义ajax是否同步   同步：ajax请求执行结束了，后面的代码才能执行
                success: function(modules) {
                    var html = "";
                    for(var i=0; i< modules.length; i++) {
                        var moduleId = modules[i].moduleId;
                        var moduleName = modules[i].moduleName;
                        html += "<li><input type='checkbox' name='moduleId' value='"+moduleId+"'/>"+moduleName+"</li> ";
                    }
                    $("#moduleUl").html(html);
                }
            });

            //main_form为form表单的class属性
            $(".main_form").Validform({
                tiptype:3
            });


            //菜单权限回显 1,2
            var moduleIds = '${vo.moduleIds}';     //字符串一定要放到单双引号中
            var moduleIdArr = moduleIds.split(",");  //[1,2]
            for(var i=0; i<moduleIdArr.length; i++) {
                $(":checkbox[name='moduleId'][value='"+moduleIdArr[i]+"']").attr("checked",true);
            }

        });


    </script>
</head>
<body>
<div id="header"> <img src="resource/images/logo.png" alt="logo" class="left"/> <a href="#">[退出]</a> </div>
<div id="navi">
    <ul id="menu">
        <li><a href="../index.html"  class="index_off"></a></li>
        <li><a href="role_list.html"  class="role_on"></a></li>
        <li><a href="../admin/admin_list.html"  class="admin_off"></a></li>
        <li><a href="../fee/fee_list.html"  class="fee_off"></a></li>
        <li><a href="../account/account_list.html"  class="account_off"></a></li>
        <li><a href="../service/service_list.html"  class="service_off"></a></li>
        <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="../report/report_list.html"  class="report_off"></a></li>
        <li><a href="../user/user_info.html"  class="information_off"></a></li>
        <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
    </ul>
</div>
<div id="main">

    <form action="updateRole" method="post" class="main_form">
        <div class="text_info clearfix"><span>角色名称：</span></div>

        <input type="hidden" name="roleId" value="${vo.roleId}"/>

        <div class="input_info">
            <input type="text" name="roleName" value="${vo.roleName}"  datatype="s2-20" ajaxurl="checkRoleName" errormsg="角色名称长度2-20" class="width200"/>
        </div>
        <div class="text_info clearfix"><span>设置权限：</span></div>
        <div class="input_info_high">
            <div class="input_info_scroll">
                <ul id="moduleUl">

                </ul>
            </div>
            <span class="required">*</span>
            <input type='checkbox' datatype='*' name='moduleId' style="display: none"/>

        </div>
        <div class="button_info clearfix">
            <input type="submit" value="保存" class="btn_save" onClick="showResult();"/>
            <input type="button" value="取消" class="btn_save"/>
        </div>
    </form>
</div>
<div id="footer">
    <p>[源自顶端的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
    <p>版权所有(C)合肥千锋IT培训集团公司 </p>
</div>
</body>

<script>


    /*$(":text[name='roleName']").blur(function(){
        var roleName = $(this).val();

        $.ajax({
            url:"checkRoleName",
            data:{"roleName":roleName},
            type:"post",
            success: function(result) {
                if(result == 'y'){
                    $("#roleNameTip").html("该角色名称可用");
                } else {
                    $("#roleNameTip").html("该角色名称已被使用");
                }
            }
        });

    });*/
</script>
</html>

