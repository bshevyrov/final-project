
<!DOCTYPE html>
<html lang="zxx">

<head>

    <title>Anime | MAIN PAGE</title>
    <%@include file="/WEB-INF/jspf/head.jspf"%>

</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Header Section Begin -->
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%--
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-2">
                <div class="header__logo">
                    <a href="./index.html">
                        <img src="img/logo.png.orig" alt="">
                    </a>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="header__nav">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li class="active"><a href="./index.html">Homepage</a></li>
                            <li><a href="./category-details.jsp">Categories <span class="arrow_carrot-down"></span></a>
                                <ul class="dropdown">
                                    <li><a href="./category-details.jsp">Categories</a></li>
                                    <li><a href="./publication-details.jsp">Anime Details</a></li>
                                    <li><a href="./anime-watching.jsp">Anime Watching</a></li>
                                    <li><a href="./blog-publication-details.jsp">Blog Details</a></li>
                                    <li><a href="./signup">Sign Up</a></li>
                                    <li><a href="./login.jsp">Login</a></li>
                                </ul>
                            </li>
                            <li><a href="./blog.jsp">Our Blog</a></li>
                            <li><a href="#">Contacts</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="col-lg-2">
                <div class="header__right">
                    <a href="#" class="search-switch"><span class="icon_search"></span></a>
                    <a href="./login.jsp"><span class="icon_profile"></span></a>
                </div>
            </div>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
</header>
--%>
<!-- Header End -->

<!-- Hero Section Begin -->
<section class="hero">
    <div class="container">
        <div class="hero__slider owl-carousel">
            <div class="hero__items set-bg" data-setbg="img/hero/hero-1.jpg">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="hero__text">
                            <div class="label">Adventure</div>
                            <h2>Fate / Stay Night: Unlimited Blade Works</h2>
                            <p>After 30 days of travel across the world...</p>
                            <a href="#"><span>Watch Now</span> <i class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__items set-bg" data-setbg="img/hero/hero-1.jpg">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="hero__text">
                            <div class="label">Adventure</div>
                            <h2>Fate / Stay Night: Unlimited Blade Works</h2>
                            <p>After 30 days of travel across the world...</p>
                            <a href="#"><span>Watch Now</span> <i class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__items set-bg" data-setbg="img/hero/hero-1.jpg">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="hero__text">
                            <div class="label">Adventure</div>
                            <h2>Fate / Stay Night: Unlimited Blade Works</h2>
                            <p>After 30 days of travel across the world...</p>
                            <a href="#"><span>Watch Now</span> <i class="fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<!-- Product Section Begin -->
<section class="product-page spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="product__page__content">
                    <div class="product__page__title">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-8">
                                <div class="section-title">
                                    <c:if test="${topicName!=null}">
                                        <h4>${topicName}</h4>
                                    </c:if>
                                    <c:if test="${searchName!=null}">
                                        <h4>${searchName}</h4>
                                    </c:if>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4">
                                </div>
                            </div>
                            <div class="row">
                                <div class="product__sort__filter">
                                    <p><fmt:message key="category.order"/>:</p>
                                    <form action="${url}" id="sortForm" method="POST">
                                        <input type="hidden" id="1" name="currentSort" value="${currentSort}">
                                        <input type="hidden" id="2" name="currentSize" value="${currentSize}">
                                        <input type="hidden" name="currentSearch" value="${searchName}">
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
                                        <input type="hidden" name="currentSearch" value="${searchName}">

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
                                        <input type="hidden" name="currentSearch" value="${searchName}">

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
                                        <input type="hidden" name="currentSearch" value="${searchName}">


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


