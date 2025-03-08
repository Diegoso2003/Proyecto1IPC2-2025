<%-- 
    Document   : sala_ventas
    Created on : 5 mar 2025, 12:59:24
    Author     : rafael-cayax
--%>

<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora"%>
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
        <jsp:include page="/vista_ventas/header_ventas.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>
        <div class="d-flex justify-content-center m-3">
            <div class="card border-dark w-50">
                <div class="card-body">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr>
                                <th>Id computadora</th>
                                <th>Tipo de computadora</th>
                                <th>Precio</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${computadoras}" var="computadora">
                                <tr>
                                    <td>${computadora.idComputadora}</td>
                                    <td>${computadora.tipo.nombre}</td>
                                    <td>${computadora.tipo.precio}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
