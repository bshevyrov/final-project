<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: bohdan
  Date: 12.09.22
  Time: 04:15
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<%--    <%@ page isELIgnored="false" %>--%>


<%--    <title>Anime | Template</title>--%>

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
<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="../../../anime/index.html"><i class="fa fa-home"></i> Home</a>
                    <a href="../open/categories.jsp">Categories</a>
                    <span>Romance</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->
<!-- Upload Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <form class="form" id="upload" method="post">
            <div class="anime__details__form">
                <div class="section-title">
                    <h5>Title</h5>
                </div>
                <input placeholder="Title" name="title"></input>
                <div class="section-title">
                    <h5>Description</h5>
                </div>
                <textarea placeholder="Description" name="description"></textarea>

                <div class="section-title">
                    <h5>Topics</h5>
                </div>

                <fieldset>
                    <legend>Choose your monster's features:</legend>

                    <div>
                        <c:forEach items="${topics}" var="topic">
                            <input type="checkbox" id="topic"
                                   name="topics" value="${topic.id}">
                            <label for="topic">${topic.title}</label>
                        </c:forEach>

                    </div>
                </fieldset>


                <input placeholder="New topic separated by comas" name="newTopic"></input>

                <div class="section-title">
                    <h5>Price</h5>
                </div>
                <input type=number name="price"></input>
                <div class="section-title">
                    <h5>Cover</h5>
                </div>
            </div>
            <div class="input__item">
                <input type="text" name="coverPath" id="cover" hidden>

            </div>
            <div style="margin: 16px; padding: 16px">
                <input
                        class="form-control form-control-lg"
                        id="selectAvatar"
                        type="file"
                />
            </div>


            <div style="margin: 24px">
                <h2>Upload Image</h2>
            </div>


            <div class="container">
                <div class="row">
                    <div class="col">
                        <h6>Image Preview:</h6>
                        <img class="img" id="avatar"/>
                    </div>
                    <div class="col">
                        <%--                    <h6>Base64 Output</h6>--%>
                        <%--                    <textarea id="textArea" rows="30" cols="50"></textarea>--%>
                    </div>
                </div>

                <%--            <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>--%>
                <button type="submit" class="site-btn">upload</button>
        </form>
    </div>
</section>
<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->


<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/player.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
<script src="/js/upload.js"></script>


</body>
</html>
