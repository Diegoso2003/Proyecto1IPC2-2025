<%-- 
    Document   : reporte_compras_usuario
    Created on : 10 mar 2025, 18:46:27
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.financiero.ReporteMasVentas"%>
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
        <c:if test="${reporte.reporte.usuario == null}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay compras registradas en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>
        <c:if test="${reporte.reporte.usuario != null}">
            <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/reporte_compras" method="post">
                <input type="hidden" name="fechaInicio" value="${fechaInicio}">
                <input type="hidden" name="fechaFin" value="${fechaFin}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary w-25 py-2 m-3">Exportar Reporte</button>
                </div>
            </form>
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title text-center">Vendedor: ${reporte.reporte.usuario.nombre}</h5>
                        <p class="card-text"><strong>Total de ventas:</strong> ${reporte.reporte.totalVentas}</p>
                        <p class="card-text">Ventas: </p>
                    </div>
                </div>
            </div>
            <c:forEach items="${reporte.reporte.compras}" var="factura">
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
                                        <th scope="col">Estado</th>
                                        <th scope="col">Ganancia</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${factura.detalles}" var="detalle">
                                        <tr>
                                            <td>${detalle.computadora.idComputadora}</td>
                                            <td>${detalle.computadora.tipo.nombre}</td>
                                            <td>${detalle.subtotal}</td>
                                            <td>${detalle.computadora.estado.descripcion}</td>
                                            <td>${detalle.computadora.ganancia}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </body>
</html>
