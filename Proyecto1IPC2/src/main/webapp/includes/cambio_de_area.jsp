<%-- 
    Document   : cambio_de_area
    Created on : 10 mar 2025, 4:40:40
    Author     : rafael-cayax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${rol != null}">
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="${pageContext.servletContext.contextPath}/svg/diagram-2.svg" width="24" height="24">
            <br>
            Cambiar de area
        </a>
        <ul class="dropdown-menu bg-primary">
          <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controllers/ensamblador/componente">
            Area de ensamblaje</a></li>
          <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_ventas/entrada_nit.jsp">
            Area de ventas</a></li>
          <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/vista_ventas/form_factura.jsp">
            Area financiera</a></li>
        </ul>
    </li>
</c:if>
