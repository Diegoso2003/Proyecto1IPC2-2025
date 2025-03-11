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
        <c:if test="${consulta.devoluciones.size() > 0}">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-75 m-3 border-info">
                    <div class="card-body">
                        <h5 class="card-title text-center">Devoluciones</h5>
                        <table class="table table-striped">
                            <thead>
                                <tr>
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
                                <c:forEach items="${consulta.devoluciones}" var="devolucion">
                                    <tr>
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
        </c:if>
    </body>
</html>
