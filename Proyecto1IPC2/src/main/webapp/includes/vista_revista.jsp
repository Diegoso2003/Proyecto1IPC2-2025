<%-- 
    Document   : vista_revista
    Created on : 18 sept 2024, 0:32:33
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3>${nombre_revista}</h3>
<div class="container-fluid d-flex flex-column vh-100">
    <div class="iframe-container">
        <iframe src="${pageContext.servletContext.contextPath}/mvc/ServletRevista?id=${id}" style="width: 70%; height: 450%;">
            revista en pdf
        </iframe>
    </div>
</div>
