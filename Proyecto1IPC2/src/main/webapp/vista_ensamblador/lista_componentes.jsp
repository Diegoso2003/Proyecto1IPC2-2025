<%-- 
    Document   : lista_componentes
    Created on : 24 feb 2025, 1:27:17
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.Componente"%>
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

        <h1 class="text-center">Lista de componentes</h1>        
        <c:forEach items="${componentes}" var="componente">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 ${componente.cantidad < 10 ? 'alert alert-danger' : 'border-success'}">
                    <div class="card-body">
                        <p>Nombre del componente: ${componente.nombre}</p>
                        <p>Precio del componente: ${componente.precio}</p>
                        <p>Existencia del componente: ${componente.cantidad}</p>
                        <div class="d-flex justify-content-center">
                            <a href="${pageContext.servletContext.contextPath}/controllers/ensamblador/gestion_componente?id=${componente.id}" class="btn btn-primary w-25 py-2">Gestionar</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
