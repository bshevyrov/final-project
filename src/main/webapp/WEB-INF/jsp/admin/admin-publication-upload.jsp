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
                <label>

                    <input placeholder="Title" name="title" value="${publication.title}">

                </label>
                <div class="section-title">
                    <h5>Description</h5>
                </div>
                <label>
                    <textarea placeholder="Description" name="description">${publication.description}</textarea>

                </label>

                <div class="section-title">
                    <h5>Topics</h5>
                </div>

                <fieldset>
                    <legend>Choose your monster's features:</legend>

                    <div>
                        <c:forEach items="${topics}" var="topic">
                            <c:choose>

                                <c:when test="${con:contains(publication.topics,topic)}">
                                    <input type="checkbox" id="topic"
                                           name="topics" value="${topic.id}" checked>
                                    <label for="topic">${topic.title}</label>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="topic"
                                           name="topics" value="${topic.id}">
                                    <label for="topic">${topic.title}</label>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>

                    </div>
                </fieldset>


                <label>
                    <input placeholder="New topic separated by comas" name="newTopic"></input>
                </label>

                <div class="section-title">
                    <h5>Price</h5>
                </div>

                <label for="price"></label>
                <input type=number step="0.01" name="price" id="price" value="${publication.price}">

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
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>


</body>
</html>
