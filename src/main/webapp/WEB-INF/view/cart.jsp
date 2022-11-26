<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="<c:url value="/resources/js/cart.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/row-details.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/context-path.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/cart-actions.js" />" type="text/javascript"></script>

<c:if test="${cookie['lang'] == null || cookie['lang'].value == \"en\"}"><c:set var="lang" value="en"/></c:if>
<c:if test="${cookie['lang'].value == \"ru\"}"><c:set var="lang" value="ru"/></c:if>
<fmt:setBundle basename="messages"/>

<fmt:message key="label.cartTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="container-fluid bg-default content-padding" id="cart-container"
         style="background-image: url(<c:url value="/resources/images/bg-store.jpg"/>)">
        <div class="container-fluid">
            <div class="row">
                <div class="col my-auto text-start">
                    <h1><fmt:message key="label.cartTotalCost"/> <span id="total-cost"></span></h1>
                </div>
                <div class="col-md-auto my-auto text-center">
                    <div class="btn-group-vertical" role="group" aria-label="Vertical button group">
                        <a role="button" href="<c:url value="/checkout"/>" class="btn btn-lg btn-dark"
                           id="btn-checkout"><i class="bi bi-cart-check-fill"></i> <fmt:message
                                key="label.cartCheckout"/></a>
                        <button type="button" onclick="clearCartButtonClick()" class="btn btn-lg btn-dark"
                                id="btn-clear-cart"><i class="bi bi-cart-x-fill"></i> <fmt:message
                                key="label.cartClear"/></button>
                    </div>
                </div>

                <h2 id="cart-empty-text" class="invisible"><fmt:message key="label.cartIsEmpty"/></h2>
            </div>

            <c:if test="${not empty cartItems}">
                <table class="table table-bordered table-dark table-hover table-hover-cursor text-primary-light mt15"
                       id="cart-table">
                    <tbody>
                    <c:forEach var="cartItem" items="${cartItems}" varStatus="foreach">
                        <tr class="item-row" data-store-item-id="${storeItems[foreach.index].getId()}"
                            data-cart-item-id="${cartItems[foreach.index].getId()}">
                            <td class="text-center">
                                <img class="item-img"
                                     src="<c:url value="/resources/images/items/${storeItems[foreach.index].getImageName()}" />"
                                     alt="<fmt:message key="label.storeItemImageAlt"/>"/>
                            </td>
                            <td class="table-text">
                                <c:if test="${lang == \"en\"}">${storeItems[foreach.index].getNameEn()}</c:if>
                                <c:if test="${lang == \"ru\"}">${storeItems[foreach.index].getNameRu()}</c:if>
                            </td>
                            <td class="table-price"><fmt:formatNumber
                                    value="${storeItems.get(foreach.index).getPriceRub()}" type="number"
                                    maxFractionDigits="3"/>₽
                            </td>
                            <td class="text-center">
                                <button type="button" class="btn btn-primary btn-sm btn-cart">
                                    <i class="bi bi-cart-dash"></i> <fmt:message key="label.cartItemRemoveFromCart"/>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

            <%@ include file="/WEB-INF/parts/_cart-remove-toasts.jsp" %>
</t:mainLayout>