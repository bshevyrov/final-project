<%--
  Created by IntelliJ IDEA.
  User: bohdan
  Date: 09.09.22
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zxx">

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
                    <h2>Sign Up</h2>
                    <p>Welcome to the official Anime blog.</p>
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
                    <h3>Sign Up</h3>
                    <form class="form" <%--action="#"--%>
                          <%--method="post" --%>id="signup" <%--onsubmit="return validateForm()--%>>
                        <div class="input__item">
                            <span class="icon_profile"></span>
                            <input type="text" placeholder="Username" name="username" id="username">
                            <small></small>

                        </div>
                        <div class="input__item">
                            <span class="icon_mail"></span>
                            <input type="text" placeholder="Email address" name="email" id="email">

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
                        <button type="submit" class="site-btn">Login Now</button>
                        <%--                        https://www.javascripttutorial.net/javascript-dom/javascript-form-validation/--%>
                    </form>
                    <h5>Already have an account? <a href="#">Log In!</a></h5>
                </div>
            </div>
            <%--
                        <div class="col-lg-6">
                            <div class="login__social__links">
                                <h3>Login With:</h3>
                                <ul>
                                    <li><a href="#" class="facebook"><i class="fa fa-facebook"></i> Sign in With Facebook</a>
                                    </li>
                                    <li><a href="#" class="google"><i class="fa fa-google"></i> Sign in With Google</a></li>
                                    <li><a href="#" class="twitter"><i class="fa fa-twitter"></i> Sign in With Twitter</a></li>
                                </ul>
                            </div>
                        </div>
            --%>
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

</html>
