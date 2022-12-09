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
                            <%--                            <h3>Fate Stay Night: Unlimited Blade</h3>--%>
                            <%--                            <span>フェイト／ステイナイト, Feito／sutei naito</span>--%>
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
                                        <%--                                        <li><span>Type:</span> TV Series</li>--%>
                                        <%--                                        <li><span>Studios:</span> Lerche</li>--%>
                                        <%--                                        <li><span>Date aired:</span> Oct 02, 2019 to ?</li>--%>
                                        <%--                                        <li><span>Status:</span> Airing</li>--%>
                                        <li><span><fmt:message key="publication.span.categories"/>:</span>
                                            <c:forEach var="topic" items="${publication.topics}">
                                                ${topic.title}
                                            </c:forEach>
                                        </li>
                                        <li><span><fmt:message
                                                key="publication.span.price"/>:</span>${publication.price}</li>
                                    </ul>
                                </div>
                                <%--                                <div class="col-lg-6 col-md-6">--%>
                                <%--                                    <ul>--%>
                                <%--                                        <li><span>Scores:</span> 7.31 / 1,515</li>--%>
                                <%--                                        <li><span>Rating:</span> 8.5 / 161 times</li>--%>
                                <%--                                        <li><span>Duration:</span> 24 min/ep</li>--%>
                                <%--                                        <li><span>Quality:</span> HD</li>--%>
                                <%--                                        <li><span>Views:</span> 131,541</li>--%>
                                <%--                                    </ul>--%>
                                <%--                                </div>--%>
                            </div>
                        </div>
                        <div>

                            <c:choose>
                                <c:when test="${sessionScope.loggedPerson != null
                                    && sessionScope.loggedPerson.publications != null
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
                            <h5>Reviews</h5>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-1.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Chris Curry - <span>1 Hour ago</span></h6>
                                <p>whachikan Just noticed that someone categorized this as belonging to the genre
                                    "demons" LOL</p>
                            </div>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-2.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Lewis Mann - <span>5 Hour ago</span></h6>
                                <p>Finally it came out ages ago</p>
                            </div>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-3.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Louis Tyler - <span>20 Hour ago</span></h6>
                                <p>Where is the episode 15 ? Slow update! Tch</p>
                            </div>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-4.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Chris Curry - <span>1 Hour ago</span></h6>
                                <p>whachikan Just noticed that someone categorized this as belonging to the genre
                                    "demons" LOL</p>
                            </div>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-5.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Lewis Mann - <span>5 Hour ago</span></h6>
                                <p>Finally it came out ages ago</p>
                            </div>
                        </div>
                        <div class="anime__review__item">
                            <div class="anime__review__item__pic">
                                <img src="img/anime/review-6.jpg" alt="">
                            </div>
                            <div class="anime__review__item__text">
                                <h6>Louis Tyler - <span>20 Hour ago</span></h6>
                                <p>Where is the episode 15 ? Slow update! Tch</p>
                            </div>
                        </div>
                    </div>
                    <div class="anime__details__form">
                        <div class="section-title">
                            <h5>Your Comment</h5>
                        </div>
                        <form action="#">
                            <textarea placeholder="Your Comment"></textarea>
                            <button type="submit"><i class="fa fa-location-arrow"></i> Review</button>
                        </form>
                    </div>
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
