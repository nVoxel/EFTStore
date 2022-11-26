<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="label.changePasswordTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="card content-margin text-bg-dark mb-3 mx-auto" style="max-width: 50rem;">
        <div class="card-header text-center">
            <h3 class="mb-0"><fmt:message key="label.changePassword"/></h3>
        </div>
        <div class="card-body">
            <form id="changePassword" method="post">
                <div class="form-group p-1 text-center">
                    <div class="row d-flex justify-content-center mt15">
                        <label for="oldPassword">
                            <h6 class="text-grey"><i class="bi bi-lock-fill"></i> <fmt:message key="label.oldPassword"/>
                            </h6>
                        </label>
                        <input placeholder="<fmt:message key="label.enterOldPassword"/>" required="required"
                               id="oldPassword" name="oldPassword" type="password" minlength="8"
                               class="form-control credentials-input">
                    </div>

                    <div class="row d-flex justify-content-center mt25">
                        <label for="newPassword">
                            <h6 class="text-grey"><i class="bi bi-lock-fill"></i> <fmt:message key="label.newPassword"/>
                            </h6>
                        </label>
                        <input placeholder="<fmt:message key="label.enterNewPassword"/>" required="required"
                               id="newPassword" name="newPassword" type="password" minlength="8"
                               class="form-control credentials-input">
                    </div>
                </div>
                <c:if test="${isIncorrectCredentials}">
                    <div class="row text-center mt25">
                        <div class="text-danger"><fmt:message key="label.passwordIncorrect"/></div>
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