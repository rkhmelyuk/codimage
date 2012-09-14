<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Coder Image</title>
    <link rel="stylesheet" type="text/css" href="<spring:url value="/css/styles.css"/>"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
    <script type="text/javascript" src="<spring:url value="/js/codimage.js"/>"></script>
    <script id="imageTmpl" type="text/html">
        <img src="{src}" rel="image"/>
    </script>
</head>
<body>
<div id="image"></div>
</body>
</html>