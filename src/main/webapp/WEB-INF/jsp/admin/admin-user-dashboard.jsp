
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
<section class="user__dashboard">
    <div class="user__dashboard__table">
        <table>
            <tr>
                <th><fmt:message key="admin.user.dashboard.email"/></th>
                <th><fmt:message key="admin.user.dashboard.username"/></th>
                <th><fmt:message key="admin.user.dashboard.total.subscriptions"/></th>
                <th><fmt:message key="admin.user.dashboard.status"/></th>
                <th><fmt:message key="admin.user.dashboard.status.change"/></th>
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
                        <form >
                        <button type="submit" formmethod="post" name="changeStatusId" value="${person.id}"><fmt:message key="admin.user.dashboard.button.ban"/></button>
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

