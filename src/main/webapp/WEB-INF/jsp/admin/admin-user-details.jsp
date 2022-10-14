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
<section class="user-dashboard">
    <p>User</p>
    <table>
        <tr>
            <th>Email</th>
            <th>Username</th>
            <th>Num of Subscriptions</th>
            <th>Status</th>
            <th>Change status</th>
        </tr>

        <tr>
            <td>
                ${person.email}
            </td>
            <td>
                ${person.username}
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.publications!=null}">
                        ${fn:length(user.publications)}
                    </c:when>
                    <c:otherwise>
                        No subscriptions
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:set var="status"
                       value="<%=((Person)pageContext.getRequest().getAttribute(\"person\")).getStatus().name()%>"/>
                ${status}
            </td>
            <td>
                <button href="/admin/user/status?id=${user.id}">Ban</button>
            </td>
        </tr>


    </table>


</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

</html>