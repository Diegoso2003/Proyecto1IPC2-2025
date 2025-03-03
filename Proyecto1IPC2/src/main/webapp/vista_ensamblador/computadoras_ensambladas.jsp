<%-- 
    Document   : computadoras_ensambladas
    Created on : 2 mar 2025, 22:32:25
    Author     : rafael-cayax
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.Computadora"%>
<%@page import="com.mycompany.proyecto1ipc2.enums.EnumEstadoCompu"%>
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

        <h1 class="h3 mb-3 text-center">Computadoras ensambladas</h1>

        <p class="text-center h5">Ordenar Por fecha</p>
        <div class="d-flex justify-content-center">
            <a class="btn btn-primary w-25 py-2 m-3" href="${pageContext.servletContext.contextPath}/controllers/ensamblador/computadora?orden=DESC">Mayor a menor</a>
            <a class="btn btn-secondary w-25 py-2 m-3" href="${pageContext.servletContext.contextPath}/controllers/ensamblador/computadora?orden=ASC">Menor a mayor</a>
        </div>
        <div class="d-flex justify-content-center">
            <div class="card border-dark w-75">
                <div class="card-body">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Fecha de ensamblaje</th>
                                <th>Tipo de computadora</th>
                                <th>Estado</th>
                                <th>Ensamblador</th>
                                <th>Precio de fabricacion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${computadoras}" var="computadora">
                                <tr>
                                    <td>${computadora.idComputadora}</td>
                                    <td>${computadora.fechaEnsamblaje}</td>
                                    <td>${computadora.tipo.nombre}</td>
                                    <td>${computadora.estado.descripcion}</td>
                                    <td>${computadora.ensamblador}</td>
                                    <td>${computadora.precioFabricacion}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
    </body>
</html>
