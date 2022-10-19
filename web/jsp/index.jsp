<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/20
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>合肥千锋－JavaDemoSystem</title>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css"/>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css"/>
</head>
<body class="index">
<div id="index_navi">
    <ul id="menu">
        <li><a href="indexPage" class="index_on"></a></li>
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
</body>
</html>