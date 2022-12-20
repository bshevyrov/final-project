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
    <div class="container" id="admin_container">
        <div class="anime__details__form" id="left">

            <div class="section-topic">
                <h5><fmt:message key="user.span.username"/></h5>
                <br/>
                <p>${person.username}</p>
            </div>

            <div class="section-topic">
                <h5><fmt:message key="user.span.funds"/></h5>
                <br/>
                <p>${person.funds}</p>
            </div>
            <div class="section-topic">
                <h5><fmt:message key="user.span.createDate"/></h5>
                <br/>
                <p>${person.createDate}</p>
            </div>

            <%--            <table>--%>
            <%--                <tr>--%>
            <%--                    <td>Email</td>--%>
            <%--                    <td>${person.email}</td>--%>
            <%--                </tr>--%>

            <%--                <tr>--%>
            <%--                    <td>Username</td>--%>
            <%--                    <td>${person.username}</td>--%>
            <%--                </tr>--%>

            <%--                <tr>--%>
            <%--                    <td>Status</td>--%>
            <%--                    <td>${person.status}</td>--%>
            <%--                </tr>--%>
            <%--            </table>--%>
        </div>
        <div class="anime__details__form" id="right">
            <div class="section-topic">
                <h5><fmt:message key="user.details.city"/></h5>
                <br/>
                <p>${person.personAddressDTO.city}</p>
            </div>
            <div class="section-topic">
                <h5><fmt:message key="user.details.country"/></h5>
                <br/>
                <p>${person.personAddressDTO.country}</p>
            </div>
            <div class="section-topic">
                <h5><fmt:message key="user.details.address"/></h5>
                <br/>
                <p>${person.personAddressDTO.address}</p>
            </div>
            <div class="section-topic">
                <h5><fmt:message key="user.details.postal.code"/></h5>
                <br/>
                <p>${person.personAddressDTO.postalCode}</p>
            </div>
            <div class="section-topic">
                <h5><fmt:message key="user.details.phone"/></h5>
                <br/>
                <p>${person.personAddressDTO.phone}</p>
            </div>
        </div>
        <div class="anime__details__form" id="right2">
            <table>
                <tr>
                    <%--                    <td>subscriptions</td>--%>
                    <%--                    <td>--%>
                    <table>
                        <div class="section-topic">

                            <h5><fmt:message key="header.user.subscriptions"/></h5>
                        </div>
                        <c:forEach items="${person.publications}" var="publication">
                            <tr>
                                <td>
                                    <a href="/publication/details?id=${publication.id}">${publication.title}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    </td>
                </tr>


                <%--</tr>

                <th>Num of Subscriptions</th>

                <th>Change status</th>


                <tr>
                    <td>

                    <td>
                        ${person.username}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${person.publications!=null}">
                                ${fn:length(person.publications)}
                            </c:when>
                            <c:otherwise>
                                No subscriptions
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                    </td>
                    <td>
                        <form>
                            <button type="submit" formmethod="post" name="changeStatusId" value="${person.id}">Ban</button>
                        </form>
                    </td>
                </tr>--%>

            </table>
        </div>
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