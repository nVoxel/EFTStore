<c:if test="${cookie['lang'] == null || cookie['lang'].value == \"en\"}"><c:set var="lang" value="en"/></c:if>
<c:if test="${cookie['lang'].value == \"ru\"}"><c:set var="lang" value="ru"/></c:if>
<fmt:setBundle basename="messages"/>

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <nav class="navbar navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="<c:url value="/"/>">
                    <img src="<c:url value="/resources/svg/icon-navbar.svg"/>" alt="Logo" width="45" height="45"
                         class="d-inline-block align-text-top">
                    <a class="navbar-brand text-primary-light" href="<c:url value="/"/>">EFT Store</a>
                </a>
            </div>
        </nav>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI.endsWith("store.jsp") ? "active" : ""}"
                       href="<c:url value="/"/>"><fmt:message key="label.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI.endsWith("cart.jsp") ? "active" : ""}"
                       href="<c:url value="/cart"/>"><fmt:message key="label.cart"/></a>
                </li>
            </ul>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item centered-vertically dropdown dropstart">
                    <a class="nav-link" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <c:if test="${lang == \"en\"}"><fmt:message key="label.english"/></c:if>
                        <c:if test="${lang == \"ru\"}"><fmt:message key="label.russian"/></c:if>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark">
                        <li><h6 class="dropdown-header"><fmt:message key="label.language"/></h6></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <a class="dropdown-item lang-option <c:if test="${lang == \"ru\"}">disabled</c:if>"
                               data-lang="ru"><fmt:message key="label.russian"/></a>
                        </li>
                        <li>
                            <a class="dropdown-item lang-option <c:if test="${lang == \"en\"}">disabled</c:if>"
                               data-lang="en"><fmt:message key="label.english"/></a>
                        </li>
                    </ul>
                </li>

                <li class="nav-item centered-vertically dropdown dropstart">
                    <a class="nav-link" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 50 50">
                            <circle cx="25" cy="25" r="24" fill="#9a8866"></circle>
                            <path fill="none" d="M0 0h50v50H0z"></path>
                            <path d="M30 35.5c-.2-1.6-.2-2.7-.2-4.2.8-.4 2-2.8 2.3-4.9.6 0 1.5-.6 1.7-2.8.2-1.2-.4-1.8-.7-2C34 18.6 35.9 10 29.5 9 28.8 8 27 7.4 24.9 7.4c-8.8.2-10 6.7-8 14.1-.3.3-.9 1-.7 2.1.2 2.2 1.1 2.8 1.7 2.8.2 2 1.6 4.5 2.3 5v4.1c-1.3 3.4-7.8 3.7-11.6 7 4 3.9 10.3 6.7 17 6.7s14.5-5.3 15.8-6.7c-3.7-3.3-10.2-3.6-11.5-7z"></path>
                        </svg>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark">
                        <li><a class="dropdown-item" href="<c:url value="/profile"/>"><fmt:message
                                key="label.goToProfile"/></a></li>
                        <li><a class="dropdown-item" href="<c:url value="/profile/log-out"/>"><fmt:message
                                key="label.logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>