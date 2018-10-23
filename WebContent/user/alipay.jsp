<%@ page language="java" import="java.util.*" pageEncoding="gb2312" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <meta charset="gb2312"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta content="telephone=no" name="format-detection"/>
    <title>正在跳转到支付页面...</title>
    <script type="text/javascript">
        alert('${title}');
    </script>
</head>
<body>
<form id="alipaysubmit" name="alipaysubmit" action="https://shenghuo.alipay.com/send/payment/fill.htm" method="POST">
    <input type="hidden" name="optEmail" value="876003335@qq.com"/>
    <input type="hidden" name="payAmount" value="${money}"/>
    <input type="hidden" name="title" value="${title}"/>
    <input type="hidden" name="memo" value="${note}"/>

    <input type="hidden" value="submit" value="sending...click here...">
</form>
<script type="text/javascript">
    document.forms['alipaysubmit'].submit();
</script>
</body>
</html>
