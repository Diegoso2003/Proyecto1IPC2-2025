<%-- 
    Document   : factura
    Created on : 4 mar 2025, 0:50:43
    Author     : rafael-cayax
--%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ventas.Compra"%>
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

        <c:if test="${factura != null}">
            <div class="d-flex justify-content-center">
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

                        <h5 class="card-title
                        text-center">Ingresar Computadora</h5>
                        <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/ventas/detalle_venta" method="post">
                            <input type="hidden" class="form-control" id="idCompra" name="id" value="${factura.idCompra}">
                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="idCompu" name="idComputadora" placeholder="Codigo de la computadora">
                                <label for="idCompu">Codigo de la Computadora</label>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary w-25 py-2 m-3">Ingresar</button>
                            </div>
                        </form>

                        <h5 class="card-title
                        text-center">Detalles de la factura</h5>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Codigo</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Subtotal</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${factura.detalles}" var="detalle">
                                    <tr>
                                        <td>${detalle.computadora.idComputadora}</td>
                                        <td>${detalle.computadora.tipo.nombre}</td>
                                        <td>${detalle.subtotal}</td>
                                        <td>
                                            <a href="${pageContext.servletContext.contextPath}/controllers/ventas/detalle_venta?idComputadora=${detalle.computadora.idComputadora}&id=${factura.idCompra}" class="btn btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-flex justify-content-center">
                            <a href="${pageContext.servletContext.contextPath}/controllers/ventas/compra?id=${factura.idCompra}" class="btn btn-primary w-25 py-2 m-3">Cancelar compra</a>
                            <c:if test="${factura.detalles.size() > 0}">
                                <a target="_blank" href="${pageContext.servletContext.contextPath}/controllers/ventas/facturacion?id=${factura.idCompra}" class="btn btn-primary w-25 py-2 m-3">Confirmar compra</a>
                            </c:if>"
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
