<!DOCTYPE html>
<html lang="zxx">

<head>
<%--    <title>Anime | Template</title>--%>

    <%@include file="/WEB-INF/jspf/head.jspf"%>
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
<section class="user-dashboard">
    <p>Users</p>
    <table>
        <tr>
            <th>Email</th>
            <th>Username</th>
        </tr>
        <c:forEach var="user" items="${personList}">
            <tr>
                <td>
                  <a href="/admin/user?id=${user.id}">${user.email}</a>
                </td>
                <td>
                    ${user.username}
                </td>
            </tr>

        </c:forEach>
        <tr>
            <td>John</td>
            <td>Doe</td>
        </tr>
        <tr>
            <td>Jane</td>
            <td>Doe</td>
        </tr>
    </table>


</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->



<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf"%>

</body>

</html>