<%-- 
    Document   : tipo_computadoras
    Created on : 12 mar 2025, 4:07:25
    Author     : rafael-cayax
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.proyecto1ipc2.dtos.ensamblador.TipoComputadora"%>
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

        <h1 class="text-center">Lista de tipos de computadoras</h1>

        <div class="d-flex justify-content-center m-3">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addTipoComputadoraModal">
                Agregar tipo de computadora
            </button>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="addTipoComputadoraModal" tabindex="-1" aria-labelledby="addTipoComputadoraModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="addTipoComputadoraModalLabel">Agregar Tipo de Computadora</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="${pageContext.servletContext.contextPath}/controllers/ensamblador/tipo_computadora">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="nombreTipoComputadora" name="nombre" placeholder="Nombre">
                                <label for="nombreTipoComputadora">Nombre</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input type="number" class="form-control" id="precioTipoComputadora" name="precio" placeholder="Precio de venta" step="0.01">
                                <label for="precioTipoComputadora">Precio de venta</label>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="submit" class="btn btn-success">Crear</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <c:forEach items="${tiposComputadoras}" var="tipoComputadora">
            <div class="d-flex justify-content-center m-3">
                <div class="card w-50 m-3 border-info">
                    <div class="card-body">
                        <p>Nombre de la computadora: ${tipoComputadora.nombre}</p>
                        <p>ID del tipo de computadora: ${tipoComputadora.idTipo}</p>
                        <p>Precio de venta: ${tipoComputadora.precio}</p>
                        <div class="d-flex justify-content-center">
                            <a href="${pageContext.servletContext.contextPath}/controllers/financiero/gestion_compu?id=${tipoComputadora.idTipo}" class="btn btn-primary">Gestionar</a>
                        </div>
                    </div>
               </div> 
            </div>
        </c:forEach>
    </body>
</html>
