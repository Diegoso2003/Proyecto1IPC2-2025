<%-- 
    Document   : fechas
    Created on : 10 mar 2025, 13:14:44
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:if test="${fechaInicio != null}">
    <p class="text-center">Fecha de inicio: ${fechaInicio}</p>
</c:if>
<c:if test="${fechaFin != null}">
    <p class="text-center">Fecha de fin: ${fechaFin}</p>
</c:if>
