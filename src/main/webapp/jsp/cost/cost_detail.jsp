<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>合肥千锋科技－JavaDemoSystem</title>
    <script src="http://www.mobiletrain.org/js/jquery.min.js"></script>
    <script src="http://lib.sinaapp.com/js/jquery/1.7.2/jquery.min.js" charset="utf-8"></script>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />
    <script>
        $(function (){
            $(".main_form option[value='"+'${cost.status}'+"']").attr("selected",true);
            $(".main_form :radio[value='"+'${cost.costType}'+"']").attr("checked",true);
        })
    </script>
</head>

<body>

<div id="header">
    <img src="resource/images/logo.png"  alt="logo" class="left"/>
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
    <form action="" method="post" class="main_form">
        <div class="text_info clearfix"><span>资费ID：</span></div>
        <div class="input_info"><input type="text" class="readonly" readonly value="${cost.costId}"/></div>
        <div class="text_info clearfix"><span>资费名称：</span></div>
        <div class="input_info"><input type="text" class="readonly" readonly value="${cost.name}"/></div>
        <div class="text_info clearfix"><span>资费状态：</span></div>
        <div class="input_info">
            <select class="readonly" disabled>
                <option value="0">开通</option>
                <option value="1">暂停</option>
                <option value="-1">删除</option>
            </select>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info fee_type">
            <input type="radio" name="radFeeType" value="1" id="monthly" disabled="disabled"/>
            <label for="monthly">包月</label>
            <input type="radio" name="radFeeType" value="2" id="package" disabled="disabled"/>
            <label for="package">套餐</label>
            <input type="radio" name="radFeeType" value="3" id="timeBased" disabled="disabled"/>
            <label for="timeBased">计时</label>
        </div>
        <div class="text_info clearfix"><span>基本时长：</span></div>
        <div class="input_info">
            <input type="text" class="readonly" value="${cost.baseDuration}" readonly/>
            <span>小时</span>
        </div>
        <div class="text_info clearfix"><span>基本费用：</span></div>
        <div class="input_info">
            <input type="text" class="readonly" value="${cost.baseCost}" readonly/>
            <span>元</span>
        </div>
        <div class="text_info clearfix"><span>单位费用：</span></div>
        <div class="input_info">
            <input type="text" class="readonly" value="${cost.unitCost}" readonly/>
            <span>元/小时</span>
        </div>
        <div class="text_info clearfix"><span>创建时间：</span></div>
        <div class="input_info"><input type="text" class="readonly" value="${cost.creatime}" readonly value="2013/1/1 00:00:00"/></div>
        <div class="text_info clearfix"><span>启动时间：</span></div>
        <div class="input_info"><input type="text" class="readonly" value="${cost.startime}" readonly value="2013/1/1 00:00:00"/></div>
        <div class="text_info clearfix"><span>资费说明：</span></div>
        <div class="input_info_high">
            <textarea class="width300 height70 readonly" readonly>${cost.desr}</textarea>
        </div>
        <div class="button_info clearfix">
            <input type="button" value="返回" class="btn_save" onClick="location.href='pageFindCost'"/>
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

