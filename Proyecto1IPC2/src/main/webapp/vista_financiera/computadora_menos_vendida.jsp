<%-- 
    Document   : computadora_menos_vendida
    Created on : 11 mar 2025, 16:29:09
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.financiero.reportes.ReporteCompuVentas"%>
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
        <h3 class="text-center">Reporte computadora mas vendida</h3>
        <jsp:include page="/includes/fechas.jsp"/>
        <div class="d-flex justify-content-center">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title
                        text-center">Computadora mas vendida</h5>
                    <p class="card-text">
                        <c:out value="${reporte.tipo.nombre}"/>
                    </p>
               </div>
            </div>
        </div>

        <c:if test="${reporte.detalles.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay detalles en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>

        <c:if test="${reporte.detalles.size() > 0}">
            <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/reporte_compras" method="post">
                <input type="hidden" name="fechaInicio" value="${fechaInicio}">
                <input type="hidden" name="fechaFin" value="${fechaFin}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary w-25 py-2 m-3">Exportar Reporte</button>
                </div>
            </form>

            <div class="d-flex justify-content-center">
                <div class="card w-75 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title text-center">Detalles de Compras</h5>
                        <div class="container mt-4">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Factura NO.</th>
                                    <th>fecha de compra</th>
                                    <th>nit</th>
                                    <th>vendedor</th>
                                    <th>subtotal</th>
                                    <th>ID computadora</th>
                                    <th>ganancia</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${reporte.detalles}" var="detalle">
                                        <tr>
                                            <td>${detalle.compra.idCompra}</td>
                                            <td>${detalle.compra.fechaCompra}</td>
                                            <td>${detalle.compra.cliente.nit}</td>
                                            <td>${detalle.compra.usuario.nombre}</td>
                                            <td>${detalle.subtotal}</td>
                                            <td>${detalle.computadora.idComputadora}</td>
                                            <td>${detalle.computadora.ganancia}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
