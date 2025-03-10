<%-- 
    Document   : consulta_cliente
    Created on : 9 mar 2025, 18:00:00
    Author     : rafael-cayax
--%>

<%@page import="com.mycompany.proyecto1ipc2.ventas.consultas.ConsultaCompraCliente"%>
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
        <div class="d-flex justify-content-center">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title
                        text-center">Consulta de compras</h5>
                    <div class="card-body">
                        <p>Compras de cliente: ${consulta.cliente.nombre}</p>
                        <p>NIT: ${consulta.cliente.nit}</p>
                        <c:if test="${fechaInicio != null}">
                            <p>Fecha de inicio: ${fechaInicio}</p>
                        </c:if>
                        <c:if test="${fechaFin != null}">
                            <p>Fecha de fin: ${fechaFin}</p>
                        </c:if>
                        <p>Compras:</p>
                    </div>
                    <c:forEach items="${consulta.consulta}" var="compra">
                        <c:if test="${compra.detalles.size() > 0}">
                            <div class="card-body">
                                <p>Fecha de compra: ${compra.fechaCompra}</p>
                                <p>Numero de factura: ${compra.idCompra}</p>
                                <p>Total: ${compra.total}</p>
                                <p>Productos:</p>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Subtotal</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${compra.detalles}" var="detalle">
                                            <tr>
                                                <td>${detalle.computadora.idComputadora}</td>
                                                <td>${detalle.computadora.tipo.nombre}</td>
                                                <td>Q${detalle.subtotal}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
