<%@ tag description="Default Layout Tag" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="title" %>

<c:if test="${cookie['lang'] == null || cookie['lang'].value == \"en\"}"><c:set var="locale" value="en_US"/></c:if>
<c:if test="${cookie['lang'].value == \"ru\"}"><c:set var="locale" value="ru_RU"/></c:if>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <script src="<c:url value="/resources/js/bootstrap.bundle.min.js"/>"></script>
    <script src="<c:url value="/resources/js/nav-lang.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
</head>
<body>
<%@ include file="/WEB-INF/parts/_navigation.jsp" %>
<jsp:doBody/>
</body>
</html>