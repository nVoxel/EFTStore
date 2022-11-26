<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="label.changeNicknameTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="card content-margin text-bg-dark mb-3 mx-auto" style="max-width: 50rem;">
        <div class="card-header text-center">
            <h3 class="mb-0"><fmt:message key="label.changeNickname"/></h3>
        </div>
        <div class="card-body">
            <form id="changeNickname" method="post">
                <div class="form-group p-1 text-center">
                    <div class="row">
                        <h4><fmt:message key="label.currentNickname"/> ${user.getNickname()}</h4>
                    </div>

                    <div class="row d-flex justify-content-center mt25">
                        <label for="nickname">
                            <h6 class="text-grey"><i class="bi bi-person-fill"></i> <fmt:message key="label.nickname"/>
                            </h6>
                        </label>
                        <input placeholder="<fmt:message key="label.enterNewNickname"/>" required="required"
                               id="nickname" name="nickname" type="text" minlength="4"
                               class="form-control credentials-input">
                    </div>
                </div>
                <c:if test="${isIncorrectCredentials}">
                    <div class="row text-center mt25">
                        <div class="text-danger"><fmt:message key="label.nicknameIncorrect"/></div>
                    </div>
                </c:if>
                <c:if test="${isNicknameAlreadyExists}">
                    <div class="row text-center mt25">
                        <div class="text-danger"><fmt:message key="label.nicknameAlreadyTaken"/></div>
                    </div>
                </c:if>
                <div class="row">
                    <button value="submit" id="submit" class="btn btn-primary credentials-input mt25 mx-auto">
                        <span class="btn-text btn-primary"><fmt:message key="label.change"/></span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</t:mainLayout>