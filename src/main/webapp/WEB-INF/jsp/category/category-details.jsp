
<head>

    <title>Mangazine | Category Details</title>

    <%@include file="/WEB-INF/jspf/head.jspf" %>
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section Begin -->
<%@include file="/WEB-INF/jspf/header.jspf" %>

<!-- Header End -->



<!-- Product Section Begin -->
<section class="product-page spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="product__page__content">
                    <div class="product__page__title">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-6">
                                <div class="section-title">
                                    <h4>${topicName}</h4>
                                </div>
                            </div>
                            <div class="row">
                                <%--                                <div class="product__page__filter">--%>
                                <div class="product__sort__filter">
                                    <p><fmt:message key="category.order"/>:</p>
                                    <form action="${url}" id="sortForm" method="POST">
                                        <input type="hidden" id="1" name="currentSort" value="${currentSort}">
                                        <input type="hidden" id="2" name="currentSize" value="${currentSize}">
                                        <select name="sort" onchange="this.form.submit()">
                                            <option value="titleAsc"><fmt:message key="category.order.title.asc"/></option>
                                            <option value="titleDesc"><fmt:message key="category.order.title.desc"/></option>
                                            <option value="priceAsc"><fmt:message key="category.order.price.asc"/></option>
                                            <option value="priceDesc"><fmt:message key="category.order.price.desc"/></option>
                                        </select>
                                    </form>
                                </div>
                                <div class="product__size__filter">
                                    <p><fmt:message key="category.page.size"/>:</p>
                                    <form action="${url}" method="POST">
                                        <input type="hidden" id="3" name="currentSort" value="${currentSort}">
                                        <input type="hidden" id="4" name="currentSize" value="${currentSize}">
                                        <select name="pageSize" onchange="this.form.submit()">
                                            <option value="6">6</option>
                                            <option value="12">12</option>
                                            <option value="24">24</option>
                                        </select>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="row">

                        <div class="row">
                            <%--https://www.codejava.net/java-ee/jstl/jstl-core-tag-foreach--%>
                            <c:forEach var="publication" items="${publications}">
                                <c:set var="cover_img" value="${publication.cover}"/>
                                <div class="col-lg-4 col-md-6 col-sm-6">
                                    <div class="product__item">
                                        <div class="product__item__pic set-bg" data-setbg="${cover_img.path}">
                                            <div class="ep">18 / 18</div>
                                            <div class="comment"><i class="fa fa-bank"></i> ${publication.price}</div>
                                            <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                        </div>
                                        <div class="product__item__text">
                                            <ul>
                                                <c:forEach var="topic" items="${publication.topics}">
                                                    <li>${topic.title}</li>
                                                </c:forEach>
                                            </ul>
                                            <h5>
                                                <a href="/publication/details?id=${publication.id}">${publication.title}</a>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>


                    </div>
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
                                        <input type="hidden" name="currentSort" value="${currentSort}">
                                        <input type="hidden" name="currentSize" value="${currentSize}">
                                        <input type="hidden" name="currentPage" value="${currentPage-1}">

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
                                        <input type="hidden" name="currentSort" value="${currentSort}">
                                        <input type="hidden" name="currentSize" value="${currentSize}">
                                        <input type="hidden" name="currentPage" value="${currentPage+1}">

                                        <button type="submit" class="button_next"><fmt:message key="category.btn.next"/></button>
                                    </form>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>


