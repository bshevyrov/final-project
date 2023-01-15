<head>
    <title>Anime | Template</title>

    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section Begin -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Header End -->

<!-- Product Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <form class="form" id="upload" method="post">
            <div class="anime__details__form" id="left">
                <div class="section-cover">
                    <h5><fmt:message key="user.span.avatar"/></h5>
                </div>
                <div class="img-container">
                    <img src="${person.avatar.path}" id="avatar"/>
                </div>

                <div class="input__item">
                    <label for="coverPath">
                    </label><input type="text" name="avatarPath" id="coverPath" value="${person.avatar.path}" hidden>
                </div>
                <div>
                    <input
                            class="form-control form-control-lg"
                            id="selectAvatar"
                            type="file" accept="image/png, image/jpeg"
                    />
                </div>

                <div class="section-title">
                    <h5><fmt:message key="user.span.email"/></h5>

                    <input placeholder="<fmt:message key="user.span.email"/>" name="email" value="${person.email}"
                           required>
                    <c:if test="${true.equals(emailExist)}">
                        <small><fmt:message key="user.details.email.exist"/></small>
                    </c:if>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"email\")}">
                            <small> <fmt:message key="user.details.error.email"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-username">
                    <h5><fmt:message key="user.span.username"/></h5>
                    <input placeholder="<fmt:message key="user.span.username"/>" name="username"
                           value="${person.username}" required>
                    <c:if test="${true.equals(usernameExist)}">
                        <small><fmt:message key="user.details.username.exist"/></small>
                    </c:if>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"username\")}">
                            <small> <fmt:message key="user.details.error.username"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-price">
                    <h5><fmt:message key="user.span.deposit.funds"/></h5>
                    <br/>
                    <label for="addFunds"></label>
                    <input type=number step="0.01" name="addFunds" id="addFunds">
                </div>
            </div>
            <div class="anime__details__form" id="right">
                <div class="section-title">
                    <h5><fmt:message key="user.details.name.first"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.name.first"/>" name="firstName"
                           value="${person.personAddressDTO.firstName}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"firstName\")}">
                            <small> <fmt:message key="user.details.error.firstName"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.name.last"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.name.last"/>" name="lastName"
                           value="${person.personAddressDTO.lastName}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"lastName\")}">
                            <small> <fmt:message key="user.details.error.lastName"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.address"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.address"/>" name="address"
                           value="${person.personAddressDTO.address}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"address\")}">
                            <small> <fmt:message key="user.details.error.address"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.city"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.city"/>" name="city"
                           value="${person.personAddressDTO.city}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"city\")}">
                            <small> <fmt:message key="user.details.error.city"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.country"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.country"/>" name="country"
                           value="${person.personAddressDTO.country}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"country\")}">
                            <small> <fmt:message key="user.details.error.country"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.phone"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.phone"/>" name="phone" type="number"
                           value="${person.personAddressDTO.phone}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"phone\")}">
                            <small> <fmt:message key="user.details.error.phone"/></small>
                        </c:if>
                    </div>
                </div>
                <div class="section-title">
                    <h5><fmt:message key="user.details.postal.code"/></h5>
                    <br/>
                    <input placeholder="<fmt:message key="user.details.postal.code"/>" name="postalCode" type="number"
                           value="${person.personAddressDTO.postalCode}" required>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"postalCode\")}">
                            <small> <fmt:message key="user.details.error.postalCode"/></small>
                        </c:if>
                    </div>
                </div>
                <div>
                    <button type="submit" <%--onclick="return validateForm();"--%> class="site-btn" id="check-btn">
                        <fmt:message
                                key="user.button.update"/></button>
                </div>
            </div>
        </form>
    </div>

</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

