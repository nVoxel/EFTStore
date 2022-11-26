<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="<c:url value="/resources/js/context-path.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/cart-actions.js" />" type="text/javascript"></script>

<c:if test="${cookie['lang'] == null || cookie['lang'].value == \"en\"}"><c:set var="lang" value="en"/></c:if>
<c:if test="${cookie['lang'].value == \"ru\"}"><c:set var="lang" value="ru"/></c:if>

<c:set var="title">
    <c:if test="${lang == \"en\"}">${item.getNameEn()}</c:if>
    <c:if test="${lang == \"ru\"}">${item.getNameRu()}</c:if>
</c:set>

<fmt:setBundle basename="messages"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="container-fluid bg-default content-padding"
         style="background-image: url(<c:url value="/resources/images/bg-store.jpg"/>)">
        <div class="card text-bg-dark mb-3 pb2 mx-auto" style="max-width: 70rem;">
            <div class="card-header text-center">
                <h3>${title}</h3>
            </div>
            <div class="card-body ph3">
                <div class="col">
                    <div class="row">
                        <div class="col text-center">
                            <img class="item-img-large"
                                 src="<c:url value="/resources/images/items/${item.getImageNameDet()}" />"
                                 alt="<fmt:message key="label.storeItemImageAlt"/>"/>
                        </div>
                    </div>
                    <div class="row text-center mt15">
                        <div class="col text-center">
                            <h5 class="fs12">
                                <c:if test="${lang == \"en\"}">${item.getDescriptionEn()}</c:if>
                                <c:if test="${lang == \"ru\"}">${item.getDescriptionRu()}</c:if>
                            </h5>
                        </div>
                    </div>
                    <div class="row text-center mt15">
                        <a role="button" href="${item.getWikiLink()}" class="btn mx-auto btn-cart-det btn-primary"><i
                                class="bi bi-info-circle-fill"></i> <fmt:message key="label.itemDetailsOpenWiki"/></a>
                    </div>
                    <div class="row text-center mt15">
                        <h4><fmt:message key="label.itemDetailsCurrentPrice"/> <fmt:formatNumber
                                value="${item.getPriceRub()}" type="number" maxFractionDigits="3"/>₽</h4>
                    </div>
                    <div class="row text-center mt15">
                        <button type="button" class="btn mx-auto btn-cart-det btn-primary"
                                onclick="addToCart(${item.getId()})">
                            <i class="bi bi-cart-plus"></i> <fmt:message key="label.storeItemAddToCart"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/parts/_cart-add-toasts.jsp" %>
</t:mainLayout>