
<head>
        <title>Mangazine | Admin publications dashboard</title>

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
<section class="publication__dashboard">
    <div class="publication__dashboard__table">
        <p><fmt:message key="admin.publication.dashboard.publications"/></p>
        <table>
            <tr>
                <th><fmt:message key="admin.publication.dashboard.title"/></th>
                <th><fmt:message key="admin.publication.dashboard.price"/></th>
                <th><fmt:message key="admin.publication.dashboard.edit"/></th>
                <th><fmt:message key="admin.publication.dashboard.delete"/></th>
            </tr>
            <c:forEach var="publication" items="${publicationList}">
                <tr>
                    <td>
                        <a href="/publication/details?id=${publication.id}">${publication.title}</a>
                    </td>
                    <td>
                            ${publication.price}
                    </td>
                    <td>
                        <a href="/admin/publication/upload?id=${publication.id}">
                            <button><fmt:message key="admin.publication.dashboard.edit"/></button>
                        </a>
                    </td>
                    <td>
                        <form>
                            <button type="submit" formmethod="post" name="deleteId" value="${publication.id}"
                                    href="/admin/publication/dashboard"><fmt:message key="admin.publication.dashboard.delete"/>
                            </button>
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

