<head>

    <title>Anime | Template</title>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>

</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section Begin -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<!-- Header End -->


<!-- Anime Section Begin -->
<section class="anime-details spad">
    <div class="container">
        <div class="anime__details__content">
            <div class="row">
                <div class="col-lg-3">
                    <div class="anime__details__pic set-bg" data-setbg="${publication.cover.path}">
                        <div class="comment"><i class="fa fa-comments"></i> 11</div>
                        <div class="view"><i class="fa fa-eye"></i> 9141</div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="anime__details__text">
                        <div class="anime__details__title">
                            <h3>${publication.title}</h3>
                        </div>
                        <div class="anime__details__rating">
                            <div class="rating">
                                <a href="#"><i class="fa fa-star"></i></a>
                                <a href="#"><i class="fa fa-star"></i></a>
                                <a href="#"><i class="fa fa-star"></i></a>
                                <a href="#"><i class="fa fa-star"></i></a>
                                <a href="#"><i class="fa fa-star-half-o"></i></a>
                            </div>
                            <span>1.029 Votes</span>
                        </div>
                        <p>${publication.description}</p>
                        <div class="anime__details__widget">
                            <div class="row">
                                <div class="col-lg-6 col-md-6">
                                    <ul>
                                        <li><span><fmt:message key="publication.span.categories"/>:</span>
                                            <c:forEach var="topic" items="${publication.topics}">
                                                ${topic.title}
                                            </c:forEach>
                                        </li>
                                        <li><span><fmt:message
                                                key="publication.span.price"/>:</span>${publication.price}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div>
                            <c:choose>
                                <c:when test="${not empty sessionScope.loggedPerson
                                    && not empty sessionScope.loggedPerson.publications
                                    && con:contains(sessionScope.loggedPerson.publications,publication)}">
                                    <c:if test="${con:contains(sessionScope.loggedPerson.publications,publication)}">
                                        <div class="anime__details__btn">
                                            <a disabled class="follow-btn"> <fmt:message
                                                    key="publication.button.subscribed"/></a>
                                        </div>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <div class="anime__details__btn">
                                        <a href="/user/subscribe?id=${publication.id}" class="follow-btn"><i
                                                class="fa fa-heart-o"></i> <fmt:message
                                                key="publication.button.subscribe"/></a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <div class="anime__details__review">
                        <div class="section-title">
                            <h5><fmt:message key="publication.text.comments"/></h5>
                        </div>
                        <c:if test="${not empty comments}">
                            <c:forEach items="${comments}" var="comment">
                                <div class="anime__review__item">
                                    <div class="anime__review__item__pic">
                                        <img src="${comment.avatarPath}" alt="">
                                    </div>
                                    <div class="anime__review__item__text">

                                        <h6>${comment.username} - <span><ocpsoft:prettytime
                                                date="${comment.createDate}" locale="${language}" /> </span></h6>
                                        <p>${comment.text}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>


                    <div class="product__pagination__button">
                        <div class="row">
                            <div class="column">
                                <c:choose>
                                    <c:when test="${currentPage==1}">
                                        <button type="submit" class="button_prev" disabled style="color: grey"><fmt:message key="category.btn.prev"/></button>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${url}" method="POST">
                                            <input type="hidden" name="currentPage" value="${currentPage-1}">
                                            <input type="hidden" name="pubId" value="${publication.id}">
                                            <button type="submit" class="button_prev"><fmt:message key="category.btn.prev"/></button>
                                        </form>

                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="column">

                                <c:choose>
                                    <c:when test="${currentPage==lastPage}">
                                        <button type="submit" class="button_next" disabled style="color: grey"><fmt:message key="category.btn.next"/></button>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${url}" method="POST">
                                            <input type="hidden" name="currentPage" value="${currentPage+1}">
                                            <input type="hidden" name="pubId" value="${publication.id}">
                                            <button type="submit" class="button_next"><fmt:message key="category.btn.next"/></button>
                                        </form>

                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty sessionScope.loggedPerson}">
                        <div class="anime__details__form">
                            <div class="section-title">
                                <h5><fmt:message key="publication.text.comment.new"/></h5>
                            </div>
                            <form class="form" method="post">
                                <input hidden name="pubId" value="${publication.id}"/>
                                <textarea name="comment" placeholder="Your Comment"></textarea>
                                <button type="submit"><i class="fa fa-location-arrow"></i> <fmt:message key="publication.button.comment.send"/></button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Anime Section End -->
<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->
<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>
</body>
