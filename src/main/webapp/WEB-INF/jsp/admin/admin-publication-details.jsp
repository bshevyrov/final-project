<%--
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
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<!-- Header End -->


<!-- Product Section Begin -->
<section class="publication-details">
    <p>Publication</p>
    <table>
        <tr>
            <th>Title</th>
            <th>Price</th>
            <th>Num of Topics</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <tr>
            <td>
                ${publication.title}
            </td>
            <td>
                ${publication.price}

            </td>
            <td>
                <c:set value="${publication.topics}" var="numTopics"/>
                ${fn:length(numTopics)}
            </td>

            <td>edit</td>
            <td>delete</td>
        </tr>


    </table>


</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<!-- Footer Section End -->

<!-- Js Plugins -->
<%@include file="/WEB-INF/jspf/scripts.jspf" %>

</body>

</html>--%>
