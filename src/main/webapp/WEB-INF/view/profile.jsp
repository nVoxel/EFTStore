<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="label.profileTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="container-fluid text-center content-padding h600 bg-default"
         style="background-image: url(<c:url value="/resources/images/bg-store.jpg"/>)">
        <div class="row align-items-center">
            <div class="col">
                <c:if test="${user.getAvatarId().isPresent()}">
                    <img class="rounded-circle avatar-img"
                         src="<c:url value="/files/avatars/${user.getAvatarId().get()}"/>"
                         alt="<fmt:message key="label.avatar"/>">
                </c:if>
                <c:if test="${!user.getAvatarId().isPresent()}">
                    <img class="rounded-circle avatar-img" src="<c:url value="/resources/images/avatar_default.jpg"/>"
                         alt="<fmt:message key="label.avatar"/>">
                </c:if>
            </div>
            <div class="col text-start">
                <h1><i class="bi bi-person-fill"></i> ${user.getNickname()}</h1>
                <h3><i class="bi bi-envelope-fill"></i> ${user.getEmail()}</h3>
            </div>
            <div class="col">
                <div class="btn-group-vertical" role="group" aria-label="Vertical button group">
                    <a role="button" href="<c:url value="/profile/change-email"/>" class="btn btn-dark "><i
                            class="bi bi-envelope-fill"></i> <fmt:message key="label.profileChangeEmail"/></a>
                    <a role="button" href="<c:url value="/profile/change-nickname"/>" class="btn btn-dark"><i
                            class="bi bi-person-fill"></i> <fmt:message key="label.profileChangeNickname"/></a>
                    <a role="button" href="<c:url value="/profile/change-password"/>" class="btn btn-dark"><i
                            class="bi bi-lock-fill"></i> <fmt:message key="label.profileChangePassword"/></a>
                    <a role="button" href="<c:url value="/profile/avatar/upload"/>" class="btn btn-dark"><i
                            class="bi bi-person-square"></i> <fmt:message key="label.profileUploadAvatar"/></a>
                    <a role="button" href="<c:url value="/profile/avatar/delete"/>"
                       class="btn <c:if test="${!user.getAvatarId().isPresent()}">disabled</c:if> btn-dark"><i
                            class="bi bi-trash3-fill"></i> <fmt:message key="label.profileDeleteAvatar"/></a>
                </div>
            </div>
        </div>
    </div>
</t:mainLayout>