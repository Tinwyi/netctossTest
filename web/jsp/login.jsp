<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>NetCTOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="resource/styles/global_color.css" />

</head>
<body class="index">
<div class="login_box">

    <p style="color: red">${msg}</p>
    <form action="login" method="post">
        <table>
            <tr>
                <td class="login_info">账号：</td>
                <td colspan="2"><input name="adminCode" type="text" class="width150" /></td>
            </tr>
            <tr>
                <td class="login_info">密码：</td>
                <td colspan="2"><input name="password" type="password" class="width150" /></td>
            </tr>
            <tr>
                <td></td>
                <td class="login_button"  colspan="2">
                    <button type="submit">
                        <img src="resource/images/login_btn.jpg" />
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
