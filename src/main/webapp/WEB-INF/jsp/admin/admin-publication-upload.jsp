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
<%--<!-- Breadcrumb Begin -->
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
<!-- Breadcrumb End -->--%>
<!-- Upload Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <form class="form" id="upload" method="post">
            <div class="anime__details__form">
                <input name="id" value="${publication.id}" hidden>
                <div class="section-title">
                    <h5>Title</h5>
                    <br/>
                    <label>
                        <input placeholder="Title" name="title" value="${publication.title}" required>
                    </label>
                </div>
                <div class="section-description">
                    <h5>Description</h5>
                    <br/>
                    <label>
                        <textarea rows="10" cols="80" placeholder="Description"
                                  name="description" required>${publication.description}</textarea>
                    </label>
                </div>
                <div class="section-topic">
                    <h5>Topics</h5>
                    <br/>
                    <small id="error-message"></small>
                    <fieldset>
                        <div>
                            <c:forEach items="${topics}" var="topic">
                                <c:choose>
                                    <c:when test="${(publication != null) && (con:contains(publication.topics,topic))}">
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
                    <br/>
                    <h3>New topics separated by coma</h3>
                    <br/>
                    <label>
                        <input placeholder="New topics" name="newTopics" id="newTopics">
                    </label>
                </div>
                <div class="section-price">
                    <h5>Price</h5>
                    <br/>
                    <label for="price"></label>
                    <input type=number step="0.01" name="price" id="price" value="${publication.price}" required>
                </div>
                <div class="section-cover">
                    <h5>Cover</h5>
                </div>
            </div>
            <div class="input__item">
                <label for="cover">

                </label><input type="text" name="coverPath" id="cover" value="${publication.cover.path}" hidden>

            </div>
            <div
            <%-- style="margin: 16px; padding: 16px"--%>>
                <c:choose>
                    <c:when test="${publication!=null}">
                        <input
                            class="form-control form-control-lg"
                            id="selectAvatar"
                            type="file"
                    />
                    </c:when>
                    <c:otherwise>
                        <input
                            class="form-control form-control-lg"
                            id="selectAvatar"
                            type="file" required
                    />
                    </c:otherwise>

                </c:choose>

            </div>

            <div class="img-container">
                <div class="row">
                    <div class="col">
                        <h3>Image Preview:</h3>
                        <img class="img" id="avatar" src="${publication.cover.path}"/>
                    </div>
                    <div class="col">
                        <%--                    <h6>Base64 Output</h6>--%>
                        <%--                    <textarea id="textArea" rows="30" cols="50"></textarea>--%>
                    </div>
                </div>
                <br/>
                <%--            <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>--%>

                <button type="submit" onclick="return validateForm();" class="site-btn" id="check-btn">upload</button>
        </form>
    </div>
<%--    <script>
        function validateForm() {
            // var input = document.getElementById("upload").querySelectorAll("[required]");
            var emptyNewTopic = document.getElementById("newTopics").value;
            var checkBox = document.querySelectorAll('input[name=topics]:checked');
            if ((emptyNewTopic) || (checkBox.length !== 0)) {

                console.log(emptyNewTopic);
                return true;
            } else {
                document.getElementById("error-message").textContent = "Please check chosen topic or create new before submitting again";
                document.getElementById("error-message").scrollIntoView();
                console.log(emptyNewTopic)
                return false;
            }

        }
    </script>--%>
</section>
<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->


<!-- Js Plugins -->
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>


</body>
</html>
