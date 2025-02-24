<%-- 
    Document   : informacion
    Created on : 15 sept 2024, 0:21:52
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (request.getAttribute("mensaje") != null) {
%>
<div class="d-flex justify-content-center m-5">
    <div class="w-50">
        <div class="alert alert-danger text-center" role="alert">
            <h4 class="alert-heading">Error!</h4>
            <p>${mensaje}</p>
        </div>
    </div>
</div>
<%
    }
%>
<%
    if (request.getAttribute("exito") != null) {
%>
<div class="d-flex justify-content-center m-5">
    <div class="w-50">
        <div class="alert alert-info text-center" role="alert">
            <h4 class="alert-heading">Información</h4>
            <p>${exito}</p>
        </div>
    </div>
</div>
<%
    }
%>
