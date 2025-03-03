<%-- 
    Document   : tipo_computadoras
    Created on : 1 mar 2025, 19:47:25
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/vista_ensamblador/header_ensamblador.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>

        <h1 class="text-center">Lista de tipos de computadoras</h1>

        <c:forEach items="${tiposComputadoras}" var="tipoComputadora">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <p>Nombre de la computadora: ${tipoComputadora.nombre}</p>
                        <p>ID del tipo de computadora: ${tipoComputadora.idTipo}</p>
                        <p>Precio de venta: ${tipoComputadora.precio}</p>
                        <div class="d-flex justify-content-center">
                            <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/ensamblaje?id=${tipoComputadora.idTipo}" class="btn btn-primary w-25 py-2 m-3">Ensamblar</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>