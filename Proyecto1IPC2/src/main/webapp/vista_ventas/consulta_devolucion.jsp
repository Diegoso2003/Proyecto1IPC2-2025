<%-- 
    Document   : consulta_devolucion
    Created on : 9 mar 2025, 21:48:27
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
                        text-center">Consulta de devoluciones</h5>
                    <div class="card-body">
                        <p>Devoluciones de cliente: ${consulta.cliente.nombre}</p>
                        <p>NIT: ${consulta.cliente.nit}</p>
                        <c:if test="${fechaInicio != null}">
                            <p>Fecha de inicio: ${fechaInicio}</p>
                        </c:if>
                        <c:if test="${fechaFin != null}">
                            <p>Fecha de fin: ${fechaFin}</p>
                        </c:if>
                        <p>Devoluciones:</p>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>fecha de devolucion</th>
                                    <th>NO de factura</th>
                                    <th>costo venta</th>
                                    <th>id computadora</th>
                                    <th>computadora</th>
                                    <th>perdida</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${consulta.devoluciones}" var="devolucion">
                                    <tr>
                                        <td>${devolucion.fechaDevolucion}</td>
                                        <td>${devolucion.compra.idCompra}</td>
                                        <td>Q${devolucion.costoVenta}</td>
                                        <td>${devolucion.computadora.idComputadora}</td>
                                        <td>${devolucion.computadora.tipo.nombre}</td>
                                        <td>Q${devolucion.perdida}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
