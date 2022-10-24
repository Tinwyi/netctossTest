<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>合肥千锋科技－JavaDemoSystem</title>
    <%--引入validform表单验证静态资源--%>
    <link rel="stylesheet" href="resource/validform/css/style.css"/>
    <%--引入jquery文件--%>
    <script src="http://www.mobiletrain.org/js/jquery.min.js"></script>
    <%--引入validform表单验证静态资源--%>
    <script type="text/javascript" src="resource/validform/js/Validform_v5.3.2_min.js"></script>

    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //保存结果的提示
        function showResult() {
            showResultDiv(true);
            window.setTimeout("showResultDiv(false);", 3000);
        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }

        //切换资费类型
        function feeTypeChange(type) {
            var inputArray = document.getElementById("main").getElementsByTagName("input");
            if (type == "1") {
                $(":text:eq(2)").attr("readonly","readonly");
                $(":text:eq(2)").attr("ignore","ignore");
                $(":text:eq(3)").attr("readonly",false);
                $(":text:eq(3)").removeAttr("ignore");
                $(":text:eq(4)").attr("readonly","readonly");
                $(":text:eq(4)").attr("ignore","ignore");
                inputArray[5].readonly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readonly = false;
                inputArray[6].className = "width100";
                inputArray[7].readonly = true;
                inputArray[7].className += " readonly";
                inputArray[7].value = "";
            }
            else if (type == "2") {

                $(":text:eq(2)").attr("readonly",false);
                $(":text:eq(2)").removeAttr("ignore");
                $(":text:eq(3)").attr("readonly",false);
                $(":text:eq(3)").removeAttr("ignore");
                $(":text:eq(4)").attr("readonly",false);
                $(":text:eq(4)").removeAttr("ignore");
                inputArray[5].readonly = false;
                inputArray[5].className = "width100";
                inputArray[6].readonly = false;
                inputArray[6].className = "width100";
                inputArray[7].readonly = false;
                inputArray[7].className = "width100";
            }
            else if (type == "3") {
                $(":text:eq(2)").attr("readonly","readonly");
                $(":text:eq(2)").attr("ignore","ignore");
                $(":text:eq(3)").attr("readonly","readonly");
                $(":text:eq(3)").attr("ignore","ignore");
                $(":text:eq(4)").attr("readonly",false);
                $(":text:eq(4)").removeAttr("ignore");
                inputArray[5].readonly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readonly = true;
                inputArray[6].value = "";
                inputArray[6].className += " readonly";
                inputArray[7].readonly = false;
                inputArray[7].className = "width100";
            }
        }

        $(function (){
            //main_form为form表单的class属性
            $(".main_form").Validform({
                tiptype:3
            })

            var type = '${cost.costType}';
            $(".main_form :radio[value='"+type+"']").attr("checked",true);
            feeTypeChange(type);
        })

    </script>
</head>
<body>
<div id="header"> <img src="resource/images/logo.png" alt="logo" class="left"/> <a href="logout">[退出]</a> </div>
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
            <li><a href="pageFindCost"  class="fee_off"></a></li>
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
    <form action="modifyCost" method="post" class="main_form">
        <div class="text_info clearfix"><span>资费id：</span></div>
        <div class="input_info">
            <input type="text" class="width300" name="costId"  readonly="readonly" value="${cost.costId}"/>
        </div>
        <div class="text_info clearfix"><span>资费名称：</span></div>
        <div class="input_info">
            <input type="text" class="width300" value="${cost.name}" datatype="s1-50" name="costName"  ajaxurl="checkCostName?costName=${cost.name}" errormsg="资费名称长度1-50"/>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info fee_type">
            <input type="radio" name="costType" value="1" id="monthly" onClick="feeTypeChange('1');" />
            <label for="monthly">包月</label>
            <input type="radio" name="costType" value="2" id="package" onClick="feeTypeChange('2');"/>
            <label for="package">套餐</label>
            <input type="radio" name="costType" value="3" id="timeBased" onClick="feeTypeChange('3');"/>
            <label for="timeBased">计时</label>
        </div>
        <div class="text_info clearfix"><span>基本时长：</span></div>
        <div class="input_info">
            <input type="text" name="baseDuration" value="${cost.baseDuration}" datatype="n1-4" class="width100"/>
            <span class="info">小时</span>
        </div>
        <div class="text_info clearfix"><span>基本费用：</span></div>
        <div class="input_info">
            <input type="text" name="baseCost" value="${cost.baseCost}" errormsg="请输入数字"
                   datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" class="width100"/>
            <span class="info">元</span>
        </div>
        <div class="text_info clearfix"><span>单位费用：</span></div>
        <div class="input_info">
            <input type="text" name="unitCost"  errormsg="请输入数字"
                   datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/" value="${cost.unitCost}" class="width100"/>
            <span class="info">元/小时</span>
        </div>
        <div class="text_info clearfix"><span>资费说明：</span></div>
        <div class="input_info_high">
            <textarea class="width300 height70" name="desr" datatype="s0-100">${cost.desr}</textarea>
        </div>
        <div class="button_info clearfix">
            <input type="submit" value="保存" class="btn_save" onClick="showResult();"/>
            <input type="button" value="取消" class="btn_save" onClick="location.href='pageFindCost'" />
        </div>
    </form>
</div>
<div id="footer"> <span>[源自合肥千锋的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span> <br/>
    <span>版权所有(C)合肥千锋信息科技有限公司 </span> </div>
</body>
</html>

