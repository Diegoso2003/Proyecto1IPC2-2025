<%-- 
    Document   : consulta_cliente
    Created on : 9 mar 2025, 18:00:00
    Author     : rafael-cayax
--%>

<%@page import="com.mycompany.proyecto1ipc2.ventas.consultas.ConsultaCliente"%>
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
                        <p>Direccion: ${consulta.cliente.direccion}</p>
                        <c:if test="${fechaInicio != null}">
                            <p>Fecha de inicio: ${fechaInicio}</p>
                        </c:if>
                        <c:if test="${fechaFin != null}">
                            <p>Fecha de fin: ${fechaFin}</p>
                        </c:if>
                        <p>Compras:</p>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${consulta.consulta.size() == 0}">
            <div class="alert alert-warning" role="alert">
                No hay compras registradas para este cliente en el periodo de tiempo seleccionado  
            </div>
        </c:if>
        <c:forEach items="${consulta.consulta}" var="compra">
            <div class="d-flex justify-content-center">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <p>Fecha de compra: ${compra.fechaCompra}</p>
                        <p>Numero de factura: ${compra.idCompra}</p>
                        <p>Total: ${compra.total}</p>
                        <p>Vendedor: ${compra.usuario.nombre}</p>
                        <p>Productos:</p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Subtotal</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${compra.detalles}" var="detalle">
                                    <tr>
                                        <td>${detalle.computadora.idComputadora}</td>
                                        <td>${detalle.computadora.tipo.nombre}</td>
                                        <td>Q${detalle.subtotal}</td>
                                        <td>${detalle.computadora.estado.descripcion}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
