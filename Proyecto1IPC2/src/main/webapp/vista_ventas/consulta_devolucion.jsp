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
                </div>
            </div>
        </div>
        <c:if test="${consulta.devoluciones.size() == 0}">
            <div class="alert alert-warning" role="alert">
                No hay devoluciones registradas para este cliente en el periodo de tiempo seleccionado   
            </div>
        </c:if>
        <c:forEach items="${consulta.devoluciones}" var="devolucion">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title text-center">Devolución NO. ${devolucion.idDevolucion}</h5>
                        <div class="m-5">
                            <p class="card-title text-center">Datos de la factura</p>
                            <p class="card-text"><strong>Fecha:</strong> ${devolucion.compra.fechaCompra}</p>
                            <p class="card-text"><strong>Precio de compra:</strong> Q${devolucion.costoVenta}</p>
                            <p class="card-text"><strong>No de factura:</strong> ${devolucion.compra.idCompra}</p>
                            <p class="card-text"><strong>Vendedor:</strong> ${devolucion.compra.usuario.nombre}</p>
                            <p class="card-title text-center">Datos de la computadora</p>
                            <p class="card-text"><strong>ID:</strong> ${devolucion.computadora.idComputadora}</p>
                            <p class="card-text"><strong>Tipo de computadora:</strong> ${devolucion.computadora.tipo.nombre}</p>
                            <p class="card-title text-center">Datos de la devolución</p>
                            <p class="card-text"><strong>Fecha:</strong> ${devolucion.fechaDevolucion}</p>
                            <p class="card-text"><strong>Pérdida:</strong> Q${devolucion.perdida}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
