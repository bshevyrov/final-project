
<head>
    <meta charset="UTF-8">

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

<!-- Normal Breadcrumb Begin -->
<section class="normal-breadcrumb set-bg" data-setbg="img/normal-breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="normal__breadcrumb__text">
                    <h2><fmt:message key="signup.page.name"/></h2>
                    <p><fmt:message key="auth.message.greetings"/></p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Normal Breadcrumb End -->

<!-- Signup Section Begin -->
<section class="signup spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login__form">
                    <h3><fmt:message key="signup.page.name"/></h3>
                    <form class="form"
                          method="post" id="signup" >
                        <div class="input__item">
                            <span class="icon_profile"></span>
                            <input type="text" placeholder="Username" name="username" id="username">
                            <c:if test="${true.equals(usernameBusy)}">
                                <small><fmt:message key="signup.message.username"/></small>
                            </c:if>
                            <small></small>

                        </div>
                        <div class="input__item">
                            <span class="icon_mail"></span>
                            <input type="text" placeholder="Email address" name="email" id="email">
                            <c:if test="${true.equals(emailBusy)}">
                                <small><fmt:message key="signup.message.email"/></small>
                            </c:if>
                            <small></small>

                        </div>
                        <div class="input__item">
                            <input type="password" placeholder="Password" name="password" id="password">
                            <span class="icon_lock"></span>
                            <small></small>

                        </div>
                        <div class="input__item">
                            <input type="password" placeholder="Confirm Password" name="confirm-password"
                                   id="confirm-password">
                            <span class="icon_lock"></span>
                            <small></small>

                        </div>
                        <button type="submit" class="site-btn"><fmt:message key="signup.button.signup"/></button>
                    </form>
                    <h5><fmt:message key="signup.message.signup"/> <a href="/login"><fmt:message key="signup.button.login"/></a></h5>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- Signup Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->


<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

