<!DOCTYPE html>
<html lang="zxx">

<head>

    <title>Anime | Template</title>

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

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="breadcrumb__links">
                    <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                    <a href="./categories.html">Categories</a>
                    <span>Romance</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Product Section Begin -->
<section class="product-page spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="product__page__content">
                    <div class="product__page__title">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-6">
                                <div class="section-title">
                                    <h4>Romance</h4>
                                </div>
                            </div>
                            <div class="row">
                                <%--                                <div class="product__page__filter">--%>
                                <div class="product__sort__filter">
                                    <p>Order by:</p>
                                    <form action="${url}" id="sortForm" method="POST">
                                        <input type="hidden" id="1" name="currentSort" value="${currentSort}">
                                        <input type="hidden" id="2" name="currentSize" value="${currentSize}">
                                        <select name="sort" onchange="this.form.submit()">
                                            <option value="titleAsc">Title asc</option>
                                            <option value="titleDesc">Title desc</option>
                                            <option value="priceAsc">Price asc</option>
                                            <option value="priceDesc">Price desc</option>
                                        </select>
                                    </form>

                                    <%--                                    <form  action="${url}" method="POST">--%>
                                    <%--                                        <input type="hidden" id="1" name="currentSort" value="${currentSort}">--%>
                                    <%--                                        <input type="hidden" id="2" name="currentSize" value="${currentSize}">--%>

                                    <%--                                    </form>--%>
                                    <%--                                    <form hidden>--%>
                                    <%--                                        <input name="currentSort" value="${currentSort}">--%>
                                    <%--                                        <input name="currentSize" value="${currentSize}">--%>
                                    <%--                                    </form>--%>

                                </div>
                                <%--                                <div class="col-lg-4 col-md-4 col-sm-6">--%>
                                <div class="product__size__filter">
                                    <p>Page size:</p>
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
                                <%--                                </div>--%>
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
                                    <button type="submit" class="button_prev" disabled style="color: grey">Prev</button>
                                </c:when>
                                <c:otherwise>
                                    <form action="${url}" method="POST">
                                        <input type="hidden" name="currentSort" value="${currentSort}">
                                        <input type="hidden" name="currentSize" value="${currentSize}">
                                        <input type="hidden" name="currentPage" value="${currentPage-1}">

                                        <button type="submit" class="button_prev">Prev</button>
                                    </form>

                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="column">

                            <c:choose>
                                <c:when test="${currentPage==lastPage}">
                                    <button type="submit" class="button_next" disabled style="color: grey">Next</button>
                                </c:when>
                                <c:otherwise>
                                    <form action="${url}" method="POST">
                                        <input type="hidden" name="currentSort" value="${currentSort}">
                                        <input type="hidden" name="currentSize" value="${currentSize}">
                                        <input type="hidden" name="currentPage" value="${currentPage+1}">

                                        <button type="submit" class="button_next">Next</button>
                                    </form>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <%--                    <%@include file="/WEB-INF/jspf/pagination.jspf" %>--%>

                    <%--                    <a href="#" class="current-page">1</a>--%>
                    <%--                    <a href="#">2</a>--%>
                    <%--                    <a href="#">3</a>--%>
                    <%--                    <a href="#">4</a>--%>
                    <%--                    <a href="#">5</a>--%>
                    <%--                    <a href="#"><i class="fa fa-angle-double-right"></i></a>--%>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-8">
                <div class="product__sidebar">
                    <div class="product__sidebar__view">
                        <div class="section-title">
                            <h5>Top Views</h5>
                        </div>
                        <ul class="filter__controls">
                            <li class="active" data-filter="*">Day</li>
                            <li data-filter=".week">Week</li>
                            <li data-filter=".month">Month</li>
                            <li data-filter=".years">Years</li>
                        </ul>
                        <div class="filter__gallery">
                            <div class="product__sidebar__view__item set-bg mix day years"
                                 data-setbg="img/sidebar/tv-1.jpg">
                                <div class="ep">18 / ?</div>
                                <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                <h5><a href="#">Boruto: Naruto next generations</a></h5>
                            </div>
                            <div class="product__sidebar__view__item set-bg mix month week"
                                 data-setbg="img/sidebar/tv-2.jpg">
                                <div class="ep">18 / ?</div>
                                <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                <h5><a href="#">The Seven Deadly Sins: Wrath of the Gods</a></h5>
                            </div>
                            <div class="product__sidebar__view__item set-bg mix week years"
                                 data-setbg="img/sidebar/tv-3.jpg">
                                <div class="ep">18 / ?</div>
                                <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                <h5><a href="#">Sword art online alicization war of underworld</a></h5>
                            </div>
                            <div class="product__sidebar__view__item set-bg mix years month"
                                 data-setbg="img/sidebar/tv-4.jpg">
                                <div class="ep">18 / ?</div>
                                <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                <h5><a href="#">Fate/stay night: Heaven's Feel I. presage flower</a></h5>
                            </div>
                            <div class="product__sidebar__view__item set-bg mix day"
                                 data-setbg="img/sidebar/tv-5.jpg">
                                <div class="ep">18 / ?</div>
                                <div class="view"><i class="fa fa-eye"></i> 9141</div>
                                <h5><a href="#">Fate stay night unlimited blade works</a></h5>
                            </div>
                        </div>
                    </div>
                    <div class="product__sidebar__comment">
                        <div class="section-title">
                            <h5>New Comment</h5>
                        </div>
                        <div class="product__sidebar__comment__item">
                            <div class="product__sidebar__comment__item__pic">
                                <img src="img/sidebar/comment-1.jpg" alt="">
                            </div>
                            <div class="product__sidebar__comment__item__text">
                                <ul>
                                    <li>Active</li>
                                    <li>Movie</li>
                                </ul>
                                <h5><a href="#">The Seven Deadly Sins: Wrath of the Gods</a></h5>
                                <span><i class="fa fa-eye"></i> 19.141 Viewes</span>
                            </div>
                        </div>
                        <div class="product__sidebar__comment__item">
                            <div class="product__sidebar__comment__item__pic">
                                <img src="img/sidebar/comment-2.jpg" alt="">
                            </div>
                            <div class="product__sidebar__comment__item__text">
                                <ul>
                                    <li>Active</li>
                                    <li>Movie</li>
                                </ul>
                                <h5><a href="#">Shirogane Tamashii hen Kouhan sen</a></h5>
                                <span><i class="fa fa-eye"></i> 19.141 Viewes</span>
                            </div>
                        </div>
                        <div class="product__sidebar__comment__item">
                            <div class="product__sidebar__comment__item__pic">
                                <img src="img/sidebar/comment-3.jpg" alt="">
                            </div>
                            <div class="product__sidebar__comment__item__text">
                                <ul>
                                    <li>Active</li>
                                    <li>Movie</li>
                                </ul>
                                <h5><a href="#">Kizumonogatari III: Reiket su-hen</a></h5>
                                <span><i class="fa fa-eye"></i> 19.141 Viewes</span>
                            </div>
                        </div>
                        <div class="product__sidebar__comment__item">
                            <div class="product__sidebar__comment__item__pic">
                                <img src="img/sidebar/comment-4.jpg" alt="">
                            </div>
                            <div class="product__sidebar__comment__item__text">
                                <ul>
                                    <li>Active</li>
                                    <li>Movie</li>
                                </ul>
                                <h5><a href="#">Monogatari Series: Second Season</a></h5>
                                <span><i class="fa fa-eye"></i> 19.141 Viewes</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<footer class="footer">
    <div class="page-up">
        <a href="#" id="scrollToTopButton"><span class="arrow_carrot-up"></span></a>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="footer__logo">
                    <a href="./index.html"><img src="img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="footer__nav">
                    <ul>
                        <li class="active"><a href="./index.html">Homepage</a></li>
                        <li><a href="./categories.html">Categories</a></li>
                        <li><a href="./blog.html">Our Blog</a></li>
                        <li><a href="#">Contacts</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                    All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by
                    <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>

            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Search model Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch"><i class="icon_close"></i></div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search model end -->


<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Search model Begin -->
<!-- Search model end -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>
<%--<script>--%>
<%--    function submitForms(){--%>
<%--        document.getElementById("sortForm").submit();--%>
<%--        document.getElementById("sizeForm").submit();--%>
<%--        document.getElementById("sizeForm").submit();--%>
<%--        --%>
<%--        // document.submitforms[1].submit();--%>
<%--    }--%>
<%--</script>--%>

</body>

</html>
