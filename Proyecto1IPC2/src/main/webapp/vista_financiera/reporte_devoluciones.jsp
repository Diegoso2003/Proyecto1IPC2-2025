<%-- 
    Document   : reporte_devoluciones
    Created on : 10 mar 2025, 13:09:42
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
        <jsp:include page="/vista_financiera/header_financiero.jsp"/>

        <jsp:include page="/includes/informacion.jsp"/>
        <h3 class="text-center">Reporte de devoluciones</h3>
        <jsp:include page="/includes/fechas.jsp"/>
        <c:if test="${devoluciones.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay devoluciones registradas en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>
        <c:if test="${devoluciones.size() > 0}">
            <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/reporte_compras" method="post">
                <input type="hidden" name="fechaInicio" value="${fechaInicio}">
                <input type="hidden" name="fechaFin" value="${fechaFin}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary w-25 py-2 m-3">Exportar Reporte</button>
                </div>
            </form>
        </c:if>
        <c:forEach items="${devoluciones}" var="devolucion">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title
                        text-center">Devolucion NO. ${devolucion.idDevolucion}</h5>
                        <div class="m-5">
                            <p class="card-title text-center">Datos de la factura</p>
                            <p class="card-text"><strong>Cliente:</strong> ${devolucion.compra.cliente.nombre}</p>
                            <p class="card-text"><strong>NIT:</strong> ${devolucion.compra.cliente.nit}</p>
                            <p class="card-text"><strong>Fecha:</strong> ${devolucion.compra.fechaCompra}</p>
                            <p class="card-text"><strong>Precio de compra:</strong> ${devolucion.costoVenta}</p>
                            <p class="card-text"><strong>No de factura: </strong> ${devolucion.compra.idCompra}</p>
                            <p class="card-text"><strong>Vendedor:</strong> ${devolucion.compra.usuario.nombre}</p>
                            <p class="card-title text-center">Datos de la computadora</p>
                            <p class="card-text"><strong>ID:</strong> ${devolucion.computadora.idComputadora}</p>
                            <p class="card-text"><strong>Tipo de computadora:</strong> ${devolucion.computadora.tipo.nombre}</p>
                            <p class="card-title text-center">Datos de la devolucion</p>
                            <p class="card-text"><strong>Fecha:</strong> ${devolucion.fechaDevolucion}</p>
                            <p class="card-text"><strong>perdida:</strong> ${devolucion.perdida}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
