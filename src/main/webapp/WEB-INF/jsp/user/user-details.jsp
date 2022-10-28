<!DOCTYPE html>
<html lang="zxx">

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
            <div class="anime__details__form">
                <input name="id" value="${publication.id}" hidden>
                <div class="section-title">
                    <h5><fmt:message key="user.span.email"/></h5>
                    <br/>
                    <label>
                        <input placeholder="Email" name="email" value="${person.email}" required>
                    </label>
                </div>
                <div class="section-username">
                    <h5><fmt:message key="user.span.username"/></h5>
                    <br/>
                    <label>
                        <input placeholder="Username" name="username" value="${person.username}" required>
                    </label>
                </div>
                <div class="section-topic">
                    <h5><fmt:message key="user.span.funds"/></h5>
                    <br/>
                    <label>
                        <input placeholder="Funds" name="funds" value="${person.funds}" readonly>
                    </label>
                </div>
            </div>
            <div class="section-price">
                <h5><fmt:message key="user.span.deposit.funds"/></h5>
                <br/>
                <label for="addFunds"></label>
                <input type=number step="0.01" name="addFunds" id="addFunds">
            </div>
            <div class="section-cover">
                <h5><fmt:message key="user.span.avatar"/></h5>
            </div>

            <div class="input__item">
                <label for="currentAvatar">
                    <input type="currentAvatar" name="coverPath" id="currentAvatar" value="${person.avatar}" hidden>
                </label>
            </div>
            <div
            <%-- style="margin: 16px; padding: 16px"--%>>
                <c:choose>
                    <c:when test="${person.avatar}">
                        <input
                                class="form-control form-control-lg"
                                id="selectAvatar"
                                type="file" accept="image/png, image/jpeg"
                        />
                    </c:when>
                    <c:otherwise>
                        <input
                                class="form-control form-control-lg"
                                id="selectAvatar"
                                type="file" accept="image/png, image/jpeg" required
                        />
                    </c:otherwise>

                </c:choose>

            </div>

            <div class="img-container">
                <div class="row">
                    <div class="col">
                        <h3><fmt:message key="user.span.avatar.preview"/></h3>
                        <img class="img" id="avatar" src="${person.avatar}"/>
                    </div>
                    <div class="col">
                        <%--                    <h6>Base64 Output</h6>--%>
                        <%--                    <textarea id="textArea" rows="30" cols="50"></textarea>--%>
                    </div>
                </div>
                <br/>
                <%--            <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>--%>

                <button type="submit" <%--onclick="return validateForm();"--%> class="site-btn" id="check-btn"><fmt:message key="user.button.update"/>
                </button>
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

</html>