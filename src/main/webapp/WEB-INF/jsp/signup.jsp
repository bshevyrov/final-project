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
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Anime | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/plyr.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">


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
                    <form class="form" <%--action="#"--%> <%--method="post" --%>id="signup" <%--onsubmit="return validateForm()--%>>
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
                            <input type="password" placeholder="Confirm Password" name="confirm-password" id="confirm-password">
                            <span class="icon_lock"></span>
                            <small></small>

                        </div>
                        <button  type="submit" class="site-btn">Login Now</button>
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
<script src="js/signup-validator.js"></script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/player.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

</body>

</html>
