
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
                </tr>


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

