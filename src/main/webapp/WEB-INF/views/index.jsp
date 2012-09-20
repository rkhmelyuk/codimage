<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>CodeImage</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="shortcut icon" href="<spring:url value="/image/favicon.png"/>" type="image/png">
    <link rel="stylesheet" media="screen" type="text/css" href="<spring:url value="/css/styles.css"/>"/>

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
    <script type="text/javascript" src="<spring:url value="/js/codimage.js"/>"></script>

    <script id="imageTmpl" type="text/html">
        <img src="{src}" rel="image"/>
    </script>
</head>
<body>
    <div id="image" class="black-and-white">
        <div class="slide-controls">
            <a href="javascript:void(0);" id="prev">Previous</a>
            <a href="javascript:void(0);" id="play" rel="play">Pause</a>
            <a href="javascript:void(0);" id="next">Next</a>
        </div>
    </div>
</body>
</html>