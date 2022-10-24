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
    <link rel="stylesheet" href="resource/layui/css/layui.css" tppabs="http://layui/dist/css/layui.css"  media="all">
    <script src="resource/layui/layui.js" charset="utf-8"></script>

    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //排序按钮的点击事件
        function sort(btnObj) {
            var order = $("#orderType").val();
            var arr =  order.split(",")
            if($(btnObj).attr("name")=="a"){
                var n = arr.indexOf("aa");
                if(n>=0){
                    arr.splice(n,1);
                    arr.splice(0,0,"ab");
                }else {
                    n = arr.indexOf("ab");
                    arr.splice(n,1);
                    arr.splice(0,0,"aa");
                }
            }else if($(btnObj).attr("name")=="b"){
                var n = arr.indexOf("ba");
                if(n>=0){
                    arr.splice(n,1);
                    arr.splice(0,0,"bb");
                }else {
                    n = arr.indexOf("bb");
                    arr.splice(n,1);
                    arr.splice(0,0,"ba");
                }
            }else if($(btnObj).attr("name")=="c"){
                var n = arr.indexOf("ca");
                if(n>=0){
                    arr.splice(n,1);
                    arr.splice(0,0,"cb");
                }else {
                    n = arr.indexOf("cb");
                    arr.splice(n,1);
                    arr.splice(0,0,"ca");
                }
            }
            order = arr.toString();
            $("#orderType").val(order);
        }

        //启用
        function startFee(id) {
            var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
            if(r){
                location.href="enableCost?costId="+id;
                document.getElementById("operate_result_info").style.display = "block";
            }

        }
        //删除
        function deleteFee(id) {
            var r = window.confirm("确定要删除此资费吗？");
            if(r){
                location.href="deleteCost?costId="+id;
                document.getElementById("operate_result_info").style.display = "block";
            }
        }

        $(function (){
            var order = $("#orderType").val();

            if(order.indexOf("aa")>=0){
                $(":submit[name='a']").attr("class","sort_asc");
            }else {
                $(":submit[name='a']").attr("class","sort_desc");
            }
            if(order.indexOf("ba")>=0){
                $(":submit[name='b']").attr("class","sort_asc");
            }else {
                $(":submit[name='b']").attr("class","sort_desc");
            }
            if(order.indexOf("ca")>=0){
                $(":submit[name='c']").attr("class","sort_asc");
            }else {
                $(":submit[name='c']").attr("class","sort_desc");
            }


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
    <form action="pageFindCost" method="post">
        <div class="search_add">
            <div>
                <input type="hidden" name="orderType" id="orderType" value="${orderType}"/>
                <input type="submit" style="background-color: green" value="月租" class="" name="a" onClick="sort(this);"/>
                <input type="submit" style="background-color: green" value="时费" class="" name="b" onClick="sort(this);"/>
                <input type="submit" style="background-color: green" value="时长" class="" name="c" onClick="sort(this);"/>
            </div>
            <input type="button" value="增加" class="btn_add" onClick="location.href='toAddCost'"/>
        </div>

        <div id="operate_result_info" class="operate_success">
            <img src="resource/images/close.png" onClick="this.parentNode.style.display='none';"/>
            删除成功！
        </div>

        <div id="data">
            <table id="datalist">
                <tr>
                    <th>资费ID</th>
                    <th class="width100">资费名称</th>
                    <th>基本时长</th>
                    <th>基本费用</th>
                    <th>单位费用</th>
                    <th>创建时间</th>
                    <th>开通时间</th>
                    <th class="width50">状态</th>
                    <th class="width200"></th>
                </tr>
                <c:forEach items="${page.list}" var="cost">
                    <tr>
                        <td>${cost.costId}</td>
                        <td><a href="findCostById?costId=${cost.costId}" style="color: blue">${cost.name}</a></td>
                        <td>${cost.baseDuration}</td>
                        <td>${cost.baseCost}</td>
                        <td>${cost.unitCost}</td>
                        <td>${cost.creatime}</td>
                        <td>${cost.startime}</td>
                        <c:if test="${fn:contains(cost.status,'1')}">
                            <td>
                                暂停
                            </td>
                            <td>
                                <input type="button" style="color: blue;" value="启用" class="btn_start" onClick="startFee(${cost.costId});"/>
                                <input type="button" value="修改" class="btn_modify" onClick="location.href='toModifyCost?costId=${cost.costId}'"/>
                                <input type="button" value="删除" class="btn_delete" onClick="deleteFee(${cost.costId});"/>
                            </td>
                        </c:if>
                        <c:if test="${fn:contains(cost.status,'0')}">
                            <td>
                                开通
                            </td>
                            <td>

                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            <p>业务说明：<br/>
                1、创建资费时，状态为暂停，记载创建时间；<br/>
                2、暂停状态下，可修改，可删除；<br/>
                3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br/>
                4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
            </p>
        </div>

        <%--分页--%>
        <div id="demo7"> </div>
        <input id="currentPage" type="hidden" name="currentPage"/>
        <input id="pageSize" type="hidden" name="pageSize"/>
    </form>
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

