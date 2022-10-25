<!DOCTYPE html>
<html lang="zxx">

<head>
    <%--    <title>Anime | Template</title>--%>

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
<%--<!-- Breadcrumb Begin -->
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
<section class="publication__dashboard">
    <div class="publication__dashboard__table">
        <p>Subscriptions</p>
        <table>
            <tr>
                <th>Title</th>
                <th>Price</th>

            </tr>
            <c:forEach var="publication" items="${publications}">
                <tr>
                    <td>
                        <a href="/publication/details?id=${publication.id}">${publication.title}</a>
                    </td>
                    <td>
                            ${publication.price}
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>
</section>
<div class="pagination">
    <%@ include file="/WEB-INF/jspf/pagination.jspf" %>

</div>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->


<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

</html>