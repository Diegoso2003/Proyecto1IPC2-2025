<%-- 
    Document   : compras_del_dia
    Created on : 9 mar 2025, 23:36:04
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
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
        <c:if test="${compras.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay compras registradas en el dia: ${fechaInicio}
                </div>
            </div>
        </c:if>
        <c:forEach items="${compras}" var="factura">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title
                        text-center">Factura NO. ${factura.idCompra}</h5>
                        <div class="m-5">
                            <p class="card-text"><strong>Cliente:</strong> ${factura.cliente.nombre}</p>
                            <p class="card-text"><strong>NIT:</strong> ${factura.cliente.nit}</p>
                            <p class="card-text"><strong>Direccion:</strong> ${factura.cliente.direccion}</p>
                            <p class="card-text"><strong>Fecha:</strong> ${factura.fechaCompra}</p>
                            <p class="card-text"><strong>Total:</strong> ${factura.total}</p>
                        </div>
    
                        <c:if test="${factura.detalles.size() == 0}">
                            <div class="alert alert-warning" role="alert">
                                No hay detalles en la factura
                            </div>
                        </c:if>
                        <c:if test="${factura.detalles.size() > 0}">
                            <h5 class="card-title
                        text-center">Detalles de la factura</h5>
    
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Codigo</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${factura.detalles}" var="detalle">
                                    <tr>
                                        <td>${detalle.computadora.idComputadora}</td>
                                        <td>${detalle.computadora.tipo.nombre}</td>
                                        <td>${detalle.subtotal}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
