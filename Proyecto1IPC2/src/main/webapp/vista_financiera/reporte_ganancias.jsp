<%-- 
    Document   : reporte_ganancias
    Created on : 11 mar 2025, 1:00:54
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
        <h5 class="text-center">Reporte de ganancias</h5>
        <jsp:include page="/includes/fechas.jsp"/>
        <div class="d-flex justify-content-center m-3">
            <div class="card w-50 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title">Ganancias y Pérdidas</h5>
                    <p class="card-text">Ganancia: Q${reporte.ganancia}</p>
                    <p class="card-text">Pérdida: Q${reporte.perdida}</p>
                </div>
            </div>
        </div>
        <c:if test="${reporte.compras.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay compras registradas en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>
        <c:forEach items="${reporte.compras}" var="factura">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-75 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title
                        text-center">Factura NO. ${factura.idCompra}</h5>
                        <div class="m-5">
                            <p class="card-text"><strong>Cliente:</strong> ${factura.cliente.nombre}</p>
                            <p class="card-text"><strong>NIT:</strong> ${factura.cliente.nit}</p>
                            <p class="card-text"><strong>Direccion:</strong> ${factura.cliente.direccion}</p>
                            <p class="card-text"><strong>Fecha:</strong> ${factura.fechaCompra}</p>
                            <p class="card-text"><strong>Total:</strong> ${factura.total}</p>
                            <p class="card-text"><strong>Vendedor:</strong> ${factura.usuario.nombre}</p>
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
                                    <th scope="col">precio fabricacion</th>
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
                                        <td>${detalle.computadora.precioFabricacion}</td>
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
        <c:if test="${reporte.devoluciones.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay devoluciones registradas en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>
        <div class="d-flex justify-content-center m-3">
            <div class="card w-75 m-3 border-info">
                <div class="card-body">
                    <h5 class="card-title text-center">Devoluciones</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Cliente</th>
                                <th scope="col">NIT</th>
                                <th scope="col">Fecha Compra</th>
                                <th scope="col">Precio Compra</th>
                                <th scope="col">Factura NO.</th>
                                <th scope="col">Vendedor</th>
                                <th scope="col">ID Comp.</th>
                                <th scope="col">Tipo Comp.</th>
                                <th scope="col">Fecha Devol.</th>
                                <th scope="col">Perdida</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reporte.devoluciones}" var="devolucion">
                    <tr>
                        <td>${devolucion.compra.cliente.nombre}</td>
                        <td>${devolucion.compra.cliente.nit}</td>
                        <td>${devolucion.compra.fechaCompra}</td>
                        <td>${devolucion.costoVenta}</td>
                        <td>${devolucion.compra.idCompra}</td>
                        <td>${devolucion.compra.usuario.nombre}</td>
                        <td>${devolucion.computadora.idComputadora}</td>
                        <td>${devolucion.computadora.tipo.nombre}</td>
                        <td>${devolucion.fechaDevolucion}</td>
                        <td>${devolucion.perdida}</td>
                    </tr>
                </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
            </div>
    </body>
</html>
