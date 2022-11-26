<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="label.uploadAvatarTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="card content-margin text-bg-dark mb-3 mx-auto" style="max-width: 50rem;">
        <div class="card-header text-center">
            <h3><fmt:message key="label.uploadAvatar"/></h3>
        </div>
        <div class="card-body text-center">
            <h4><fmt:message key="label.hereIsYourCurrentAvatar"/></h4>
            <c:if test="${user.getAvatarId().isPresent()}">
                <img class="rounded-circle avatar-img mx-auto mt15"
                     src="<c:url value="/files/avatars/${user.getAvatarId().get()}"/>"
                     alt="<fmt:message key="label.avatar" />">
            </c:if>
            <c:if test="${!user.getAvatarId().isPresent()}">
                <img class="rounded-circle avatar-img mx-auto mt15"
                     src="<c:url value="/resources/images/avatar_default.jpg"/>"
                     alt="<fmt:message key="label.avatar" />">
            </c:if>

            <form id="uploadAvatar" method="post" enctype="multipart/form-data">
                <label for="avatar" class="mt25">
                    <h6 class="text-grey"><i class="bi bi-file-earmark-arrow-up-fill"></i> <fmt:message
                            key="label.selectAvatarImage"/></h6>
                </label>
                <input class="form-control mx-auto credentials-input" id="avatar" type="file" name="avatar"
                       required="required" accept=".jpg, .jpeg, .png">

                <div class="row text-center mt15">
                    <div class="text-warning"><fmt:message key="label.selectAvatarNote"/></div>
                </div>

                <button value="submit" id="submit" class="btn btn-primary credentials-input mt15 mx-auto">
                    <span class="btn-text btn-primary"><fmt:message key="label.upload"/></span>
                </button>

                <c:if test="${isIncorrectFileSize}">
                    <div class="row text-center mt15">
                        <div class="text-danger"><fmt:message key="label.fileSize"/></div>
                    </div>
                </c:if>

                <c:if test="${isIncorrectContentType}">
                    <div class="row text-center mt15">
                        <div class="text-danger"><fmt:message key="label.fileFormat"/></div>
                    </div>
                </c:if>

                <c:if test="${isInvalidFile}">
                    <div class="row text-center mt15">
                        <div class="text-danger"><fmt:message key="label.fileDamaged"/></div>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</t:mainLayout>