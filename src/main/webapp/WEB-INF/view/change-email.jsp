<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="label.changeEmailTitle" var="title"/>

<t:mainLayout title="EFT Store — ${title}">
    <div class="card content-margin text-bg-dark mb-3 mx-auto" style="max-width: 50rem;">
        <div class="card-header text-center">
            <h3 class="mb-0"><fmt:message key="label.changeEmail"/></h3>
        </div>
        <div class="card-body">
            <form id="changeEmail" method="post">
                <div class="form-group p-1 text-center">
                    <div class="row">
                        <h4><fmt:message key="label.currentEmail"/> ${user.getEmail()}</h4>
                    </div>

                    <div class="row d-flex justify-content-center mt25">
                        <label for="email">
                            <h6 class="text-grey"><i class="bi bi-envelope-fill"></i> <fmt:message key="label.email"/>
                            </h6>
                        </label>
                        <input placeholder="<fmt:message key="label.enterNewEmail" />" required="required" id="email"
                               name="email" type="email" minlength="4" class="form-control credentials-input">
                    </div>
                </div>
                <c:if test="${isIncorrectCredentials}">
                    <div class="row text-center mt25">
                        <div class="text-danger"><fmt:message key="label.emailIncorrect"/></div>
                    </div>
                </c:if>
                <c:if test="${isEmailAlreadyExists}">
                    <div class="row text-center mt25">
                        <div class="text-danger"><fmt:message key="label.emailAlreadyTaken"/></div>
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