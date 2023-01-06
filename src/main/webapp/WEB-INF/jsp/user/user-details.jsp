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
        <div class="anime__details__form" id="left">
            <div class="section-cover">
                <h5><fmt:message key="user.span.avatar"/></h5>
            </div>
            <div class="img-container">
                <img src="${person.avatar.path} alt="">
            </div>
            <%--                <input name="id" value="${publication.id} hidden>--%>
            <div class="section-title">
                <h5><fmt:message key="user.span.email"/></h5>
                <br/>
                <p>${person.email}</p>
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
                <p>${person.personAddressDTO.firstName}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.name.last"/></h5>
                <br/>
                <p>${person.personAddressDTO.lastName}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.address"/></h5>
                <br/>
                <p>${person.personAddressDTO.address}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.city"/></h5>
                <br/>
                <p>${person.personAddressDTO.city}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.country"/></h5>
                <br/>
                <p>${person.personAddressDTO.country}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.phone"/></h5>
                <br/>
                <p>${person.personAddressDTO.phone}</p>
            </div>
            <div class="section-title">
                <h5><fmt:message key="user.details.postal.code"/></h5>
                <br/>
                <p>${person.personAddressDTO.postalCode}</p>
            </div>
            <div>
                <form>
                    <button type="submit" formaction="/user/details/update"><fmt:message
                            key="user.button.update.details"/></button>
                </form>
            </div>
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

