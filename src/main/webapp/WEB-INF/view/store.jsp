<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="<c:url value="/resources/js/store.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/row-details.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/context-path.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/cart-actions.js" />" type="text/javascript"></script>

<c:if test="${cookie['lang'] == null || cookie['lang'].value == \"en\"}"><c:set var="lang" value="en"/></c:if>
<c:if test="${cookie['lang'].value == \"ru\"}"><c:set var="lang" value="ru"/></c:if>
<fmt:setBundle basename="messages"/>

<t:mainLayout title="EFT Store">
    <div class="container-fluid bg-default content-padding"
         style="background-image: url(<c:url value="/resources/images/bg-store.jpg"/>)">
        <div class="container-fluid">
            <h1><fmt:message key="${greeting}"/>, ${user.getNickname()}</h1>
        </div>

        <table class="table table-bordered table-dark table-hover table-hover-cursor text-primary-light mt30">
            <thead>
            <tr>
                <th scope="col"><h3>#</h3></th>
                <th scope="col"><h3><fmt:message key="label.storeItemImage"/></h3></th>
                <th scope="col"><h3><fmt:message key="label.storeItemTitle"/></h3></th>
                <th scope="col"><h3><fmt:message key="label.storeItemPrice"/></h3></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}" varStatus="foreach">
                <tr class="item-row" data-store-item-id="${item.getId()}">
                    <th scope="row">${foreach.index + 1}</th>
                    <td class="text-center">
                        <img class="item-img" src="<c:url value="/resources/images/items/${item.getImageName()}" />"
                             alt="<fmt:message key="label.storeItemImageAlt"/>"/>
                    </td>
                    <td class="table-text">
                        <c:if test="${lang == \"en\"}">${item.getNameEn()}</c:if>
                        <c:if test="${lang == \"ru\"}">${item.getNameRu()}</c:if>
                    </td>
                    <td class="table-price"><fmt:formatNumber value="${item.getPriceRub()}" type="number"
                                                              maxFractionDigits="3"/>₽
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btn-primary btn-sm btn-cart">
                            <i class="bi bi-cart-plus"></i> <fmt:message key="label.storeItemAddToCart"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/WEB-INF/parts/_cart-add-toasts.jsp" %>
</t:mainLayout>