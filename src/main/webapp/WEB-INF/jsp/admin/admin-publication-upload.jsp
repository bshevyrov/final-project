<head>

    <%--    https://www.youtube.com/watch?v=LZ2beyIysYw&t=189s--%>
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

<!-- Upload Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <form class="form" id="upload" method="post">
            <div class="anime__details__form">
                <input name="id" value="${publication.id}" hidden>
                <div class="section-title">
                    <h5><fmt:message key="admin.publication.upload.title"/></h5>
                    <label>
                        <input placeholder="Title" name="title" value="${publication.title}" required>
                    </label>
                    <div class="error_small">
                        <c:if test="${con:contains(errorFields,\"title\")}">
                            <small> <fmt:message key="admin.publication.upload.error.title"/></small>
                        </c:if>
                    </div>
                    <div class="section-description">
                        <h5><fmt:message key="admin.publication.upload.description"/></h5>

                        <label>
                        <textarea rows="10" cols="80" placeholder="Description"
                                  name="description" required>${publication.description}</textarea>
                        </label>
                        <div class="error_small">
                            <c:if test="${con:contains(errorFields,\"description\")}">
                                <small> <fmt:message key="admin.publication.upload.error.description"/></small>
                            </c:if>
                        </div>
                    </div>

                    <div class="section-topic">
                        <h5><fmt:message key="admin.publication.upload.topics"/></h5>
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
                        <h3><fmt:message key="admin.publication.upload.topics.coma"/></h3>
                        <label>
                            <input placeholder="New topics" name="newTopics" id="newTopics">
                        </label>
                        <div class="error_small">
                            <c:if test="${con:contains(errorFields,\"newTopics\")}">
                                <small> <fmt:message key="admin.publication.upload.error.newTitle"/></small>
                            </c:if>
                        </div>
                    </div>

                    <div class="section-price">
                        <h5><fmt:message key="admin.publication.upload.price"/></h5>
                        <label for="price">
                            <input type=number step="0.01" name="price" id="price" value="${publication.price}"
                                   required>
                        </label>
                        <div class="error_small">
                            <c:if test="${con:contains(errorFields,\"price\")}">
                                <small> <fmt:message key="admin.publication.upload.error.price"/></small>
                            </c:if>
                        </div>
                    </div>
                    <div class="section-cover">
                        <h5><fmt:message key="admin.publication.upload.cover"/></h5>
                    </div>
                </div>
                <div class="input__item">
                    <label for="cover">
                    </label><input type="text" name="coverPath" id="cover" value="${publication.cover.path}" hidden>
                </div>
                <div>
                    <c:choose>
                        <c:when test="${publication!=null}">
                            <input
                                    class="form-control form-control-lg"
                                    id="selectAvatar"
                                    type="file" accept="image/png, image/jpeg"
                            />
                        </c:when>
                        <c:otherwise>
                            <input
                                    class="form-control form-control-lg"
                                    id="selectAvatar"
                                    type="file" accept="image/png, image/jpeg" required
                            />
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="img-container">
                    <div class="row">
                        <div class="col">
                            <h5><fmt:message key="admin.publication.upload.image.preview"/>:</h5>
                            <img class="img" id="avatar" src="${publication.cover.path}"/>
                        </div>
                        <div class="col">
                        </div>
                    </div>


                    <button type="submit" onclick="return validateForm();" class="site-btn" id="check-btn"><fmt:message
                            key="admin.publication.upload.btn"/></button>
                </div>
            </div>

        </form>
    </div>

</section>
<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->


<!-- Js Plugins -->
<%@ include file="/WEB-INF/jspf/scripts.jspf" %>


</body>
