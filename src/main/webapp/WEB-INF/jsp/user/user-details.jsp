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
<%--
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="../../../anime/index.html"><i class="fa fa-home"></i> Home</a>
                    <a href="./categories.html">Categories</a>
                    <span>${topicName}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->--%>

<!-- Product Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <form class="form" id="upload" method="post">
            <div class="anime__details__form">
                <input name="id" value="${publication.id}" hidden>
                <div class="section-title">
                    <h5>Email</h5>
                    <br/>
                    <label>
                        <input placeholder="Email" name="email" value="${person.email}" required>
                    </label>
                </div>
                <div class="section-username">
                    <h5>Username</h5>
                    <br/>
                    <label>
                        <input placeholder="Username" name="username" value="${person.username}" required>
                    </label>
                </div>
                <div class="section-topic">
                    <h5>Funds</h5>
                    <br/>
                    <label>
                        <input placeholder="Funds" name="funds" value="${person.funds}" readonly>
                    </label>
                </div>
            </div>
            <div class="section-price">
                <h5>Deposit Funds</h5>
                <br/>
                <label for="price"></label>
                <input type=number step="0.01" name="funds" id="price">
            </div>
            <div class="section-cover">
                <h5>Avatar</h5>
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
                                type="file"
                        />
                    </c:when>
                    <c:otherwise>
                        <input
                                class="form-control form-control-lg"
                                id="selectAvatar"
                                type="file" required
                        />
                    </c:otherwise>

                </c:choose>

            </div>

            <div class="img-container">
                <div class="row">
                    <div class="col">
                        <h3>Image Preview:</h3>
                        <img class="img" id="avatar" src="${person.avatar}"/>
                    </div>
                    <div class="col">
                        <%--                    <h6>Base64 Output</h6>--%>
                        <%--                    <textarea id="textArea" rows="30" cols="50"></textarea>--%>
                    </div>
                </div>
                <br/>
                <%--            <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>--%>

                <button type="submit" <%--onclick="return validateForm();"--%> class="site-btn" id="check-btn">upload
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