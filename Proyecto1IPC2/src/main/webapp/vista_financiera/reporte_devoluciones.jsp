<%-- 
    Document   : reporte_devoluciones
    Created on : 10 mar 2025, 13:09:42
    Author     : rafael-cayax
--%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.financiero.reportes.ReporteDevolucion"%>
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

        <c:if test="${reporte.devoluciones.size() == 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="alert alert-warning w-50" role="alert">
                    No hay devoluciones registradas en el rango de fechas seleccionado
                </div>
            </div>
        </c:if>
        <c:if test="${reporte.devoluciones.size() > 0}">
            <form class="mb-5" action="${pageContext.servletContext.contextPath}/controllers/financiero/reporte_compras" method="post">
                <input type="hidden" name="fechaInicio" value="${fechaInicio}">
                <input type="hidden" name="fechaFin" value="${fechaFin}">
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary w-25 py-2 m-3">Exportar Reporte</button>
                </div>
            </form>
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
