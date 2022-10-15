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
<section class="user__dashboard">
    <div class="user__dashboard__table">
        <table>
            <tr>
                <th>Email</th>
                <th>Username</th>
                <th>Total subscriptions</th>
                <th>Status</th>
                <th>Ban/Unban</th>
            </tr>
            <c:forEach var="person" items="${personList}">
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/user/details?id=${person.id}">${person.email}</a>
                    </td>
                    <td>
                            ${person.username}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${person.publications!=null}">
                                ${fn:length(person.publications)}
                            </c:when>
                            <c:otherwise>
                                0
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>

                        ${person.status}
                    </td>
                    <td>
<%--                        <button type="button"  href="/admin/user/status?id=${person.id}">Ban</button>--%>
                        <form >
                        <button type="submit" formmethod="post" name="changeStatusId" value="${person.id}">Ban</button>
                        </form>
                    </td>
                </tr>


            </c:forEach>

        </table>
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