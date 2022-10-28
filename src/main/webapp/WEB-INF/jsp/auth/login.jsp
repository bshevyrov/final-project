<?xml version="1.0" encoding="UTF-8"?>

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

<!-- Normal Breadcrumb Begin -->
<section class="normal-breadcrumb set-bg" data-setbg="img/normal-breadcrumb.jpg">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <div class="normal__breadcrumb__text">
          <h2><fmt:message key="auth.message.page.name"/></h2>
          <p><fmt:message key="auth.message.greetings"/></p>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Normal Breadcrumb End -->

<!-- Login Section Begin -->
<section class="login spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-6">
        <div class="login__form">
          <h3><fmt:message key="auth.login"/></h3>
          <c:if test ="${true.equals(ban)}">
            <small><fmt:message key="auth.message.ban"/></small>
          </c:if>
          <form method="post">
            <div class="input__item">
              <input type="text" name="email" placeholder="Email address">
              <span class="icon_mail"></span>
              <c:if test ="${true.equals(emailWrong)}">
                <small><fmt:message key="auth.message.email"/></small>
              </c:if>
            </div>
            <div class="input__item">
              <input type="text" name="pass" placeholder="Password">
              <span class="icon_lock"></span>
              <c:if test ="${true.equals(passWrong)}">
                <small><fmt:message key="auth.message.pass"/></small>
              </c:if>
            </div>
            <fmt:message key="login.button.submit" var="buttonValue" />
            <button type="submit" class="site-btn" value="${buttonValue}"><fmt:message key="auth.button.submit"/></button>
          </form>
<%--          <a href="#" class="forget_pass">Forgot Your Password?</a>--%>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="login__register">
          <h3><fmt:message key="auth.message.register"/></h3>
          <a href="/signup" class="primary-btn"><fmt:message key="auth.button.register"/></a>
        </div>
      </div>
    </div>
<%--
    <div class="login__social">
      <div class="row d-flex justify-content-center">
        <div class="col-lg-6">
          <div class="login__social__links">
            <span>or</span>
            <ul>
              <li><a href="#" class="facebook"><i class="fa fa-facebook"></i> Sign in With
                Facebook</a></li>
              <li><a href="#" class="google"><i class="fa fa-google"></i> Sign in With Google</a></li>
              <li><a href="#" class="twitter"><i class="fa fa-twitter"></i> Sign in With Twitter</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
--%>
  </div>
</section>
<!-- Login Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

<!-- Footer Section End -->

<!-- Search model Begin -->
<div class="search-model">
  <div class="h-100 d-flex align-items-center justify-content-center">
    <div class="search-close-switch"><i class="icon_close"></i></div>
    <form class="search-model-form">
      <input type="text" id="search-input" placeholder="Search here.....">
    </form>
  </div>
</div>
<!-- Search model end -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>



</body>

