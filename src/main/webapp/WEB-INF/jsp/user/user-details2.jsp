
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
<%--        <form class="form" id="upload" method="post">--%>
            <div class="anime__details__form" id="left">
<%--                <input name="id" value="${publication.id}" hidden>--%>
                <div class="section-title">
                    <h5><fmt:message key="user.span.email"/></h5>
                    <br/>
                        <p>${person.email}"</p>
                </div>
                <div class="section-username">
                    <h5><fmt:message key="user.span.username"/></h5>
                    <br/>
                        <p> ${person.username}</p>
                </div>
                <div class="section-topic">
                    <h5><fmt:message key="user.span.funds"/></h5>
                    <br/>
                    <p>${person.funds}</p>
                </div>
            </div>
    <div class="anime__details__form" id="right">
        <div class="section-title">
            <h5><fmt:message key="user.details.name.first"/></h5>
            <br/>
            <p>${person.details.firstName}"</p>
        </div>
        <div class="section-title">
            <h5><fmt:message key="user.details.name.last"/></h5>
            <br/>
            <p>${person.details.lastName}"</p>
        </div>
        <div class="section-title">
            <h5><fmt:message key="user.details.address"/></h5>
            <br/>
            <p>${person.details.address}"</p>
        </div>
         <div class="section-title">
            <h5><fmt:message key="user.details.city"/></h5>
            <br/>
            <p>${person.details.city}"</p>
        </div>
         <div class="section-title">
            <h5><fmt:message key="user.details.country"/></h5>
            <br/>
            <p>${person.details.country}"</p>
        </div>
         <div class="section-title">
            <h5><fmt:message key="user.details.phone"/></h5>
            <br/>
            <p>${person.details.phone}"</p>
        </div>
         <div class="section-title">
            <h5><fmt:message key="user.details.postal.code"/></h5>
            <br/>
            <p>${person.details.postalCode}"</p>
        </div>

    </div>

            <div class="section-cover">
                <h5><fmt:message key="user.span.avatar"/></h5>
            </div>


            <div class="img-container">
                <img src="${person.avatar.path}" alt="">
                </div>
                <br/>

    </div>

</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

